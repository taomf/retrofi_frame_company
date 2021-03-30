package com.taomf.retrofit_frame.core.tool.net;

import com.taomf.retrofit_frame.core.util.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class MyLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody requestBody  = request.body();
        String body = null;
        if(requestBody != null) {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
//            logger.info(String.format("Sending request %s on %s%n%s", request.url(),
//                    chain.connection(), request.headers()));

            Charset charset = Charset.forName("utf-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            body = buffer.readString(charset);
        }
        Logger.e("#####url==="+request.url());
        Logger.e("#####url==="+request.headers().toString());

        Logger.e(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), body,request.headers()));
//        Logger.e("#####body==="+String.format("size=%s offset=%s byteCount=%s",body));


        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        String rBody = null;

//        if(HttpEngine.hasBody(response)) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("utf-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                }
            }
            rBody = buffer.clone().readString(charset);
//        }
        return response;
    }
}
