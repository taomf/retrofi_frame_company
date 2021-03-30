package com.taomf.retrofit_frame.core.base;


import com.google.gson.Gson;

import java.lang.ref.WeakReference;

public class BasePresenter<V> implements IPresenter<V>{

    protected Gson mGson ;

    {
        mGson = new Gson();
    }



    private WeakReference<V> viewRef;

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference(view);
    }

    protected V getView() {
        return viewRef == null ? null : viewRef.get();
    }


    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

}