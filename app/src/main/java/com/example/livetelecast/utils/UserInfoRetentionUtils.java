package com.example.livetelecast.utils;

import android.content.Context;

import com.example.livetelecast.okhttp.data.UserInfo;
import com.example.livetelecast.utils.ASimpleCacheUtils.ACache;
import com.google.gson.reflect.TypeToken;

/**
 * Created by 博 on 2017/8/5.
 */

public class UserInfoRetentionUtils {

    private ACache aCache ;
    private static UserInfoRetentionUtils instance ;
    private Context context ;
    private static final String USER_INFO = "user_info" ;

    private UserInfoRetentionUtils(Context context){
        this.context = context ;
        this.aCache = ACache.get(context) ;
    }

    public static UserInfoRetentionUtils getInstance(Context context){

        if (instance == null){
            synchronized (UserInfoRetentionUtils.class){
                if(instance == null){
                    instance = new UserInfoRetentionUtils(context) ;
                }
            }
        }

        return instance ;
    }

    /**
     * 将用户信息存入Acache
     * @param userInfo
     */
    public void putUserInof(UserInfo userInfo){

        if (userInfo != null ){

            String json = GsonUtils.toJson(userInfo);
            aCache.put(USER_INFO , json);

        }
    }

    /**
     * 将用户信息存入Acache，并设置时效单位为秒
     * @param userInfo
     * @param time
     */
    public void putUserInof(UserInfo userInfo , int time){

        if (userInfo != null ){

            String json = GsonUtils.toJson(userInfo);
            aCache.put(USER_INFO , json , time);

        }
    }

    /**
     * 删除用户信息
     * @param userInfo
     */
    public void removeUserInfo(UserInfo userInfo){
        if (userInfo != null ){
            aCache.remove(USER_INFO);
        }
    }

    public UserInfo getUserInfo(){
        if (aCache != null ){
            String json = aCache.getAsString(USER_INFO) ;
            if (json != null ){
                return GsonUtils.fromJson(json , (Class<UserInfo>) new TypeToken<UserInfo>(){} .getType()) ;
            }

        }
        return null;
    }

}
