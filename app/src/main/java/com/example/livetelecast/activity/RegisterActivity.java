package com.example.livetelecast.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidlibrary.ButterKnife.BindOnclick;
import com.example.androidlibrary.ButterKnife.BindView;
import com.example.livetelecast.R;
import com.example.livetelecast.base.BaseActivity;

/**
 * Created by 博 on 2017/8/1.
 */

public class RegisterActivity extends BaseActivity{

    private String username ;
    private String password ;

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
    }

    @Override
    protected void initData() {
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

        }else{
            tilUsername.setHint(getString(R.string.loginActivity_input_phone));
            tilPassword.setHint(getString(R.string.loginActivity_input_identifycode));
            tilMSPassword.setVisibility(View.GONE);
            butRequestIdentifycode.setVisibility(View.VISIBLE);
            butRegisterByAnyWay.setText(R.string.registerbyusername);
        }

    }

}
