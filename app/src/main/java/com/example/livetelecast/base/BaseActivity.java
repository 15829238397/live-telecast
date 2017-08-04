package com.example.livetelecast.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlibrary.ButterKnife.ButterKnife;

/**
 * Created by 博 on 2017/8/1.
 */

public abstract class BaseActivity extends FragmentActivity {

    private Activity mContext ;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBeforeLayout() ;
        //通过程序改变屏幕显示的方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //强制竖屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE););//强制为横屏

        mContext = this;
        mHandler = new Handler() ;

        if( getLayoutId() != 0 ){
            setContentView(getLayoutId());
        }
        //绑定BuutterKnife
        ButterKnife.bind(mContext);

        initData() ;
        setListener() ;
    }

    protected abstract void setListener() ;

    protected abstract void initData() ;

    protected abstract int getLayoutId() ;

    protected void setBeforeLayout() {
    }

    /**
     * 封装一些获得View控件的方法
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T obtainView(int resId){
        return (T) findViewById(resId) ;
    }

    protected Button findButton (int resId){
        return obtainView(resId) ;
    }

    protected TextView findTextView (int resId){
        return obtainView(resId) ;
    }

    protected EditText findEditText (int resId){
        return obtainView(resId) ;
    }

    protected ListView findListView (int resId) {
        return obtainView(resId) ;
    }


    /**
     * 显示Toast
     *
     * @param resStr
     * @return Toast对象，便于控制toast的显示与关闭
     */
    public Toast showToast(final String resStr){
        if (TextUtils.isEmpty(resStr)){
            return null ;
        }

        final Toast[] toast = {null};

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                toast[0] = Toast.makeText(BaseActivity.this , resStr , Toast.LENGTH_SHORT) ;
                toast[0].show();
            }
        });
        return toast[0];
    }

}
