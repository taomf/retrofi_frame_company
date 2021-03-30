package com.taomf.retrofit_frame.core.tool.image;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import androidx.annotation.DrawableRes;


public class PicassoImageLoader implements ImageLoaderInterface.ImgLoader {

    @Override
    public void display(Context context, ImageView imageView, String url) {
        this.display(context,imageView,url,0,0);
    }



    @Override
    public void display(Context context, ImageView imageView, String url, @DrawableRes int loadingResId, @DrawableRes int failResId) {
        this.display(context,imageView,url,loadingResId,failResId,null);
    }

    @Override
    public void display(Context context, final ImageView imageView, final String url, @DrawableRes int loadingResId, @DrawableRes int failResId, final ImageLoaderInterface.ImgListener listener) {

        String str_url = url;
        if (TextUtils.isEmpty(url)){
            str_url = "error";
        }

        RequestCreator creator = Picasso.get().load(str_url);

        if (loadingResId != 0){
            creator.placeholder(loadingResId);
        }
        if (failResId != 0){
            creator.error(failResId);
        }

        creator.into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                if (listener!=null) listener.onSuccess(imageView,url);
            }

            @Override
            public void onError(Exception e) {
                if (listener!=null) listener.onFailed(imageView,url);
            }
        });

    }
}
