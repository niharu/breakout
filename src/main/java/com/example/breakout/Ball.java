package com.example.breakout;

import java.awt.*;

/**
 * Created by ryo on 2018/08/25.
 */
public class Ball {
    private static final int SIZE = 8;

    // ボールの左上の座標
    private Coordinates coordinates;

    // 速度
    private Velocity velocity;

    public Ball() {
        coordinates = new Coordinates(20, 250);
        velocity = new Velocity(5, 5);
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(coordinates.getX(), coordinates.getY(), SIZE, SIZE);
    }

    public void move() {
        // 位置を速度分だけ動かす
        coordinates.move(velocity);

    }

    public void boundX() {
        velocity.reverseX();
    }

    public void boundY() {
        velocity.reverseY();
    }

    public void boundXY() {
        boundX();
        boundY();
    }

    public Point getPos(Corner direction) {
        switch(direction) {
            case TOP_LEFT:
                return new Point(coordinates.getX(),coordinates.getY());
            case TOP_RIGHT:
                return new Point(coordinates.getX()+SIZE,coordinates.getY());
            case BOTTOM_LEFT:
                return new Point(coordinates.getX(), coordinates.getY()+SIZE);
            case BOTTOM_RIGHT:
                return new Point(coordinates.getX()+SIZE,coordinates.getY()+SIZE);
            default:
                // TODO 例外返却でよいか検討
                throw new RuntimeException();
        }
    }

    private boolean isCollideSide(int width) {
        return coordinates.getX() <= 0 || width - SIZE <= coordinates.getX();
    }

    private boolean isCollideTop() {
        return coordinates.getY() <= 0;
    }

    public void bounceOf(Background background) {
        if (isCollideSide(background.getWidth())) {
            boundX();
        }

        if (isCollideTop()) {
            boundY();
        }
    }

    public void bounceOf(Racket racket) {
        Rectangle racketRect = racket.getRectangle();
        Rectangle ballRect = getRectangle();

        if(racketRect.intersects(ballRect) ) {
            boundY();
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(coordinates.getX(), coordinates.getY(), SIZE, SIZE);
    }

    public void bounceTo(Direction direction) {
        switch (direction) {
            // ボールの当たった位置からボールの反射方向を計算
            case UP:
            case DOWN:
                boundY();
                break;
            case LEFT:
            case RIGHT:
                boundX();
                break;
            case UP_LEFT:
            case UP_RIGHT:
            case DOWN_LEFT:
            case DOWN_RIGHT:
                boundXY();
                break;
            default:
                break;
        }
    }

    public boolean isContinue(Background background) {
        return !isGameOver(background);
    }

    public boolean isGameOver(Background background) {
        return background.getHeight() - SIZE <= coordinates.getY();
    }
}
