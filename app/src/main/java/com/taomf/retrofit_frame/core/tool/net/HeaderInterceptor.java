package com.taomf.retrofit_frame.core.tool.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 *  Header 拦截器
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //ToDo  处理业务逻辑，可以对header统一处理，涉及到header加密的也在此处理

        return chain.proceed(request);
    }
}
