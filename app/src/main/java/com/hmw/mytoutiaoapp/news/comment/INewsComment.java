package com.hmw.mytoutiaoapp.news.comment;


import com.hmw.mytoutiaoapp.base.IBaseListView;
import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.news.bean.NewsCommentBean;

import java.util.List;

/**
 * Created by han on 2018/6/7.
 */

public interface INewsComment {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... groupId_ItemId);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

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
