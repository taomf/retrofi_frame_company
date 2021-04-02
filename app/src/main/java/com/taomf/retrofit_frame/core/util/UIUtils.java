package com.taomf.retrofit_frame.core.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 说明：UI工具类
 */
public final class UIUtils {

    /**
     * 说明：禁止实例化
     */
    private UIUtils(){}

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        Resources r = FastFrame.getApplication().getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, r.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = FastFrame.getApplication().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp(float pxValue) {
        float fontScale = FastFrame.getApplication().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     */
    public static int sp2px(float spValue) {
        float fontScale = FastFrame.getApplication().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取资源
     * @return
     */
    private static Resources getResource() {
        return FastFrame.getApplication().getResources();
    }

    /**
     * 返回String数组
     * @param id  资源id
     * @return
     */
    public static String[] getStringArray(int id) {
        return getResource().getStringArray(id);
    }

    /**
     * 说明：获取字符串资源
     * @param id
     * @return
     */
    public static String getString(int id) {
        return getResource().getString(id);
    }
    /**
     * 说明：获取颜色资源
     * @param id
     * @return
     */
    public static int getColor(int id) {
        return getResource().getColor(id);
    }
    /**
     * 说明：获取屏幕的宽度
     * @return
     */
    public static int screenWidth(){
        WindowManager wm = (WindowManager) FastFrame.getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    /**
     * 说明：获取屏幕的高度
     * @return
     */
    public static int screenHeight(){
        WindowManager wm = (WindowManager) FastFrame.getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
    /**
     * 说明：获取屏幕的密度
     * @return
     */
    public static float density(){
        WindowManager wm = (WindowManager) FastFrame.getApplication().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }

    /**
     * 说明：获取状态栏的高度
     * @return
     */
    public static int getStatusBarHeight(){
        int height = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int x = NumberUtils.toInt(field.get(object).toString());
            height = getResource().getDimensionPixelSize(x);
        }catch (Exception e){
            LogUtils.e(e);
        }
        return height;
    }

    /**
     * 说明：获取布局文件
     * @param id
     * @param group
     * @param flag
     * @return
     */
    public static View inflate(int id, ViewGroup group, boolean flag){
        return LayoutInflater.from(FastFrame.getApplication()).inflate(id, group, flag);
    }
    /**
     * 说明：获取布局文件
     * @param id
     * @return
     */
    public static View inflate(int id){
        return LayoutInflater.from(FastFrame.getApplication()).inflate(id, null, false);
    }
}


