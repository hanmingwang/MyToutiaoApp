package com.hmw.mytoutiaoapp.interfaces;

import android.view.View;

/**
 * Created by han on 2018/6/25.
 */

public interface IOnItemLongClickListener {
    /**
     * RecyclerView Item长按事件
     */
    void onLongClick(View view, int position);
}
