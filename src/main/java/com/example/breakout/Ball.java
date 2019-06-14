package com.example.breakout;

import java.awt.*;
import java.util.Random;

/**
 * Created by ryo on 2018/08/25.
 */
public class Ball {
    private static final int SIZE = 8;

    private int x, y;

    private int vx, vy;

    private Random random;

    public Ball() {
        x = 10;
        y = 10;
        vx = 5;
        vy = 5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() {
        x += vx;
        y += vy;

        if (x <= 0 || MainPanel.WIDTH - SIZE <= x) {
            boundX();
        }

        if (y <= 0 || MainPanel.HEIGHT - SIZE <= y) {
            boundY();
        }
    }

    public void boundX() {
        vx *= -1;
    }

    public void boundY() {
        vy *= -1;
    }

    public void boundXY() {
        boundX();
        boundY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getPos(Corner direction) {
        switch(direction) {
            case TOP_LEFT:
                return new Point(x,y);
            case TOP_RIGHT:
                return new Point(x+SIZE,y);
            case BOTTOM_LEFT:
                return new Point(x,y+SIZE);
            case BOTTOM_RIGHT:
                return new Point(x+SIZE,y+SIZE);
            default:
                // TODO 例外返却でよいか検討
                throw new RuntimeException();
        }
    }

    public int getSize() {
        return SIZE;
    }
}
