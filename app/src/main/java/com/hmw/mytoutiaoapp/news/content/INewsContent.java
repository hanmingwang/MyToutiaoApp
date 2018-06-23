package com.hmw.mytoutiaoapp.news.content;


import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.base.IBaseView;
import com.hmw.mytoutiaoapp.news.bean.MultiNewsArticleDataBean;

/**
 * Created by han on 2018/6/8.
 */

interface INewsContent {

    interface View extends IBaseView<Presenter> {

        /**
         * 加载网页
         */
        void onSetWebView(String url, boolean flag);
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(MultiNewsArticleDataBean dataBean);
    }
}
