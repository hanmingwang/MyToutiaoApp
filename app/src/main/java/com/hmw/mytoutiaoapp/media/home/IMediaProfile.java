package com.hmw.mytoutiaoapp.media.home;

import com.hmw.mytoutiaoapp.base.IBaseListView;
import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.media.bean.MediaWendaBean;
import com.hmw.mytoutiaoapp.media.bean.MultiMediaArticleBean;

import java.util.List;

/**
 * Created by han on 2018/6/25.
 */

public interface IMediaProfile {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadArticle(String... mediaId);

        void doLoadVideo(String... mediaId);

        void doLoadWenda(String... mediaId);

        /**
         * 再起请求数据
         */
        void doLoadMoreData(int type);

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiMediaArticleBean.DataBean> list);

        void doSetWendaAdapter(List<MediaWendaBean.AnswerQuestionBean> list);

        void doRefresh(int type);

        void doShowNoMore();
    }
}
