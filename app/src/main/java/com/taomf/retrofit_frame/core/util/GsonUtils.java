package com.taomf.retrofit_frame.core.util;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 说明：Gson工具类
 */
public final class GsonUtils {

    private static Gson sGson = null;

    private final static String TAG = "GsonUtils";

    static {
        if (sGson == null){
            sGson = new Gson();
        }
    }

    /**
     * 说明：禁止实例化
     */
    private GsonUtils(){}

    /**
     * 说明：将对象转成json字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        check();
        return sGson.toJson(obj);
    }

    /**
     * 说明：将json转成对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T jsonToBean(String json, Class<?> clazz){
        check();
        if (StringUtils.isEmpty(json) || clazz == null){
            return null;
        }
        return (T)sGson.fromJson(json, clazz);
    }

    /**
     * 说明：将json转成对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends Object> T jsonToBean(String json, String type){
        check();
        Class clazz = null;
        try {
            clazz = Class.forName(type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            clazz = Object.class;
        }
        return (T)jsonToBean(json, clazz);
    }

    /**
     * 说明：将json数组转list
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T toList(String json, Class<?> clazz){
        check();
        List<Object> list = new ArrayList<>();
        JSONArray array;
        try {
            array = new JSONArray(json);
            for (int i = 0;i < array.length();i++){
                list.add(jsonToBean(array.getString(i), clazz));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T)list;
    }

    /**
     * 说明：将json数组转list
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends Object> T toList(String json, String type){
        check();
        List<Object> list = new ArrayList<>();
        JSONArray array;
        try {
            array = new JSONArray(json);
            for (int i = 0;i < array.length();i++){
                list.add(jsonToBean(array.getString(i), type));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T)list;
    }

    /**
     * 说明：根据json类型转换bean
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Object> T toBean(String json, Class<?> clazz){
        check();
        if (json.trim().startsWith("[")){
            return toList(json,clazz);
        }else {
            return jsonToBean(json,clazz);
        }
    }

    /**
     * 说明：根据json类型转换bean
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T extends Object> T toBean(String json, String type){
        check();
        if (json.trim().startsWith("[")){
            return toList(json,type);
        }else {
            return jsonToBean(json,type);
        }
    }

    /**
     * 说明：json转map
     * @param json
     * @return
     */
    public static Map<?,?> jsonToMap(String json){
        check();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
        }.getType();
        Map<?,?> map  = sGson.fromJson(json, type);
        return map;
    }

    /**
     * 说明：解析json
     * @param json
     * @param key
     * @return
     */
    public static String optString(String json, String key){
        try {
            JSONObject object = new JSONObject(json);
            return object.getString(key);
        } catch (Exception e) {
            LogUtils.e(TAG,"get "+key+" error!");
        }
        return "";
    }

    /**
     * 说明：解析json
     * @param json
     * @param key
     * @return
     */
    public static int optInt(String json, String key){
        try {
            JSONObject object = new JSONObject(json);
            return object.getInt(key);
        } catch (Exception e) {
            LogUtils.e(TAG,"get "+key+" error!");
        }
        return 0;
    }

    /**
     * 说明：解析json
     * @param json
     * @param key
     * @return
     */
    public static boolean optBoolean(String json, String key){
        try {
            JSONObject object = new JSONObject(json);
            return object.getBoolean(key);
        } catch (Exception e) {
            LogUtils.e(TAG,"get "+key+" error!");
        }
        return false;
    }

    /**
     * 说明：解析json
     * @param json
     * @param key
     * @return
     */
    public static double optDouble(String json, String key){
        try {
            JSONObject object = new JSONObject(json);
            return object.getDouble(key);
        } catch (Exception e) {
            LogUtils.e(TAG,"get "+key+" error!");
        }
        return 0.0d;
    }

    /**
     * 说明：解析json
     * @param json
     * @param key
     * @return
     */
    public static long optLong(String json, String key){
        try {
            JSONObject object = new JSONObject(json);
            return object.getLong(key);
        } catch (Exception e) {
            LogUtils.e(TAG,"get "+key+" error!");
        }
        return 0L;
    }

    /**
     * 说明：解析json
     * @param json
     * @param key
     * @return
     */
    public static Object opt(String json, String key){
        try {
            JSONObject object = new JSONObject(json);
            return object.get(key);
        } catch (Exception e) {
            LogUtils.e(TAG,"get "+key+" error!");
        }
        return null;
    }

    /**
     * 说明：检查Gson是否为null
     */
    private static void check(){
        if (sGson == null){
            sGson = new Gson();
        }
    }
}