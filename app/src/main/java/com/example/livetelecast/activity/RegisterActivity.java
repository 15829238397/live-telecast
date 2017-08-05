package com.example.livetelecast.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidlibrary.ButterKnife.BindOnclick;
import com.example.androidlibrary.ButterKnife.BindView;
import com.example.livetelecast.R;
import com.example.livetelecast.base.BaseActivity;
import com.example.livetelecast.presenter.RegisterPresenter;
import com.example.livetelecast.presenter.ipresenter.IRegisterPresenter;

/**
 * Created by 博 on 2017/8/1.
 */

public class RegisterActivity extends BaseActivity implements IRegisterPresenter.IRegisterView{

    private String username ;
    private String password ;
    private RegisterPresenter mRegisterPresenter ;

    @BindView(R.id.but_request_Identifycode)
    private Button butRequestIdentifycode ;

    @BindView(R.id.til_username)
    private TextInputLayout tilUsername ;

    @BindView(R.id.til_password)
    private TextInputLayout tilPassword ;

    @BindView(R.id.til_ms_password)
    private TextInputLayout tilMSPassword ;

    @BindView(R.id.but_back)
    private Button butBack ;

    @BindView(R.id.but_register)
    private Button butRegister ;

    @BindView(R.id.but_registerbyAnyWay)
    private Button butRegisterByAnyWay ;

    @BindView(R.id.et_username)
    private EditText etUeserName ;

    @BindView(R.id.et_password)
    private EditText etPassword ;

    @BindView(R.id.et_ms_password)
    private EditText etMSPassword ;

    @Override
    protected void setListener() {

        addOnchangeListener() ;
    }

    private void addOnchangeListener(){
        etUeserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (butRegisterByAnyWay.getText().toString().trim().equals(getString(R.string.registerbyusername))){
                    mRegisterPresenter.checkphoneRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() ) ;
                }else {
                    mRegisterPresenter.checkNornameRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() , etMSPassword.getText().toString().trim()) ;
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (butRegisterByAnyWay.getText().toString().trim().equals(getString(R.string.registerbyusername))){
                    mRegisterPresenter.checkphoneRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() ) ;
                }else {
                    mRegisterPresenter.checkNornameRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() , etMSPassword.getText().toString().trim()) ;
                }
            }
        });

        etMSPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (butRegisterByAnyWay.getText().toString().trim().equals(getString(R.string.registerbyusername))){
                    mRegisterPresenter.checkphoneRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() ) ;
                }else {
                    mRegisterPresenter.checkNornameRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() , etMSPassword.getText().toString().trim()) ;
                }
            }
        });
    }

    @Override
    protected void initData() {

        mRegisterPresenter = new RegisterPresenter(this) ;

        mRegisterPresenter.checkNornameRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() , etMSPassword.getText().toString().trim()) ;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    public static void invoke(Context context){
        Intent intent = new Intent(context , RegisterActivity.class) ;
        context.startActivity(intent);
    }

    @BindOnclick(R.id.but_back)
    private void goBack(View v){
        finish();
    }

    @BindOnclick(R.id.but_registerbyAnyWay)
    private void registerByAnyWay(View v){

        if (butRegisterByAnyWay.getText().toString().trim().equals(getString(R.string.registerbyusername))){

            tilUsername.setHint(getString(R.string.registerActivity_input_username));
            tilPassword.setHint(getString(R.string.registerActivity_input_password));
            tilMSPassword.setVisibility(View.VISIBLE);
            butRequestIdentifycode.setVisibility(View.GONE);
            butRegisterByAnyWay.setText(R.string.registerbyphone);
            mRegisterPresenter.checkNornameRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() , etMSPassword.getText().toString().trim()) ;

        }else{
            tilUsername.setHint(getString(R.string.loginActivity_input_phone));
            tilPassword.setHint(getString(R.string.loginActivity_input_identifycode));
            tilMSPassword.setVisibility(View.GONE);
            butRequestIdentifycode.setVisibility(View.VISIBLE);
            butRegisterByAnyWay.setText(R.string.registerbyusername);
            mRegisterPresenter.checkphoneRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim()) ;
        }
    }

    @BindOnclick(R.id.but_register)
    private void register(View view){
        if (butRegisterByAnyWay.getText().toString().trim().equals(getString(R.string.registerbyusername))){

        }else {
            mRegisterPresenter.normalRegister(etUeserName.getText().toString().trim() , etPassword.getText().toString().trim() , etMSPassword.getText().toString().trim());
        }
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void showMsg(String msg) {
    }

    @Override
    public void showMsg(int msg) {
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void normalRegisterSuccess() {
    }

    @Override
    public void normalRegisterFailed(String error) {
    }

    @Override
    public void phoneRegisterSuccess() {
    }

    @Override
    public void phoneRegisterFailed(String error) {
    }

    @Override
    public void usernameError(String error) {
        etUeserName.setError(error);
    }

    @Override
    public void phoneErroe(String error) {
        etUeserName.setError(error);
    }

    @Override
    public void verifyCodeError(String error) {
        etPassword.setError(error);
    }

    @Override
    public void passwordError(String error) {
        etPassword.setError(error);
    }

    @Override
    public void msPasswordError(String error) {
        etMSPassword.setError(error);
    }
}
