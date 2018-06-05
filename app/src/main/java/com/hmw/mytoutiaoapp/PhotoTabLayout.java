package com.hmw.mytoutiaoapp;

import android.support.v4.app.Fragment;

/**
 * Created by han on 2018/6/5.
 */

public class PhotoTabLayout extends Fragment {

    public static PhotoTabLayout getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final PhotoTabLayout INSTANCE = new PhotoTabLayout();
    }

    public void onDoubleClick() {

    }
}
