package com.hmw.mytoutiaoapp.media.binder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hmw.mytoutiaoapp.R;
import com.hmw.mytoutiaoapp.interfaces.IOnItemLongClickListener;
import com.hmw.mytoutiaoapp.media.home.MediaHomeActivity;
import com.hmw.mytoutiaoapp.media.bean.MediaChannelBean;
import com.hmw.mytoutiaoapp.util.ErrorAction;
import com.hmw.mytoutiaoapp.util.ImageLoader;
import com.hmw.mytoutiaoapp.widget.CircleImageView;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by han on 2018/6/25.
 */

public class MediaChannelViewBinder extends ItemViewBinder<MediaChannelBean, MediaChannelViewBinder.ViewHolder> {

    private IOnItemLongClickListener listener;

    public MediaChannelViewBinder(IOnItemLongClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    protected MediaChannelViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_media_channel, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final MediaChannelBean item) {
        try {
            final Context context = holder.itemView.getContext();
            String url = item.getAvatar();
            ImageLoader.loadCenterCrop(context, url, holder.cv_avatar, R.color.viewBackground);
            holder.tv_mediaName.setText(item.getName());
            holder.tv_descText.setText(item.getDescText());

            RxView.clicks(holder.itemView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(o -> MediaHomeActivity.launch(item.getId()));
        } catch (Exception e) {
            ErrorAction.print(e);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private CircleImageView cv_avatar;
        private TextView tv_mediaName;
        private TextView tv_followCount;
        private TextView tv_descText;
        private IOnItemLongClickListener listener;

        public ViewHolder(View itemView, IOnItemLongClickListener listener) {
            super(itemView);
            this.cv_avatar = itemView.findViewById(R.id.cv_avatar);
            this.tv_mediaName = itemView.findViewById(R.id.tv_mediaName);
            this.tv_followCount = itemView.findViewById(R.id.tv_followCount);
            this.tv_descText = itemView.findViewById(R.id.tv_descText);
            this.listener = listener;
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                listener.onLongClick(v, getLayoutPosition());
                return true;
            }
            return false;
        }
    }
}

