package com.example.livetelecast.okhttp.request;

import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;

import java.util.concurrent.ConcurrentHashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by 博 on 2017/8/3.
 */

public class RequestParams {

    protected final static String LOG_TAG = "RequestParams";
    protected final ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();

    private Gson mGson = new Gson() ;
    /**
     * 提供方法装入请求参数
     * @param key
     * @param value
     */
    public void put (String key , String value){
        if (urlParams != null){
            urlParams.put(key , value) ;
        }
    }

    /**
     * 将map中的参数装载到getBuiler里
     * @return
     */
    public GetBuilder getGetBuilder(){

        GetBuilder getBuilder = new GetBuilder() ;
        if (urlParams != null){
            for (String key : urlParams.keySet()){
                getBuilder.addParams(key , urlParams.get(key)) ;
            }
        }

        return getBuilder ;
    }

    public PostFormBuilder getPostFormBuilder(){

        PostFormBuilder postFormBuilder = new PostFormBuilder() ;

        if (urlParams != null ){
            for (String key : urlParams.keySet()){
                Log.d(TAG, "getPostFormBuilder: ------------------------" + key + "-------------" + urlParams.get(key));
                postFormBuilder.addParams(key , urlParams.get(key)) ;
            }
        }

        return postFormBuilder ;
    }

    public PostStringBuilder getPostJsonBuilder(){
        PostStringBuilder postStringBuilder = new PostStringBuilder() ;

        if (urlParams != null){

            postStringBuilder.content(mGson.toJson(urlParams)) ;
        }
        return postStringBuilder ;
    }

    public boolean has(String key){

        for (String key1 : urlParams.keySet()){
            if (key.equals(key1)){
                return true ;
            }
        }
        return false ;
    }

}
