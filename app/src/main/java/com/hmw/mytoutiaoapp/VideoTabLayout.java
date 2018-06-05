package com.hmw.mytoutiaoapp;

import android.support.v4.app.Fragment;

/**
 * Created by han on 2018/6/5.
 */

public class VideoTabLayout extends Fragment {

    public static VideoTabLayout getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final VideoTabLayout INSTANCE = new VideoTabLayout();
    }

    public void onDoubleClick() {

    }
}
