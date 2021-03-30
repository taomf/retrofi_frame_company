package com.taomf.retrofit_frame.core.tool.net;

import com.taomf.retrofit_frame.core.util.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 备注：Retrofit生产者
 * */
public class ServiceGenerator {

    private static final String DOMAIN_BASE = "http://192.168.1.1";

    private static Map<String, Retrofit> mServiceMap = new HashMap();


    private ServiceGenerator() {}


    public static <T> T getService(Class<T> serviceClass) {


        return getCustomService(DOMAIN_BASE,serviceClass);
    }


    public static <T> T getCustomService(String domain, Class<T> serviceClass) {
        synchronized (ServiceGenerator.class) {
            Retrofit retrofit = mServiceMap.get(domain);
            if (retrofit == null){
                retrofit  = getRetrofit(domain);
                //只缓存最常用的
                if (DOMAIN_BASE.equals(domain)){
                    mServiceMap.put(domain,retrofit);
                }
            }
            return createServiceFrom(retrofit,serviceClass);
        }
    }


    private static <T> T createServiceFrom(Retrofit retrofit, Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private static Retrofit getRetrofit(String base_url){
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .client(getOkHttp())
                .addConverterFactory(GsonConverterFactory.create())         //返回内容的转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //请求Call的转换器
                .build();
    }

    private static OkHttpClient getOkHttp() {

        final int TIME_OUT_CONNECT = 20;
        final int TIME_OUT_READ = 20;
        final int TIME_OUT_WRITE = 20;

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_WRITE, TimeUnit.SECONDS);

        //日志拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Logger.i("WORD_HTTP","" + message);
            }
        });

        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(logging);

        return builder.build();
    }

}
