package com.example.livetelecast;

import android.app.Application;
import android.content.Context;

import com.example.livetelecast.okhttp.data.UserInfo;
import com.example.livetelecast.utils.UserInfoRetentionUtils;

/**
 * Created by 博 on 2017/8/3.
 */

public class LiveApp extends Application {

    private static LiveApp instance ;
    private UserInfo userInfo ;
    private UserInfoRetentionUtils userInofRetentionUtils ;

    private LiveApp(Context context){
        userInofRetentionUtils = UserInfoRetentionUtils.getInstance(context) ;
    }

    public static LiveApp getInstance(Context context){

        if (instance == null){
            synchronized (LiveApp.class){
                if (instance == null){
                    instance = new LiveApp(context) ;
                }
            }
        }

        return instance ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        userInfo = userInofRetentionUtils.getUserInfo() ;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        userInofRetentionUtils.putUserInof(userInfo);
    }

    public void setUserInfo(UserInfo userInfo , int timeOut) {
        this.userInfo = userInfo;
        userInofRetentionUtils.putUserInof(userInfo , timeOut);
    }

}
