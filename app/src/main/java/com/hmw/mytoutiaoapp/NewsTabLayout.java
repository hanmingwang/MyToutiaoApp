package com.hmw.mytoutiaoapp;


import android.support.v4.app.Fragment;

/**
 * Created by han on 2018/6/5.
 */

public class NewsTabLayout extends Fragment {

    public static NewsTabLayout getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final NewsTabLayout INSTANCE = new NewsTabLayout();
    }

    public void onDoubleClick() {

    }
}
