package com.taomf.retrofit_frame.core.tool.image;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;


public class ImageLoader implements ImageLoaderInterface.ImgLoader {

    private final ImageLoaderInterface.ImgLoader loader;

    private ImageLoader() {
        loader = new PicassoImageLoader();
    }

    public static ImageLoader get() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final ImageLoader INSTANCE = new ImageLoader();
    }


    @Override
    public void display(Context context, ImageView imageView, String url) {
        loader.display(context, imageView, url);
    }


    @Override
    public void display(Context context, ImageView imageView, String url, @DrawableRes int loadingResId, @DrawableRes int failResId) {
        loader.display(context, imageView, url, loadingResId, failResId);
    }

    @Override
    public void display(Context context, ImageView imageView, String url, @DrawableRes int loadingResId, @DrawableRes int failResId, ImageLoaderInterface.ImgListener listener) {
        loader.display(context, imageView, url, loadingResId, failResId, listener);
    }
}
