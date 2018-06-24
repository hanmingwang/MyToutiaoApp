package com.hmw.mytoutiaoapp.video.article;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.hmw.mytoutiaoapp.Register;
import com.hmw.mytoutiaoapp.base.BaseListFragment;
import com.hmw.mytoutiaoapp.loading.OnLoadMoreListener;
import com.hmw.mytoutiaoapp.loading.bean.LoadingBean;
import com.hmw.mytoutiaoapp.news.article.INewsArticle;
import com.hmw.mytoutiaoapp.news.article.NewsArticlePresenter;
import com.hmw.mytoutiaoapp.util.DiffCallback;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by han on 2018/6/23.
 */

public class VideoArticleView extends BaseListFragment<INewsArticle.Presenter> implements INewsArticle.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "VideoArticleView";
    private String categoryId;

    public static VideoArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        VideoArticleView videoArticleView = new VideoArticleView();
        videoArticleView.setArguments(bundle);
        return videoArticleView;
    }

    @Override
    protected void initData() {
        categoryId = getArguments().getString(TAG);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerVideoArticleItem(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(categoryId);
    }

    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        recyclerView.stopScroll();
    }

    /**
     * API 跟新闻的一样 所以采用新闻的 presenter
     *
     * @param presenter
     */
    @Override
    public void setPresenter(INewsArticle.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new NewsArticlePresenter(this);
        }
    }
}

