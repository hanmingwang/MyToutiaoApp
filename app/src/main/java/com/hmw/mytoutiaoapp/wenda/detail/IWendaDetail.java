package com.hmw.mytoutiaoapp.wenda.detail;

import com.hmw.mytoutiaoapp.base.IBaseListView;
import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.news.bean.NewsCommentBean;

import java.util.List;

/**
 * Created by han on 2018/6/23.
 */

public interface IWendaDetail {

    interface View extends IBaseListView<Presenter> {

        /**
         * 加载网页
         */
        void onSetWebView(String baseUrl, boolean flag);

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String url);

        /**
         * 加载评论
         */
        void doLoadComment(String... ansId);

        /**
         * 加载更多评论
         */
        void doLoadMoreComment();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<NewsCommentBean.DataBean.CommentBean> list);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}

