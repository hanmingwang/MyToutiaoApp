package com.hmw.mytoutiaoapp.wenda.article;


import com.hmw.mytoutiaoapp.base.IBaseListView;
import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.wenda.bean.WendaArticleDataBean;

import java.util.List;

/**
 * Created by han on 2018/6/22.
 */

public interface IWendaArticle {

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
        void doLoadData();

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<WendaArticleDataBean> list);
    }
}
