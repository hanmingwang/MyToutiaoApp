package com.hmw.mytoutiaoapp;

import android.support.annotation.NonNull;

import com.hmw.mytoutiaoapp.loading.bean.LoadingBean;
import com.hmw.mytoutiaoapp.loading.bean.LoadingEndBean;
import com.hmw.mytoutiaoapp.loading.binder.LoadingEndViewBinder;
import com.hmw.mytoutiaoapp.loading.binder.LoadingViewBinder;
import com.hmw.mytoutiaoapp.news.bean.MultiNewsArticleDataBean;
import com.hmw.mytoutiaoapp.news.bean.NewsCommentBean;
import com.hmw.mytoutiaoapp.news.binder.NewsArticleImgViewBinder;
import com.hmw.mytoutiaoapp.news.binder.NewsArticleTextViewBinder;
import com.hmw.mytoutiaoapp.news.binder.NewsArticleVideoViewBinder;
import com.hmw.mytoutiaoapp.news.binder.NewsCommentViewBinder;
import com.hmw.mytoutiaoapp.search.binder.SearchArticleVideoViewBinder;
import com.hmw.mytoutiaoapp.wenda.bean.WendaArticleDataBean;
import com.hmw.mytoutiaoapp.wenda.bean.WendaContentBean;
import com.hmw.mytoutiaoapp.wenda.binder.WendaArticleOneImgViewBinder;
import com.hmw.mytoutiaoapp.wenda.binder.WendaArticleTextViewBinder;
import com.hmw.mytoutiaoapp.wenda.binder.WendaArticleThreeImgViewBinder;
import com.hmw.mytoutiaoapp.wenda.binder.WendaContentHeaderViewBinder;
import com.hmw.mytoutiaoapp.wenda.binder.WendaContentViewBinder;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by han on 2018/6/6.
 */

public class Register {

    public static void registerNewsArticleItem(@NonNull MultiTypeAdapter adapter) {
        // 一个类型对应多个 ItemViewBinder
        adapter.register(MultiNewsArticleDataBean.class)
                .to(new NewsArticleImgViewBinder(),
                        new NewsArticleVideoViewBinder(),
                        new NewsArticleTextViewBinder())
                .withClassLinker((position, item) -> {
                    if (item.isHas_video()) {
                        return NewsArticleVideoViewBinder.class;
                    }
                    if (null != item.getImage_list() && item.getImage_list().size() > 0) {
                        return NewsArticleImgViewBinder.class;
                    }
                    return NewsArticleTextViewBinder.class;
                });
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerNewsCommentItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(NewsCommentBean.DataBean.CommentBean.class, new NewsCommentViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerWendaArticleItem(@NonNull MultiTypeAdapter adapter) {
        // 一个类型对应多个 ItemViewBinder
        adapter.register(WendaArticleDataBean.class)
                .to(new WendaArticleTextViewBinder(),
                        new WendaArticleOneImgViewBinder(),
                        new WendaArticleThreeImgViewBinder())
                .withClassLinker((position, item) -> {
                    if (null != item.getExtraBean().getWenda_image() &&
                            null != item.getExtraBean().getWenda_image().getThree_image_list() &&
                            item.getExtraBean().getWenda_image().getThree_image_list().size() > 0) {
                        return WendaArticleThreeImgViewBinder.class;
                    }
                    if (null != item.getExtraBean().getWenda_image() &&
                            null != item.getExtraBean().getWenda_image().getLarge_image_list() &&
                            item.getExtraBean().getWenda_image().getLarge_image_list().size() > 0) {
                        return WendaArticleOneImgViewBinder.class;
                    }
                    return WendaArticleTextViewBinder.class;
                });
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerWendaContentItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(WendaContentBean.QuestionBean.class, new WendaContentHeaderViewBinder());
        adapter.register(WendaContentBean.AnsListBean.class, new WendaContentViewBinder());
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

    public static void registerSearchItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(MultiNewsArticleDataBean.class)
                .to(new NewsArticleImgViewBinder(),
                        new SearchArticleVideoViewBinder(),
                        new NewsArticleTextViewBinder())
                .withClassLinker((position, item) -> {
                    if (item.isHas_video()) {
                        return SearchArticleVideoViewBinder.class;
                    }
                    if (null != item.getImage_list() && item.getImage_list().size() > 0) {
                        return NewsArticleImgViewBinder.class;
                    }
                    return NewsArticleTextViewBinder.class;
                });
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
