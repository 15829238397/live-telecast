package com.example.livetelecast.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidlibrary.ButterKnife.BindOnclick;
import com.example.androidlibrary.ButterKnife.BindView;
import com.example.livetelecast.R;
import com.example.livetelecast.base.BaseActivity;
import com.example.livetelecast.presenter.ipresenter.ILoginPresenter;
import com.example.livetelecast.presenter.LoginPresenter;

/**
 * Created by 博 on 2017/8/1.
 */

public class LoginActivity extends BaseActivity implements ILoginPresenter.ILoginView{

    private String TAG = LoginActivity.class.getName() ;
    private LoginPresenter loginPresent ;

    @BindView(R.id.til_username)
    private TextInputLayout tilUsername ;

    @BindView(R.id.til_password)
    private TextInputLayout tilPassword ;

    @BindView(R.id.et_username)
    private EditText etUserName ;

    @BindView(R.id.et_password)
    private EditText etPassword ;

    @BindView(R.id.but_loginbyanyway)
    private Button butLoginByAnyWay ;

    @BindView(R.id.but_register)
    private Button butRegister ;

    @BindView(R.id.but_login)
    private Button butLogin ;

    @BindView(R.id.but_request_Identifycode)
    private Button butRequestIdentifyCode ;

    private String username ;
    private String password ;

    @Override
    protected void setListener() {
    }

    @Override
    protected void initData() {

        username = etUserName.getText().toString().trim() ;
        password = etPassword.getText().toString().trim() ;
        loginPresent = new LoginPresenter(this) ;

        if (TextUtils.isEmpty(username)){
            Log.d(TAG , "username:" + username ) ;
        }

        if (TextUtils.isEmpty(password)){
            Log.d(TAG , "password:" + password ) ;
        }

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context , LoginActivity.class) ;
        context.startActivity(intent);
    }

    @BindOnclick(R.id.but_register)
    public void toTumpRegister(View v){
        RegisterActivity.invoke(this);
    }

    @BindOnclick(R.id.but_loginbyanyway)
    public void loginByAnyWay(View v){

        if (butLoginByAnyWay.getText().toString().trim().equals(getString(R.string.usernamelogin_ways))){
            tilUsername.setHint(getString(R.string.loginActivity_username_name));
            tilPassword.setHint(getString(R.string.loginActivity_pwd_des));
            butLoginByAnyWay.setText(R.string.phonelogin_ways);
            butRequestIdentifyCode.setVisibility(View.GONE);
        }else{
            tilUsername.setHint(getString(R.string.loginActivity_username_pho));
            tilPassword.setHint(getString(R.string.loginActivity_input_identifycode));
            butLoginByAnyWay.setText(R.string.usernamelogin_ways);
            butRequestIdentifyCode.setVisibility(View.VISIBLE);
        }
    }

    @BindOnclick(R.id.but_login)
    public void login(View v){
        if(getString(R.string.usernamelogin_ways).equals(butLoginByAnyWay.getText().toString().trim())){
            loginPresent.phoneLogin("15829238397" ,"");
        }else {
            loginPresent.normalLogin(etUserName.getText().toString().trim() ,etPassword.getText().toString().trim());
        }
    }

    @Override
    public void normalLoginSuccess() {
    }

    @Override
    public void normalLoginFailed(String error) {
        showToast(error) ;
    }

    @Override
    public void phoneLoginSuccess() {
    }

    @Override
    public void phoneLoginFailed(String error) {
        showToast(error) ;
    }

    @Override
    public void usernameError(String error) {
        showToast(error) ;
    }

    @Override
    public void phoneErroe(String error) {
        showToast(error) ;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void showMsg(String msg) {
        showToast(msg) ;
    }

    @Override
    public void showMsg(int msg) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
