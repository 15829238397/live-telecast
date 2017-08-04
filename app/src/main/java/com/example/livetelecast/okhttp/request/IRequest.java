package com.example.livetelecast.okhttp.request;

import com.example.livetelecast.okhttp.IDontObfuscate;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by 博 on 2017/8/3.
 */

public abstract class IRequest extends IDontObfuscate{

    public static final String HOST_PUBLIC = "http://live.demo.cniao5.com/Api/";

    public int mRequestId = 0;
    protected int mDraw = 0;
    protected RequestParams requestParams = new RequestParams() ;
    protected final static Gson mGson = new Gson();

    /**
     * 设置方法获得request的唯一标识
     * @return
     */
    public int getmRequestId() {
        return mRequestId;
    }

    /**
     * 设置方法设置request的唯一标识
     * @param mRequestId
     */
    public void setmRequestId(int mRequestId) {
        this.mRequestId = mRequestId;
    }

    /**
     * 提供方法获得请求参数
     * @return
     */
    public RequestParams getRequestParams() {
        return requestParams;
    }

    /**
     * 提供方法设置请求参数
     * @param requestParams
     */
    public void setRequestParams(RequestParams requestParams) {
        this.requestParams = requestParams;
    }

    /**
     * 提供抽象方法，传递url
     * @return
     */
    public abstract String getUrl();

    /**
     * 提供方法获得Host
     * @return
     */
    public String getHost(){
        return HOST_PUBLIC ;
    }

    public abstract Type getParserType() ;

}
