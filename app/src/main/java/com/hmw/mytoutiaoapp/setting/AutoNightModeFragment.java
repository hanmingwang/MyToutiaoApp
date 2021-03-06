package com.hmw.mytoutiaoapp.setting;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.hmw.mytoutiaoapp.R;
import com.hmw.mytoutiaoapp.util.SettingUtil;

/**
 * Created by han on 2018/6/25.
 */

public class AutoNightModeFragment extends PreferenceFragment {

    private SettingUtil settingUtil = SettingUtil.getInstance();
    private String nightStartHour;
    private String nightStartMinute;
    private String dayStartHour;
    private String dayStartMinute;

    private Preference autoNight;
    private Preference autoDay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_autonight);
        setHasOptionsMenu(true);

        autoNight = findPreference("auto_night");
        autoDay = findPreference("auto_day");

        setText();

        autoNight.setOnPreferenceClickListener(preference -> {
            TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                    (timePicker, hour, minute) -> {
                        settingUtil.setNightStartHour(hour > 9 ? "" + hour : "0" + hour);
                        settingUtil.setNightStartMinute(minute > 9 ? "" + minute : "0" + minute);
                        setText();
                    }, Integer.parseInt(nightStartHour), Integer.parseInt(nightStartMinute), true);
            dialog.show();
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(R.string.done);
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(R.string.cancel);
            return false;
        });
        autoDay.setOnPreferenceClickListener(preference -> {
            TimePickerDialog dialog = new TimePickerDialog(getActivity(),
                    (timePicker, hour, minute) -> {
                        settingUtil.setDayStartHour(hour > 9 ? "" + hour : "0" + hour);
                        settingUtil.setDayStartMinute(minute > 9 ? "" + minute : "0" + minute);
                        setText();
                    }, Integer.parseInt(dayStartHour), Integer.parseInt(dayStartMinute), true);
            dialog.show();
            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setText(R.string.done);
            dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setText(R.string.cancel);
            return false;
        });
    }

    private void setText() {
        nightStartHour = settingUtil.getNightStartHour();
        nightStartMinute = settingUtil.getNightStartMinute();
        dayStartHour = settingUtil.getDayStartHour();
        dayStartMinute = settingUtil.getDayStartMinute();

        autoNight.setSummary(nightStartHour + ":" + nightStartMinute);
        autoDay.setSummary(dayStartHour + ":" + dayStartMinute);
    }
}


