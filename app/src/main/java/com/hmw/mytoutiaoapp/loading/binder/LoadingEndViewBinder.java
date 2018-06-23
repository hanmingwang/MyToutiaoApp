package com.hmw.mytoutiaoapp.loading.binder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hmw.mytoutiaoapp.R;
import com.hmw.mytoutiaoapp.loading.bean.LoadingEndBean;

import me.drakeet.multitype.ItemViewBinder;


/**
 * Created by han on 2018/6/7.
 */

public class LoadingEndViewBinder extends ItemViewBinder<LoadingEndBean, LoadingEndViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected LoadingEndViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loading_end, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LoadingEndBean item) {

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
