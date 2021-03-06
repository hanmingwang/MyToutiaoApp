package com.hmw.mytoutiaoapp.video.article;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hmw.mytoutiaoapp.net.RetrofitFactory;
import com.hmw.mytoutiaoapp.net.api.IMobileVideoApi;
import com.hmw.mytoutiaoapp.news.bean.MultiNewsArticleBean;
import com.hmw.mytoutiaoapp.news.bean.MultiNewsArticleDataBean;
import com.hmw.mytoutiaoapp.util.ErrorAction;
import com.hmw.mytoutiaoapp.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by han on 2018/6/23.
 */

public class VideoArticlePresenter implements IVideoArticle.Presenter {

    private static final String TAG = "VideoArticlePresenter";
    private IVideoArticle.View view;
    private String category;
    private String time;
    private Gson gson = new Gson();
    private List<MultiNewsArticleDataBean> dataList = new ArrayList<>();

    VideoArticlePresenter(IVideoArticle.View view) {
        this.view = view;
        this.time = TimeUtil.getCurrentTimeStamp();
    }

    @Override
    public void doLoadData(String... category) {
        try {
            if (null == this.category) {
                this.category = category[0];
            }
        } catch (Exception e) {
            ErrorAction.print(e);
        }

        // 释放内存
        if (dataList.size() > 100) {
            dataList.clear();
        }

        RetrofitFactory.getRetrofit().create(IMobileVideoApi.class)
                .getVideoArticle(this.category, time)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .switchMap((Function<MultiNewsArticleBean, Observable<MultiNewsArticleDataBean>>) multiNewsArticleBean -> {
                    List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
                    for (MultiNewsArticleBean.DataBean dataBean : multiNewsArticleBean.getData()) {
                        dataList.add(gson.fromJson(dataBean.getContent(), MultiNewsArticleDataBean.class));
                    }
                    return Observable.fromIterable(dataList);
                })
                .filter(dataBean -> {
                    time = dataBean.getBehot_time();
                    if (TextUtils.isEmpty(dataBean.getSource())) {
                        return false;
                    }
                    try {
                        // 过滤头条问答新闻
                        if (dataBean.getSource().contains("头条问答")
                                || dataBean.getTag().contains("ad")
                                || dataBean.getSource().contains("话题")) {
                            return false;
                        }
                    } catch (NullPointerException e) {
                        ErrorAction.print(e);
                    }
                    // 过滤重复新闻(与上次刷新的数据比较)
                    for (MultiNewsArticleDataBean bean : dataList) {
                        if (bean.getTitle().equals(dataBean.getTitle())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList()
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
    public void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen) {
        dataList.addAll(dataBeen);
        view.onSetAdapter(dataList);
        view.onHideLoading();
    }

    @Override
    public void doRefresh() {
        if (dataList.size() != 0) {
            dataList.clear();
            time = TimeUtil.getCurrentTimeStamp();
        }
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }
}

