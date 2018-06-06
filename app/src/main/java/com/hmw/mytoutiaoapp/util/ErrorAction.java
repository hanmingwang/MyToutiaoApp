package com.hmw.mytoutiaoapp.util;

import android.support.annotation.NonNull;

import com.hmw.mytoutiaoapp.BuildConfig;

import io.reactivex.functions.Consumer;

/**
 * Created by han on 2018/6/6.
 */

public class ErrorAction {

    @NonNull
    public static Consumer<Throwable> error() {
        return throwable -> {
            if (BuildConfig.DEBUG) {
                throwable.printStackTrace();
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
