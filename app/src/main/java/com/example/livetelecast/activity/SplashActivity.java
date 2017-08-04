package com.example.livetelecast.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.example.livetelecast.R;
import com.example.livetelecast.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * @Description:  闪屏页面
 * @author: Andruby
 * @date: 2016年7月9日
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int START_LOGIN = 2873;
    private final MyHandler mHandler = new MyHandler(this);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        if (!isTaskRoot()                                               //isTaskRoot()判断当前Activity是否最后一个Activity
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {
            finish();
            return;
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //从全局池中返回一个新的消息实例。避免在许多情况下，分配新的对象
        Message msg = Message.obtain();
        msg.arg1 = START_LOGIN;
        //延时1秒发送信息
        mHandler.sendMessageDelayed(msg, 1000);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onBackPressed() {

    }

    private void jumpToLoginActivity() {
        LoginActivity.invoke(this);
        finish();
    }

    private static class MyHandler extends Handler {

//        WeakReference则类似于可有可无的东西。在垃圾回收器线程扫描它所管辖的内存区域的过程中，
//        一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存，说白了就是
//        一个没那么strong要求垃圾回收器将一个对象保留在内存中。不过，由于垃圾回收器是一个优先
//        级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。

        private final WeakReference<SplashActivity> mActivity;

        public MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            //.get(),方法用来获得被WeakReference包裹的对象
            SplashActivity activity = mActivity.get();
            if (activity != null) {
                activity.jumpToLoginActivity();
            }
        }
    }

}
