package com.example.livetelecast.okhttp.request;

import com.example.livetelecast.okhttp.data.UserInfo;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.livetelecast.okhttp.request.RequestComm.ACTION;
import static com.example.livetelecast.okhttp.request.RequestComm.PASSWORD;
import static com.example.livetelecast.okhttp.request.RequestComm.USERNAME;

/**
 * Created by 博 on 2017/8/5.
 */

public class RegisterRequest extends IRequest {

    private static final String URL = "User" ;
    private static final String ACTION_VALUE = "register" ;

    public RegisterRequest(String usreName , String passWord){
        requestParams.put(ACTION , ACTION_VALUE);
        requestParams.put(USERNAME , usreName);
        requestParams.put(PASSWORD , passWord);
    }

    @Override
    public String getUrl() {
        return getHost() + URL ;
    }

    @Override
    public Type getParserType() {
        return new TypeToken<UserInfo>(){}.getType();
    }
}
