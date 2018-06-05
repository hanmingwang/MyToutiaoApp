package com.hmw.mytoutiaoapp;

import android.support.v4.app.Fragment;

/**
 * Created by han on 2018/6/5.
 */

public class MediaChannelView extends Fragment {

    public static MediaChannelView getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final MediaChannelView INSTANCE = new MediaChannelView();
    }
}
