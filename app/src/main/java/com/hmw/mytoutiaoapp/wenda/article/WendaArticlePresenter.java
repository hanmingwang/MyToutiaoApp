package com.hmw.mytoutiaoapp.wenda.article;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hmw.mytoutiaoapp.net.RetrofitFactory;
import com.hmw.mytoutiaoapp.net.api.IMobileWendaApi;
import com.hmw.mytoutiaoapp.util.ErrorAction;
import com.hmw.mytoutiaoapp.util.TimeUtil;
import com.hmw.mytoutiaoapp.wenda.bean.WendaArticleBean;
import com.hmw.mytoutiaoapp.wenda.bean.WendaArticleDataBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by han on 2018/6/22.
 */

class WendaArticlePresenter implements IWendaArticle.Presenter {

    private static final String TAG = "WendaArticlePresenter";
    private IWendaArticle.View view;
    private String time;
    private Gson gson = new Gson();
    private List<WendaArticleDataBean> dataList = new ArrayList<>();


    WendaArticlePresenter(IWendaArticle.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doLoadData() {

        // 释放内存
        if (dataList.size() > 100) {
            dataList.clear();
        }

        RetrofitFactory.getRetrofit().create(IMobileWendaApi.class)
                .getWendaArticle(time)
                .subscribeOn(Schedulers.io())
                .switchMap((Function<WendaArticleBean, Observable<WendaArticleDataBean>>) wendaArticleBean -> {

                    List<WendaArticleDataBean> list = new ArrayList<>();
                    for (WendaArticleBean.DataBean bean : wendaArticleBean.getData()) {
                        WendaArticleDataBean contentBean = gson.fromJson(bean.getContent(), WendaArticleDataBean.class);
                        list.add(contentBean);
                    }
                    return Observable.fromIterable(list);
                })
                .filter(wendaArticleDataBean -> !TextUtils.isEmpty(wendaArticleDataBean.getQuestion()))
                .map(bean -> {

                    WendaArticleDataBean.ExtraBean extraBean = gson.fromJson(bean.getExtra(), WendaArticleDataBean.ExtraBean.class);
                    WendaArticleDataBean.QuestionBean questionBean = gson.fromJson(bean.getQuestion(), WendaArticleDataBean.QuestionBean.class);
                    WendaArticleDataBean.AnswerBean answerBean = gson.fromJson(bean.getAnswer(), WendaArticleDataBean.AnswerBean.class);
                    bean.setExtraBean(extraBean);
                    bean.setQuestionBean(questionBean);
                    bean.setAnswerBean(answerBean);

                    time = bean.getBehot_time();
                    return bean;
                })
                .filter(wendaArticleDataBean -> {
                    for (WendaArticleDataBean bean : dataList) {
                        if (bean.getQuestionBean().getTitle().equals(wendaArticleDataBean.getQuestionBean().getTitle())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose())
                .subscribe(this::doSetAdapter, throwable -> {
                    doShowNetError();
                    ErrorAction.print(throwable);
                });
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doRefresh() {
        if (dataList.size() != 0) {
            dataList.clear();
            time = TimeUtil.getCurrentTimeStamp();
        }
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doSetAdapter(List<WendaArticleDataBean> list) {
        dataList.addAll(list);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }
}
