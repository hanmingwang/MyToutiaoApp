package com.hmw.mytoutiaoapp.wenda.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hmw.mytoutiaoapp.InitApp;
import com.hmw.mytoutiaoapp.R;
import com.hmw.mytoutiaoapp.base.BaseActivity;
import com.hmw.mytoutiaoapp.wenda.bean.WendaContentBean;

/**
 * Created by han on 2018/6/23.
 */

public class WendaDetailActivity extends BaseActivity {

    private static final String TAG = "WendaDetailActivity";

    public static void launch(WendaContentBean.AnsListBean bean) {
        InitApp.AppContext.startActivity(new Intent(InitApp.AppContext, WendaDetailActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, WendaDetailFragment.newInstance(getIntent().getParcelableExtra(TAG)))
                .commit();
    }
}
