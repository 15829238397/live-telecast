package com.example.livetelecast.presenter;

import android.support.design.widget.BaseTransientBottomBar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.livetelecast.okhttp.AsyncHttp;
import com.example.livetelecast.okhttp.data.UserInfo;
import com.example.livetelecast.okhttp.request.RegisterRequest;
import com.example.livetelecast.okhttp.response.Response;
import com.example.livetelecast.presenter.ipresenter.IRegisterPresenter;
import com.example.livetelecast.utils.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * 完成注册功能，处理数据逻辑。
 */
public class RegisterPresenter extends IRegisterPresenter {

    IRegisterView mIRegisterView ;

    public RegisterPresenter(IRegisterView mIRegisterView) {
        super();
        this.mIRegisterView = mIRegisterView ;
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
    public boolean checkNornameRegister(String username, String password , String maPassword ) {

        if (TextUtils.isEmpty(username)){
            mIRegisterView.usernameError("请填写用户名");
            return false ;
        }

        if (TextUtils.isEmpty(password)){
            mIRegisterView.passwordError("请填写密码");
            return false ;
        }

        if (!maPassword.equals(password)){
            mIRegisterView.msPasswordError("密码不一致");
        }

        return true;
    }

    @Override
    public void normalRegister(String username, String password , String msPassword) {

        if (!checkNornameRegister(username , password ,msPassword )){
            return ;
        }

        AsyncHttp.instance().postJson(new RegisterRequest(username, password), new AsyncHttp.IHttpListener() {
            @Override
            public void onStart(int requestId) {
            }

            @Override
            public void onSuccess(int requestId, Response response) {
                mIRegisterView.normalRegisterSuccess();
                Log.d(TAG , "normalRegister:" + response ) ;
            }

            @Override
            public void onFailure(int requestId, int httpStatus, Throwable error) {
                mIRegisterView.normalRegisterFailed( error.getMessage() );
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
    public boolean checkphoneRegister(String phone, String verifyCode) {

        if (TextUtils.isEmpty(phone)){
            mIRegisterView.phoneErroe("请输入手机号");
            return false ;
        }
        if(phone.length() != 11){
            mIRegisterView.phoneErroe("手机号格式错误");
            return false ;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}" ;
        Pattern p = Pattern.compile(rule) ;
        Matcher m = p.matcher(phone) ;

        if (!m.matches()){
            mIRegisterView.phoneErroe("手机号格式错误");
            return false;
        }

        if (TextUtils.isEmpty(verifyCode)){
            mIRegisterView.verifyCodeError("请填写验证码");
            return false;
        }

        if(verifyCode.length() != 4){
            mIRegisterView.verifyCodeError("验证码格式错误");
        }

        return true;
    }

    @Override
    public void phoneRegister(String phone, String verifyCode) {
        ToastUtils.showStToast(mIRegisterView.getContext() , "此接口尚未开通");
    }
}
