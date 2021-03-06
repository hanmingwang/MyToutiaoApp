package com.hmw.mytoutiaoapp.wenda.content;

import com.hmw.mytoutiaoapp.base.IBaseListView;
import com.hmw.mytoutiaoapp.base.IBasePresenter;
import com.hmw.mytoutiaoapp.wenda.bean.WendaContentBean;

import java.util.List;

/**
 * Created by han on 2018/6/23.
 */

public interface IWendaContent {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();

        /**
         * 设置顶部信息
         */
        void onSetHeader(WendaContentBean.QuestionBean questionBean);
    }

    interface Presenter extends IBasePresenter {

        /**
         * 设置顶部信息
         */
        void doSetHeader(WendaContentBean.QuestionBean questionBean);

        /**
         * 请求数据
         */
        void doLoadData(String qid);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<WendaContentBean.AnsListBean> list);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
