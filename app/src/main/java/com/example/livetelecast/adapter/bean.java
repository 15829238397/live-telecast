package com.example.livetelecast.adapter;

/**
 * Created by 博 on 2017/8/1.
 */

public class bean {

    private String name ;
    private String num ;

    public bean(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
