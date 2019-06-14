package com.example.breakout;

import java.awt.*;

/**
 * Created by ryo on 2018/08/25.
 */
public class Racket {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 5;

    private int centerPos;

    public Racket() {
        centerPos = MainPanel.WIDTH / 2;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT, WIDTH, HEIGHT);
    }

    public void move(int pos) {
        centerPos = pos;

        if (centerPos < WIDTH / 2) {
            centerPos = WIDTH / 2;
        } else if (centerPos > (MainPanel.WIDTH - WIDTH / 2)) {
            centerPos = MainPanel.WIDTH - WIDTH / 2;
        }
    }

    public boolean collideWith(Ball ball) {
        Rectangle racketRect = new Rectangle(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT, WIDTH, HEIGHT);
        Rectangle ballRect = new Rectangle(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());

        return racketRect.intersects(ballRect);
    }
}
