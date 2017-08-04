package com.example.livetelecast.base;

/**
 * Created by 博 on 2017/8/3.
 */

public interface BasePresenter {

    /**
     * presenter开始时的处理方法
     */
    void start() ;

    /**
     * presenter结束时的处理方法，一般界面结束的时候调用，处理一些销毁工作
     */
    void finish() ;

}
