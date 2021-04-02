package com.taomf.retrofit_frame.core.tool.upload;

import com.taomf.retrofit_frame.core.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * author : taomf
 * Date   : 2021/3/31 14:04
 * Desc   : 文件下载
 */
public class DownLoadFile {

    /**
     * date   : 2021/3/31 13:37
     * author : taomf
     * Desc   : 文件下载
     * @param response 返回体
     * @param target 文件路径
     * @return
     * @throws IOException
     */

    public String saveFile(Response response, File target) throws IOException {
        InputStream is = null;
        int len = 0;
        byte[] buf = new byte[1024*3];
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            long total = response.body().contentLength();
            long sum = 0;
            FileUtils.mkdirs(target.getParentFile());
            fos = new FileOutputStream(target);
            while ((len = is.read(buf)) != -1){
                sum += len;
                fos.write(buf,0,len);
                //  下载进度
              /*  if (callBack != null){
                    publishProgress(sum,total);
                }*/
            }
            fos.flush();
            return target.getAbsolutePath();
        }finally {
            FileUtils.closeIO(is,fos);
        }
    }
}
