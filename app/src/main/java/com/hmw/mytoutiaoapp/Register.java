package com.hmw.mytoutiaoapp;

import android.support.annotation.NonNull;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by han on 2018/6/6.
 */

public class Register {
    public static void registerSearchItem(@NonNull MultiTypeAdapter adapter) {
//        adapter.register(MultiNewsArticleDataBean.class)
//                .to(new NewsArticleImgViewBinder(),
//                        new SearchArticleVideoViewBinder(),
//                        new NewsArticleTextViewBinder())
//                .withClassLinker((position, item) -> {
//                    if (item.isHas_video()) {
//                        return SearchArticleVideoViewBinder.class;
//                    }
//                    if (null != item.getImage_list() && item.getImage_list().size() > 0) {
//                        return NewsArticleImgViewBinder.class;
//                    }
//                    return NewsArticleTextViewBinder.class;
//                });
//        adapter.register(LoadingBean.class, new LoadingViewBinder());
//        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
