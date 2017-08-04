package com.example.livetelecast.presenter.ipresenter;

import com.example.livetelecast.base.BasePresenter;
import com.example.livetelecast.base.BaseView;

/**
 * Created by 博 on 2017/8/3.
 */

public abstract class ILoginPresenter implements BasePresenter {

    public abstract boolean checkNornameLogin(String username , String password) ;
    public abstract void normalLogin(String username , String password);

    public abstract boolean checkphoneLogin(String phone , String verifyCode) ;
    public abstract void phoneLogin(String phone , String verifyCode) ;

    public interface ILoginView extends BaseView{

        void normalLoginSuccess() ;
        void normalLoginFailed(String error) ;

        void phoneLoginSuccess() ;
        void phoneLoginFailed(String error) ;

        void usernameError(String error) ;
        void phoneErroe(String error) ;
    }
}
