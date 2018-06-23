package com.hmw.mytoutiaoapp.interfaces;

/**
 * Created by han on 2018/6/7.
 */

public interface IOnDragVHListener {

    /**
     * Item被选中时触发
     */
    void onItemSelected();


    /**
     * Item在拖拽结束/滑动结束后触发
     */
    void onItemFinish();
}
