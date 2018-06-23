package com.hmw.mytoutiaoapp.search.result;

import android.os.Bundle;
import android.view.View;

import com.hmw.mytoutiaoapp.Register;
import com.hmw.mytoutiaoapp.base.BaseListFragment;
import com.hmw.mytoutiaoapp.loading.bean.LoadingBean;
import com.hmw.mytoutiaoapp.loading.OnLoadMoreListener;
import com.hmw.mytoutiaoapp.util.DiffCallback;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by han on 2018/6/6.
 */

public class SearchResultFragment extends BaseListFragment<ISearchResult.Presenter> implements ISearchResult.View {

    private String query;
    private String curTab;

    public static SearchResultFragment newInstance(String query, String curTab) {
        Bundle args = new Bundle();
        args.putString("queryAll", query);
        args.putString("curTab", curTab);
        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() throws NullPointerException {
        query = getArguments().getString("queryAll");
        curTab = getArguments().getString("curTab");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerSearchItem(adapter);
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
    public void onRefresh() {
        recyclerView.smoothScrollToPosition(0);
        presenter.doRefresh();
    }

    @Override
    public void setPresenter(ISearchResult.Presenter presenter) {
        if (presenter == null) {
            this.presenter = new SearchResultPresenter(this);
        }
    }

    @Override
    public void onSetAdapter(List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        recyclerView.stopScroll();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData(query, curTab);
    }

    @Override
    public void fetchData() {
        onLoadData();
    }

}
