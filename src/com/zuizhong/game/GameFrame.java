package com.zuizhong.game;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//静态导入
import static com.zuizhong.util.Constant.*;

/*
 * 游戏的主窗口
 * 所有的游戏展示的内容在改类中实现
 *
 * */
public class GameFrame extends Frame implements Runnable {
    //游戏状态
    public static int gameState;
    //菜单指向
    private int menuIndex;
    //定义坦克对象
    private Tank myTank;

    /*
     * 对窗口进行初始化
     * */
    public GameFrame() {
        initFrame();
        initEventListener();
        //启动刷新
        new Thread(this).start();
    }

    /*
     *对游戏进行初始化
     **/
    private void initGame() {
        gameState = STATE_MENU;
    }

    /*
     * 属性进行初始化*/
    private void initFrame() {
        //设置标题
        setTitle(GAME_TITLE);
        //设置窗口大小
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        //设置窗口左上角的坐标
        setLocation(FRAME_X, FRAME_Y);
        //设置窗口大小不可改变
        setResizable(false);
        //设置窗口可见
        setVisible(true);

    }


    /*
     * 是FRAME类的方法,继承下来的方法,负责了所有绘制的内容,所有需要在屏幕中显示的
     * 内容都要在该方法内调用.该方法不能主动调用 必须通过调用repaint();去回调该方法
     * 只要调用repaint系统就会调用该方法
     * */
    public void update(Graphics g) {
        //设置字体
        g.setFont(GAME_FONT);
        switch (gameState) {
            case STATE_MENU:
                drawMenu(g);
                break;
            case STATE_HELP:
                drawHelp(g);
                break;
            case STATE_ABOUT:
                drawAbout(g);
                break;
            case STATE_RUN:
                drawRun(g);
                break;
            case STATE_OVER:
                drawOver(g);
                break;
        }
    }

    private void drawOver(Graphics g) {

    }

    private void drawRun(Graphics g) {
        //绘制黑色背景
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        myTank.draw(g);
    }

    private void drawAbout(Graphics g) {

    }

    private void drawHelp(Graphics g) {

    }

    /*
     * 绘制菜单状态下的内容
     * */
    private void drawMenu(Graphics g) {
        //绘制黑色的背景
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        //字符长度
        final int STR_WIDTH = 76;
        int x = FRAME_WIDTH - STR_WIDTH >> 1;
        int y = FRAME_HEIGHT / 3;
        //行间距
        final int DIS = 50;
        g.setColor(Color.WHITE);
        for (int i = 0; i < MENUS.length; i++) {
            if (i == menuIndex) {
                //选中的设置为红色 其他的为白色

                g.setColor(Color.red);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(MENUS[i], x, y + DIS * i);
        }
    }

    /*
     * 初始化窗口的事件监听
     * */
    private void initEventListener() {


        //注册监听事件
        addWindowListener(new WindowAdapter() {
            //点击关闭按钮时候,方法会被调用
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        //添加按键的监听事件
        addKeyListener(new KeyAdapter() {
            //这是按下时候的事件
            @Override
            public void keyPressed(KeyEvent e) {
                //得到被按键的键值
                int keyCode = e.getKeyCode();
                //不同的游戏状态按键的处理方法不一样
                switch (gameState) {
                    case STATE_MENU:
                        KeyEventMenu(keyCode);
                        break;
                    case STATE_HELP:
                        KeyEventHelp(keyCode);
                        break;
                    case STATE_ABOUT:
                        KeyEventAbout(keyCode);
                        break;
                    case STATE_RUN:
                        KeyEventRun(keyCode);
                        break;
                    case STATE_OVER:
                        KeyEventOVer(keyCode);
                        break;
                }
            }

            //这是按键松开时的事件
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void KeyEventOVer(int keyCode) {

    }

    private void KeyEventRun(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                myTank.setDir(Tank.DIR_UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                myTank.setDir(Tank.DIR_DOWN);

                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                myTank.setDir(Tank.DIR_LEFT);

                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                myTank.setDir(Tank.DIR_RIGHT);

                break;

        }
    }

    private void KeyEventAbout(int keyCode) {

    }

    private void KeyEventHelp(int keyCode) {

    }

    //菜单状态下按键的处理
    private void KeyEventMenu(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                menuIndex--;
                if (menuIndex < 0) {
                    menuIndex = MENUS.length - 1;
                }

                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                menuIndex++;
                if (menuIndex > MENUS.length - 1) {
                    menuIndex = 0;
                }


                break;
            case KeyEvent.VK_ENTER:
                //TODO
                newGame();
                break;
        }

    }

    /*
     * 开始新游戏的方法
     * */
    private void newGame() {
        gameState = STATE_RUN;
        //创建坦克对象和敌人的坦克对象
        myTank = new Tank(400, 100, Tank.DIR_DOWN);
    }


    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(REPAINT_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
