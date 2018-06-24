package com.hmw.mytoutiaoapp.video.article;

import com.hmw.mytoutiaoapp.base.IBaseListView;
import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.news.bean.MultiNewsArticleDataBean;

import java.util.List;

/**
 * Created by han on 2018/6/23.
 */

public interface IVideoArticle {

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
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);
    }
}
