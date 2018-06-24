package com.hmw.mytoutiaoapp.video.content;

import com.hmw.mytoutiaoapp.news.comment.INewsComment;

/**
 * Created by han on 2018/6/23.
 */

public interface IVideoContent {

    interface View extends INewsComment.View {

        /**
         * 设置播放器
         */
        void onSetVideoPlay(String url);
    }

    interface Presenter extends INewsComment.Presenter {

        /**
         * 请求数据
         */
        void doLoadVideoData(String videoid);
    }
}
