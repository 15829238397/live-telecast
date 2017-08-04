package com.example.livetelecast.okhttp.data;

import android.content.Context;

import com.example.livetelecast.okhttp.IDontObfuscate;


/**
 * @description: 用户信息
 *
 * @author: Andruby
 * @time: 2016/10/31 18:07
 */
public class UserInfo extends IDontObfuscate {

    public String userId;
    public String nickname;
    public String headPic;
    public String sigId;
    public String sdkAppId;
    public String sdkAccountType;
    public int sex;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSigId() {
        return sigId;
    }

    public void setSigId(String sigId) {
        this.sigId = sigId;
    }

    public String getSdkAppId() {
        return sdkAppId;
    }

    public void setSdkAppId(String sdkAppId) {
        this.sdkAppId = sdkAppId;
    }

    public String getSdkAccountType() {
        return sdkAccountType;
    }

    public void setSdkAccountType(String sdkAccountType) {
        this.sdkAccountType = sdkAccountType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headPic='" + headPic + '\'' +
                ", sigId='" + sigId + '\'' +
                ", sdkAppId='" + sdkAppId + '\'' +
                ", sdkAccountType='" + sdkAccountType + '\'' +
                ", sex=" + sex +
                '}';
    }
}
