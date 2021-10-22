package com.example.breakout;

import lombok.Getter;

public class Coordinates {
    @Getter
    private int x;

    @Getter
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Velocity velocity) {
        this.x += velocity.getX();
        this.y += velocity.getY();
    }

    public void moveX(int x) {
        this.x = x;
    }
}
