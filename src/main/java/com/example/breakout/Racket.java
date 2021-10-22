package com.example.breakout;

import java.awt.*;

/**
 * Created by ryo on 2018/08/25.
 */
public class Racket {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 5;

    private Coordinates coordinates;

    public Racket(int x, int y) {
        this.coordinates = new Coordinates(x,y);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(coordinates.getX(), coordinates.getY(), WIDTH, HEIGHT);
    }

    public void move(int centerPos, int mainWidth) {
        // ラケットが左端に到達したら止まる
        if (mainWidth - WIDTH < centerPos) {
            coordinates.moveX(mainWidth - WIDTH);
            return;
        }

        // ラケットが右端に到達したら止まる
        if (centerPos - WIDTH / 2 < 0) {
            coordinates.moveX(0);
            return;
        }

        coordinates.moveX(centerPos - WIDTH / 2);
    }

    public Rectangle getRectangle() {
        return new Rectangle(coordinates.getX(), coordinates.getY(), WIDTH, HEIGHT);
    }
}

