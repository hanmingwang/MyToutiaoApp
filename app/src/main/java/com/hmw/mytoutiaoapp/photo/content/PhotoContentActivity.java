package com.hmw.mytoutiaoapp.photo.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hmw.mytoutiaoapp.InitApp;
import com.hmw.mytoutiaoapp.R;
import com.hmw.mytoutiaoapp.base.BaseActivity;
import com.hmw.mytoutiaoapp.photo.bean.PhotoArticleBean;
import com.r0adkll.slidr.model.SlidrInterface;

/**
 * Created by han on 2018/6/23.
 */

public class PhotoContentActivity extends BaseActivity{

    private static final String TAG = "PhotoContentActivity";

    public static void launch(PhotoArticleBean.DataBean bean) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, PhotoContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, PhotoContentFragment.newInstance(getIntent().getParcelableExtra(TAG)))
                .commit();
    }

    public SlidrInterface getSlidrInterface() {
        return slidrInterface;
    }
}
