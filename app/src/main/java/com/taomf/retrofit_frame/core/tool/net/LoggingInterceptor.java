package com.taomf.retrofit_frame.core.tool.net;


import com.taomf.retrofit_frame.core.util.Logger;

import java.io.IOException;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 日志打印拦截器
 */
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {


        //这个chain里面包含了request和response，所以你要什么都可以从这里拿
        //=========发送===========
        Request request = chain.request();
        long requestTime = System.currentTimeMillis();//请求发起的时间
        HttpUrl requestUrl=request.url();
        Connection requestConnection=chain.connection();
        Headers requestHeaders=request.headers();
        //打印发送信息
        Logger.e("===LoggingInterceptor===发送==requestUrl="+requestUrl);
        Logger.e("===LoggingInterceptor===发送==requestConnection="+requestConnection);
        Logger.e("===LoggingInterceptor===发送==requestbody="+request.body().toString());
        Logger.e("===LoggingInterceptor===发送==requestHeaders="+requestHeaders);


        //=========接收===========
        long responseTime = System.currentTimeMillis();//收到响应的时间
        Response response = chain.proceed(chain.request());
//        ResponseBody responseBody = response.peekBody(1024 * 1024);
//        HttpUrl responseUrl=response.request().url();
//        String content = response.body().string();
//        Headers responseHeaders=response.headers();

        ResponseBody responseBody = response.peekBody(response.body().contentLength());//这里改为这个会好一点，不然超过1K的会被截断
        HttpUrl responseUrl=response.request().url();
//        String content = response.body().string();//这里不能这么写，会造成后面的返回错误，因为response.body().string()后，输出流被关闭了，建议改为上面的responseBody.string()
        String content = responseBody.string();
        Headers responseHeaders=response.headers();


        long delayTime=responseTime-requestTime;
        //打印接收信息
        Logger.e("=====LoggingInterceptor===接收==responseUrl="+responseUrl);
        Logger.e("=====LoggingInterceptor===接收==responseHeaders="+responseHeaders);
        Logger.e("=====LoggingInterceptor===接收==delayTime="+delayTime);
        Logger.e("=====LoggingInterceptor===接收==content="+content);


        return response;
    }
}
