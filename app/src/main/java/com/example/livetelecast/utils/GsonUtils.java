package com.example.livetelecast.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 博 on 2017/8/5.
 */

public class GsonUtils {

    private static Gson mGson = new Gson() ;

    /**
     * 将json字符串转化为List
     * @param json
     * @param type
     */
    public static <T> T fromJson(String json , Type type){
        return mGson.fromJson(json ,type ) ;
    }

    public static String toJson(Object o ){
        mGson.toJson(o) ;
        return null;
    }

}
