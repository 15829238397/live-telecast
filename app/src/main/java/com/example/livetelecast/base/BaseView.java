package com.example.livetelecast.base;

import android.content.Context;

/**
 * Created by 博 on 2017/8/3.
 */

public interface BaseView {

    /**
     * @description: 网络加载或耗时加载时界面显示
     * @author: Andruby
     * @time: 2016/9/8 23:08
     */
    void showLoading();

    /**
     * @description: 网络加载或耗时加载完成时界面显示
     * @author: Andruby
     * @time: 2016/9/8 23:08
     */
    void dismissLoading();

    /**
     * @description: 消息提示，如Toast，Dialog等
     * @author: Andruby
     * @time: 2016/9/8 23:08
     * @param:  msg 提示内容
     */
    void showMsg(String msg);
    void showMsg(int msg);

    /**
     * @description: 获取Context
     * @author: Andruby
     * @time: 2016/9/8 23:08
     */
    Context getContext();



}
