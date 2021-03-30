package com.taomf.retrofit_frame.core.base;

public interface IPresenter<V> {
    void attachView(V view);
    void detachView(boolean retainInstance);
}
