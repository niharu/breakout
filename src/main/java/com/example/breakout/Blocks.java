package com.example.breakout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Blocks {
    private static final int NUM_BLOCK_ROW = 10;
    private static final int NUM_BLOCK_COL = 7;

    private List<Block> blocks = new ArrayList<>();

    public Blocks() {
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                int x = j * Block.WIDTH + Block.WIDTH;
                int y = i * Block.HEIGHT + Block.HEIGHT;
                blocks.add(new Block(x, y));
            }
        }
    }

    public void draw(Graphics g) {
        blocks.stream().filter(b -> !b.isDeleted()).forEach(b -> b.draw(g));
    }

    public Optional<Block> getCollideBlock(Ball ball) {
        return blocks.stream().filter(b -> !b.isDeleted()).filter(b -> b.isCollide(ball))
                .findFirst();
    }
}
