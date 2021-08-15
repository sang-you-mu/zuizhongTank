package com.zuizhong.game;

import com.zuizhong.app.GameMain;
import com.zuizhong.util.MyUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
* 坦克类
* */
public class Tank {
    //坦克状态
    private Color Color;
    private int state=STATE_STAND;
    public static final int STATE_STAND=0;
    public static final int STATE_MOVE=1;
    public static final int STATE_DIE=2;
    //四个方向
    public static final int DIR_UP=0;
    public static final int DIR_DOWN=1;
    public static final int DIR_LEFT=2;
    public static final int DIR_RIGHT=3;
    private int x,y;
    //半径
    public static final int RADIUS =16;
    //默认速度
    public static final int DEFAULT_SPEED =4;
    //血量和默认血量
    public static final int DEFAULT_HP=1000;
    private int hp= DEFAULT_HP;
    //攻击
    private  int atk;
    //速度
    private int speed;
    //方向
    private int dir;

    //TODO 炮弹
    private List bullets=new ArrayList();


    public Tank(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        Color= MyUtil.getRandomColor();
    }

    public java.awt.Color getColor() {
        return Color;
    }

    public void setColor(java.awt.Color color) {
        Color = color;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public List getBullets() {
        return bullets;
    }

    public void setBullets(List bullets) {
        this.bullets = bullets;
    }

    /*坦克的逻辑处理
    * */
    private void logic(){
        switch (state){
            case STATE_STAND:
                break;
            case STATE_MOVE:
                break;

            case STATE_DIE:
                break;

        }
    }
    /*
    * 绘制坦克
    * */
    public void draw (Graphics g){
     g.setColor(Color);
     //绘制坦克的圆
        g.fillOval(x-RADIUS,y-RADIUS,RADIUS<<1,RADIUS<<1);
        int endX=x;
        int endY=y;
        switch (dir){
            case DIR_UP:
                endY=y-RADIUS*2;
                g.drawLine(x-1,y,endX-1,endY);
                g.drawLine(x+1,y,endX+1,endY);
                break;
            case DIR_DOWN:
                endY=y+RADIUS*2;
                g.drawLine(x-1,y,endX-1,endY);
                g.drawLine(x+1,y,endX+1,endY);
                break;
            case DIR_LEFT:
                endX=x-2*RADIUS;
                g.drawLine(x,y-1,endX,endY-1);
                g.drawLine(x,y+1,endX,endY+1);
                break;
            case DIR_RIGHT:
                endX=x+2*RADIUS;
                g.drawLine(x,y-1,endX,endY-1);
                g.drawLine(x,y+1,endX,endY+1);
                break;
        }
        g.drawLine(x,y,endX,endY);
    }

}
