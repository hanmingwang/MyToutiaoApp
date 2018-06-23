package com.hmw.mytoutiaoapp.wenda.article;

import android.view.View;

import com.hmw.mytoutiaoapp.Register;
import com.hmw.mytoutiaoapp.base.BaseListFragment;
import com.hmw.mytoutiaoapp.loading.OnLoadMoreListener;
import com.hmw.mytoutiaoapp.loading.bean.LoadingBean;
import com.hmw.mytoutiaoapp.util.DiffCallback;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by han on 2018/6/7.
 */

public class WendaArticleView extends BaseListFragment<IWendaArticle.Presenter> implements IWendaArticle.View {

    public static WendaArticleView newInstance() {
        return new WendaArticleView();
    }

    @Override
    public void setPresenter(IWendaArticle.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new WendaArticlePresenter(this);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        Register.registerWendaArticleItem(adapter);
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
        presenter.doLoadData();
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onLoadData();
    }

}
