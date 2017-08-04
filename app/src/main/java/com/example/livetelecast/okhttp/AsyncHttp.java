package com.example.livetelecast.okhttp;

import android.util.Log;

import com.example.livetelecast.okhttp.request.IRequest;
import com.example.livetelecast.okhttp.response.Response;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * Created by 博 on 2017/8/3.
 */

public class AsyncHttp {

    private static final String TAG = AsyncHttp.class.getName() ;
    private static Gson mGson = null ;
    private static AsyncHttp mInstance ;

    //实例化一个Client ;
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1000*20 , TimeUnit.MILLISECONDS)
            .readTimeout(1000*20 , TimeUnit.MILLISECONDS)
            .writeTimeout(1000*20 , TimeUnit.MILLISECONDS)
            .build() ;

    private AsyncHttp (){

        if (mGson == null){
            mGson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create() ;
        }


        OkHttpUtils.initClient(okHttpClient) ;
    }

    /**
     * 对外提供一个得到单例对象的方法
     * @return
     */
    public static AsyncHttp instance(){

        if (mInstance == null){
            synchronized (OkHttpUtils.class){
                if (mInstance == null){
                    mInstance = new AsyncHttp() ;
                }
            }
        }
        return mInstance ;
    }

    /**
     * 提供发起get请求的方法
     * @param request
     * @param listener
     */
    public void get(IRequest request , IHttpListener listener ){

        if (request != null){
           request.getRequestParams().getGetBuilder().url(request.getUrl()).id(request.getmRequestId()).build().execute(new BaseCallBack(listener , request.getParserType()));
        }
    }

    public void post(IRequest request , IHttpListener listener){
        if (request != null ){
            request.getRequestParams().getPostFormBuilder().url(request.getUrl()).id(request.getmRequestId()).build().execute(new BaseCallBack(listener , request.getParserType()));
        }
    }

    public void postJson(IRequest request, IHttpListener listener) {

        if(request.getRequestParams().has("action")){
            if (request != null) {
            request.getRequestParams().getPostJsonBuilder().url(request.getUrl()).id(request.getmRequestId()).
                    build().execute(new BaseCallBack(listener, request.getParserType()));
        }} else {
            throw new RuntimeException("Request param is null");
        }
    }


    /**
     * 请求状态的接口
     */
    public interface IHttpListener {
        void onStart(int requestId);

        void onSuccess(int requestId, Response response);

        void onFailure(int requestId, int httpStatus, Throwable error);

    }

    class BaseCallBack extends Callback<Response>{

        private IHttpListener mHttpListener;
        private Type mParserType;

        public BaseCallBack( IHttpListener mHttpListener , Type mParserType ) {
            super();
            this.mHttpListener = mHttpListener ;
            this.mParserType = mParserType ;
        }

        @Override
        public void onBefore(Request request, int id) {

            if (mHttpListener != null ){
                mHttpListener.onStart(id);
            }

        }

        @Override
        public Response parseNetworkResponse(okhttp3.Response response, int id) throws Exception {
            Response responseData = null;
            if(mHttpListener != null && mParserType != null){
                if (response.isSuccessful()){
                    String json = response.body().string() ;
                    Log.d(TAG, "parseNetworkResponse: "+json);
                    if (json != null) responseData = mGson.fromJson(json, mParserType);
                }else {
//					mHttpListener.onFailure(id, response.code(), new Exception("net error"));
                    onError(null, new Exception("net error"),id);
                }
            }
            return responseData ;
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            if (mHttpListener != null){
                mHttpListener.onFailure(id , 0 , e );
            }
        }

        @Override
        public void onResponse(Response response, int id) {
            if (mHttpListener != null){
                mHttpListener.onSuccess(id , response);
            }
        }
    }

}
