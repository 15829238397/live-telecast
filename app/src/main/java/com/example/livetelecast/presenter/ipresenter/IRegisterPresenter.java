package com.example.livetelecast.presenter.ipresenter;

import com.example.livetelecast.base.BasePresenter;
import com.example.livetelecast.base.BaseView;

/**
 * Created by 博 on 2017/8/5.
 */

public abstract class IRegisterPresenter implements BasePresenter {

    public abstract boolean checkNornameRegister(String username , String password , String msPassword) ;
    public abstract void normalRegister(String username , String password , String msPassword);

    public abstract boolean checkphoneRegister(String phone , String verifyCode) ;
    public abstract void phoneRegister(String phone , String verifyCode) ;

    public interface IRegisterView extends BaseView {

        void normalRegisterSuccess() ;
        void normalRegisterFailed(String error) ;

        void phoneRegisterSuccess() ;
        void phoneRegisterFailed(String error) ;

        void usernameError(String error) ;
        void phoneErroe(String error) ;
        void verifyCodeError(String error) ;
        void passwordError(String error) ;
        void msPasswordError(String error) ;

    }

}
