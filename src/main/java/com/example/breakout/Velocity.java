package com.example.breakout;

import lombok.Getter;

public class Velocity {
    @Getter
    private int x;

    @Getter
    private int y;

    public Velocity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reverseX() {
        x *= -1;
    }

    public void reverseY() {
        y *= -1;
    }
}
