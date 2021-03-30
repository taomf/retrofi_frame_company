package com.taomf.retrofit_frame.core.tool.net;

public interface MVPCallBack<T> {
    void onSuccess(T t);
    void onException(BaseModel model);
    void onError(Throwable e);

}
