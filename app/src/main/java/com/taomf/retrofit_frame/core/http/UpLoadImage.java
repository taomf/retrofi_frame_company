package com.taomf.retrofit_frame.core.http;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * author : taomf
 * Date   : 2021/3/30/15:05
 * Desc   : 图片上传
 */
public interface UpLoadImage {


    @Multipart
    @POST
    public void uploadImage(@Part("desc") RequestBody desc, @Part MultipartBody.Part file);


    @Multipart
    @POST
    public void upLoadImageMap(@Part("desc") RequestBody desc, @Part MultipartBody.Part file);
}
