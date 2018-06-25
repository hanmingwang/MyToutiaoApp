package com.hmw.mytoutiaoapp.media.home;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.hmw.mytoutiaoapp.Register;
import com.hmw.mytoutiaoapp.base.BaseListFragment;
import com.hmw.mytoutiaoapp.loading.OnLoadMoreListener;
import com.hmw.mytoutiaoapp.loading.bean.LoadingBean;
import com.hmw.mytoutiaoapp.media.bean.MediaProfileBean;
import com.hmw.mytoutiaoapp.util.DiffCallback;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.hmw.mytoutiaoapp.media.home.MediaTabPresenter.TYPE_ARTICLE;

/**
 * Created by han on 2018/6/25.
 */

public class MediaArticleFragment extends BaseListFragment<IMediaProfile.Presenter> implements IMediaProfile.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "MediaArticleFragment";
    private MediaProfileBean.DataBean dataBean = null;

    public static MediaArticleFragment newInstance(Parcelable parcelable) {
        Bundle args = new Bundle();
        args.putParcelable(TAG, parcelable);
        MediaArticleFragment fragment = new MediaArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(IMediaProfile.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new MediaTabPresenter(this);
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerMediaArticleItem(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData(TYPE_ARTICLE);
                }
            }
        });
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        this.dataBean = bundle.getParcelable(TAG);
        if (null == dataBean) {
            onShowNetError();
        }
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items();
        newItems.add(dataBean);
        newItems.addAll(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        recyclerView.stopScroll();
    }

    @Override
    public void fetchData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        presenter.doLoadArticle(dataBean.getMedia_id());
    }

    @Override
    public void onRefresh() {
        onShowLoading();
        presenter.doRefresh(TYPE_ARTICLE);
    }
}
