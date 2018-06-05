package com.hmw.mytoutiaoapp.base;

/**
 * Created by han on 2018/6/4.
 */

public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();
}
