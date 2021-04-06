package com.taomf.retrofit_frame.core.tool.upload;

import com.taomf.retrofit_frame.core.util.FastFrame;
import com.taomf.retrofit_frame.core.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Response;
import okhttp3.ResponseBody;

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

    /**
     * 下载到本地
     *
     * @param body 内容
     * @return 成功或者失败
     */
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            //判断文件夹是否存在
            File files = new File(FastFrame.getApplication().getPackageName());//跟目录一个文件夹
            if (!files.exists()) {
                //不存在就创建出来
                files.mkdirs();
            }
            //创建一个文件
            File futureStudioIconFile = new File(FastFrame.getApplication().getFilesDir() + "download.jpg");
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;

            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(futureStudioIconFile);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;

                }
                //刷新
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }

                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
