package com.example.livetelecast.presenter;

import android.text.TextUtils;

import com.example.livetelecast.LiveApp;
import com.example.livetelecast.okhttp.AsyncHttp;
import com.example.livetelecast.okhttp.data.UserInfo;
import com.example.livetelecast.okhttp.request.LoginRequest;
import com.example.livetelecast.okhttp.request.RequestComm;
import com.example.livetelecast.okhttp.response.Response;
import com.example.livetelecast.presenter.ipresenter.ILoginPresenter;
import com.example.livetelecast.utils.ToastUtils;
import com.example.livetelecast.utils.UserInfoRetentionUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 博 on 2017/8/3.
 */

public class LoginPresenter extends ILoginPresenter {

    ILoginView iLoginView ;
    LiveApp liveApp ;

    public LoginPresenter (ILoginView iLoginView){
        this.iLoginView = iLoginView ;
        this.liveApp = LiveApp.getInstance(iLoginView.getContext()) ;
    }

    @Override
    public void start() {
    }

    @Override
    public void finish() {
    }

    /**
     * 检查用户名是否规范
     * @param username
     * @param password
     * @return 信息填写是否规范
     */
    @Override
    public boolean checkNornameLogin(String username, String password) {

        if (TextUtils.isEmpty(username)){
            iLoginView.usernameError("请填写用户名");
            return false ;
        }

        if (TextUtils.isEmpty(password)){
            iLoginView.passwordError("请填写密码");
            return false ;
        }
        return true;
    }

    /**
     * 用户名登录
     * @param username
     * @param password
     */
    @Override
    public void normalLogin(String username, String password) {

        if (!this.checkNornameLogin(username , password)){
            return ;
        }

        AsyncHttp asyncHttp = AsyncHttp.instance() ;

        asyncHttp.postJson( new LoginRequest(RequestComm.login , username , password), new AsyncHttp.IHttpListener() {
            @Override
            public void onStart(int requestId) {
                //此处应展示正在登陆的loading 框。
            }

            @Override
            public void onSuccess(int requestId, Response response) {

                UserInfo userInfo = (UserInfo) response.data;
                liveApp.setUserInfo(userInfo , 3600*24);

                iLoginView.normalLoginSuccess();
            }

            @Override
            public void onFailure(int requestId, int httpStatus, Throwable error) {
                iLoginView.normalLoginFailed(error.getMessage());
            }
        });
    }

    /**
     * 检查手机号登陆信息是否规范
     * @param phone
     * @param verifyCode
     * @return 填写信息是否规范
     */
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
            iLoginView.verifyCodeError("请填写验证码");
            return false;
        }

        if(verifyCode.length() != 4){
            iLoginView.verifyCodeError("验证码格式错误");
        }

        return true;
    }

    /**
     * 用手机号登陆
     * @param phone
     * @param verifyCode
     */
    @Override
    public void phoneLogin(String phone, String verifyCode) {
        //此功能暂未开放。
        ToastUtils.showStToast(iLoginView.getContext() , "此接口尚未开通");
    }
}
