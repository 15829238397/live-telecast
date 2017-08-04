package com.example.livetelecast.okhttp.request;

import com.example.livetelecast.okhttp.data.UserInfo;
import com.example.livetelecast.okhttp.response.Response;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @description: 登陆请求
 * @author: Andruby
 * @time: 2016/11/2 18:07
 */
public class LoginRequest extends IRequest {

	/**
	 * 传递参数
	 * @param id
	 * @param userName
	 * @param password
	 */
	public LoginRequest(int id, String userName, String password) {
		requestParams.put("action", "login");
		requestParams.put("userName", userName);
		requestParams.put("password", password);
	}

	/**
	 * 获得uri
	 * @return
	 */
	@Override
	public String getUrl() {
		return getHost() + "User";
	}

	/**
	 * 传递解析需要的数据类型
	 * @return
	 */
	@Override
	public Type getParserType() {
		return new TypeToken<Response<UserInfo>>(){}.getType();
	}

}
