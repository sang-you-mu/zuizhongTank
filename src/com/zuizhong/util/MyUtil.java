package com.zuizhong.util;

import java.awt.*;

public class MyUtil {
    private MyUtil(){

    }
    /*
    * 得到随机数
    * */
    public static final int getRandomNumber(int min,int max){
        return (int)(Math.random()*(max-min)+min);
    }
/*
* 得到随机颜色
* */
    public static final Color getRandomColor(){
        int red=getRandomNumber(0,256);
        int blue=getRandomNumber(0,256);
        int green=getRandomNumber(0,256);
        Color c=new Color(red,green,blue);
        return new Color(red,green,blue);
    }
}
