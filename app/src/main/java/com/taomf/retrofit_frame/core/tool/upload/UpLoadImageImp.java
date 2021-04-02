package com.taomf.retrofit_frame.core.tool.upload;


import android.os.Environment;

import com.taomf.retrofit_frame.core.http.UpLoadFile;
import com.taomf.retrofit_frame.core.http.UpLoadImage;
import com.taomf.retrofit_frame.core.tool.net.ServiceGenerator;
import com.taomf.retrofit_frame.core.util.StringUtils;
import com.taomf.retrofit_frame.core.util.ToastUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    /**
     * date   : 2021/3/31 11:08
     * author : taomf
     * Desc   : 单一图片上传
     * @param url 图片路径
     */

    public void  upLoadImage(String url){
        boolean b = StringUtils.isImage(url);
        if (!b){
            ToastUtil.get().shortToast("图片格式错误");
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), url);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part part = MultipartBody.Part.create(requestFile);
        ServiceGenerator.getService(UpLoadImage.class).uploadImage(part);
    }

    public void  upLoadImageMap(List<String> stringList){
        Map<String,MultipartBody.Part> map = new HashMap<>();
        for (int i = 0; i < stringList.size() ; i++) {
            File file = new File(stringList.get(i));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
            MultipartBody.Part part = MultipartBody.Part.create(requestBody);

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.addFormDataPart("file"+i,file.getName(),requestBody);

            map.put("file"+i,part);
        }
        ServiceGenerator.getService(UpLoadImage.class).upLoadImageMap(map);
    }
    /**
     * date   : 2021/3/31 16:59
     * author : taomf
     * Desc   : 文件上传
     * @param file 文件
     * @param desc 文件描述
     */

    public void upLoadFile(File file,String desc){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestDesc = RequestBody.create(MediaType.parse("multipart/form-data"), desc);
        MultipartBody.Part part = MultipartBody.Part.create(requestBody);
        ServiceGenerator.getService(UpLoadFile.class).uploadFile(requestDesc,part);
    }

}
