package com.taomf.retrofit_frame.core.tool.image;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

public interface ImageLoaderInterface {

    interface ImgLoader{

        void display(Context context, ImageView imageView, String url);

        void display(Context context, ImageView imageView, String url, @DrawableRes int loadingResId, @DrawableRes int failResId);

        void display(Context context, ImageView imageView, String url, @DrawableRes int loadingResId, @DrawableRes int failResId, ImgListener listener);

    }

    interface ImgListener{
        void onSuccess(View view, String path);
        void onFailed(View view, String path);
    }



}
