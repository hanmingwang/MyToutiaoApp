package com.hmw.mytoutiaoapp.media;

import android.content.Intent;

import com.hmw.mytoutiaoapp.InitApp;
import com.hmw.mytoutiaoapp.base.BaseActivity;

/**
 * Created by han on 2018/6/7.
 */

public class MediaHomeActivity extends BaseActivity {

    private static final String ARG_MEDIAID = "mediaId";

    public static void launch(String MediaId) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, MediaHomeActivity.class)
                .putExtra(ARG_MEDIAID, MediaId)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
