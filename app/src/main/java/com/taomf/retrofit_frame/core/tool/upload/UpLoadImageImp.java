package com.taomf.retrofit_frame.core.tool.upload;


import android.os.Environment;

import com.taomf.retrofit_frame.core.http.UpLoadImage;
import com.taomf.retrofit_frame.core.tool.net.ServiceGenerator;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

/**
 * author : taomf
 * Date   : 2021/3/30 16:12
 * Desc   : 图片上传
 */
public class UpLoadImageImp {



    public void  upLoadImage(String url){
        File file = new File(Environment.getExternalStorageDirectory(), url);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
//        ServiceGenerator.getService(UpLoadImage.class).uploadImage(requestFile,part);
    }

    public void  upLoadImageMap(){
//        ServiceGenerator.getService(UpLoadImage.class).upLoadImageMap();
    }
}
