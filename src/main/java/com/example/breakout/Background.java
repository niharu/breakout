package com.example.breakout;

import lombok.Getter;

import java.awt.*;

public class Background {

    @Getter
    private int width;

    @Getter
    private int height;

    public Background(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
    }
}
