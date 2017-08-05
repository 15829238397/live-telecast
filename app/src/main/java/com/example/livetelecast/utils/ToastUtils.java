package com.example.livetelecast.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 博 on 2017/8/5.
 */

public class ToastUtils {

    public static void showStToast(Context context , String msg ){
        Toast.makeText(context , msg , Toast.LENGTH_SHORT).show();
    }

    public static void showLgToast(Context context , String msg ){
        Toast.makeText(context , msg , Toast.LENGTH_LONG).show();
    }

}
