package com.example.livetelecast.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.livetelecast.okhttp.AsyncHttp;
import com.example.livetelecast.okhttp.request.LoginRequest;
import com.example.livetelecast.okhttp.request.RequestComm;
import com.example.livetelecast.okhttp.response.Response;
import com.example.livetelecast.presenter.ipresenter.ILoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * Created by 博 on 2017/8/3.
 */

public class LoginPresenter extends ILoginPresenter {

    ILoginView iLoginView ;

    public LoginPresenter (ILoginView iLoginView){
        this.iLoginView = iLoginView ;
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }

    @Override
    public boolean checkNornameLogin(String username, String password) {
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
            return true ;
        }else {
            iLoginView.usernameError("请填写账号或密码");
        }
        return false;
    }

    @Override
    public void normalLogin(String username, String password) {

        if (!this.checkNornameLogin(username , password)){
            return ;
        }

        AsyncHttp asyncHttp = AsyncHttp.instance() ;

        asyncHttp.postJson( new LoginRequest(RequestComm.login , username , password), new AsyncHttp.IHttpListener() {
            @Override
            public void onStart(int requestId) {
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onSuccess(int requestId, Response response) {
                iLoginView.normalLoginSuccess();
            }

            @Override
            public void onFailure(int requestId, int httpStatus, Throwable error) {
                iLoginView.normalLoginFailed(error.getMessage());
            }
        });
    }

    @Override
    public boolean checkphoneLogin(String phone, String verifyCode) {

        if (TextUtils.isEmpty(phone)){
            iLoginView.phoneErroe("请输入手机号");
            return false ;
        }
        if(phone.length() != 11){
            iLoginView.phoneErroe("手机号格式错误");
            return false ;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}" ;
        Pattern p = Pattern.compile(rule) ;
        Matcher m = p.matcher(phone) ;

        if (!m.matches()){
            iLoginView.phoneErroe("手机号格式错误");
            return false;
        }

        if (TextUtils.isEmpty(verifyCode)){
            iLoginView.phoneErroe("请填写验证码");
            return false;
        }

        if(verifyCode.length() != 4){
            iLoginView.phoneErroe("验证码格式错误");
        }

        return true;
    }

    @Override
    public void phoneLogin(String phone, String verifyCode) {

    }
}
