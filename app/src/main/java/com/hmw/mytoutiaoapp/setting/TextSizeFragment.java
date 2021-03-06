package com.hmw.mytoutiaoapp.setting;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hmw.mytoutiaoapp.R;
import com.hmw.mytoutiaoapp.base.BaseListFragment;
import com.hmw.mytoutiaoapp.util.RxBus;
import com.hmw.mytoutiaoapp.util.SettingUtil;
import com.jaygoo.widget.RangeSeekBar;

import java.text.DecimalFormat;

/**
 * Created by han on 2018/6/25.
 */

public class TextSizeFragment extends Fragment {

    private RangeSeekBar seekbar;
    private TextView text;
    private DecimalFormat df = new DecimalFormat("0");
    private int currentSize = -1;
    private SettingUtil settingUtil = SettingUtil.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_textsize, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        seekbar = view.findViewById(R.id.seekbar);
        text = view.findViewById(R.id.text);
        text.setTextSize(settingUtil.getTextSize());
        seekbar.setValue(settingUtil.getTextSize() - 14);
        seekbar.setLineColor(0, settingUtil.getColor());
        seekbar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, final float min, float max, boolean isFromUser) {
                if (isFromUser) {
                    int size = Integer.parseInt(df.format(min));
                    if (currentSize != size) {
                        setText(size);
                        currentSize = size;
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }

    private void setText(int size) {
        // 最小 14sp
        size = 14 + size;
        text.setTextSize(size);
        settingUtil.setTextSize(size);
        RxBus.getInstance().post(BaseListFragment.TAG, size);
    }
}

