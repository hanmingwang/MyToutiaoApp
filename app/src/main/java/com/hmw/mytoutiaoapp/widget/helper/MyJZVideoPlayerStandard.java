package com.hmw.mytoutiaoapp.widget.helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.hmw.mytoutiaoapp.R;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by han on 2018/6/23.
 */

public class MyJZVideoPlayerStandard extends JZVideoPlayerStandard {

    public static onClickFullScreenListener onClickFullScreenListener;

    public MyJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static void setOnClickFullScreenListener(onClickFullScreenListener listener) {
        onClickFullScreenListener = listener;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.fullscreen) {
            if (onClickFullScreenListener != null) {
                onClickFullScreenListener.onClickFullScreen();
            }
        }
    }

    public interface onClickFullScreenListener {
        void onClickFullScreen();
    }
}

