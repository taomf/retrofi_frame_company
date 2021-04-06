package com.taomf.retrofit_frame.core.http;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * author : taomf
 * Date   : 2021/3/30/15:05
 * Desc   : 文件上传
 */
public interface UpLoadFile {
   /**
    * date   : 2021/3/30/15:09
    * author : taomf
    * Desc   : 上传文件
    */
    @Multipart
    @POST
    public void uploadFile(@Part("desc") RequestBody desc, @Part MultipartBody.Part file);

    /**
     * date   : 2021/3/30/15:09
     * author : taomf
     * Desc   : 上传多个文件
     */
    @Multipart
    @POST
    public void uploadFileMap(@Part("desc") RequestBody desc, @PartMap Map<String,MultipartBody.Part> file);

    //这个也支持多文件上传
    @Multipart
    @POST
    public void uploadFileMap(@Part MultipartBody body);
}
