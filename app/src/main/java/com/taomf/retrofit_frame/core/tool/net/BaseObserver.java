package com.taomf.retrofit_frame.core.tool.net;
import com.taomf.retrofit_frame.core.util.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 备注：
 */
public abstract class BaseObserver<T> implements Observer<BaseModel<T>> {

    private static final String TAG = "WORD_HTTP";

    protected BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(BaseModel<T> value) {
        if (BaseModel.STATE_SUCCESS == value.getCode()) {
            T t = value.getData();
            Logger.e(TAG, "onSuccess:" + t.toString());
            onSuccess(t);
        } else {
            onException(value);
        }
    }


    @Override
    public void onComplete() {
        Logger.d(TAG, "onComplete");
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(TAG, "onError:" + e.toString());
    }

    protected abstract void onSuccess(T t);

    protected void onException(BaseModel model) {
        Logger.e(TAG, "onException:" + model.toString());
    }


}
