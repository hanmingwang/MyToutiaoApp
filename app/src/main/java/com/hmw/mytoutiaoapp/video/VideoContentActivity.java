package com.hmw.mytoutiaoapp.video;

import android.content.Intent;

import com.hmw.mytoutiaoapp.InitApp;
import com.hmw.mytoutiaoapp.base.BaseActivity;
import com.hmw.mytoutiaoapp.news.bean.MultiNewsArticleDataBean;

/**
 * Created by han on 2018/6/7.
 */

public class VideoContentActivity extends BaseActivity {

    public static final String TAG = "VideoContentActivity";

    public static void launch(MultiNewsArticleDataBean bean) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, VideoContentActivity.class)
                .putExtra(VideoContentActivity.TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
