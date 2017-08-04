package com.example.livetelecast.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 博 on 2017/8/1.
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getLayoutId() != 0){
            mRootView = inflater.inflate(getLayoutId() , container , false) ;
        }
        initView(mRootView) ;
        //设置fragment可见
        setUserVisibleHint(true);
        initData() ;
        setListener() ;

        return mRootView ;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(hidden){

        }
        super.onHiddenChanged(hidden);
    }

    protected abstract void initData();

    protected abstract void setListener();

    protected abstract void initView (View view) ;

    protected abstract int getLayoutId() ;
}
