package com.example.breakout;

import java.awt.*;

/**
 * Created by ryo on 2018/08/25.
 */
public class Block {
    public static final int WIDTH = 40;
    public static final int HEIGHT = 16;

    // 位置（左上隅の座標）
    private int x, y;

    // ボールが当たって消されたか
    private boolean isDeleted;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        isDeleted = false;
    }

    /**
     * ブロックを描画
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, WIDTH, HEIGHT);

        // 枠線を描画
        g.setColor(Color.BLACK);
        g.drawRect(x, y, WIDTH, HEIGHT);
    }

    public boolean isCollide(Ball ball) {
        Rectangle blockRect = new Rectangle(x, y, WIDTH, HEIGHT);
        if (blockRect.contains(ball.getPos(Corner.TOP_LEFT)) ||
                blockRect.contains(ball.getPos(Corner.TOP_RIGHT)) ||
                blockRect.contains(ball.getPos(Corner.BOTTOM_LEFT))||
                blockRect.contains(ball.getPos(Corner.BOTTOM_RIGHT))) {
            return true;
        }
        return false;
    }

    /**
     * ボールと衝突したか
     *
     * @param ball ボール
     * @return 衝突位置
     */
    public Direction collideWith(Ball ball) {
        Rectangle blockRect = new Rectangle(x, y, WIDTH, HEIGHT);

        if (blockRect.contains(ball.getPos(Corner.TOP_LEFT))
                && blockRect.contains(ball.getPos(Corner.TOP_RIGHT))) {
            // ブロックの下から衝突＝ボールの左上・右上の点がブロック内
            return Direction.DOWN;
        } else if (blockRect.contains(ball.getPos(Corner.TOP_LEFT))
                && blockRect.contains(ball.getPos(Corner.BOTTOM_RIGHT))) {
            // ブロックの左から衝突＝ボールの右上・右下の点がブロック内
            return Direction.LEFT;
        } else if (blockRect.contains(ball.getPos(Corner.TOP_LEFT))
                && blockRect.contains(ball.getPos(Corner.BOTTOM_LEFT))) {
            // ブロックの右から衝突＝ボールの左上・左下の点がブロック内
            return Direction.RIGHT;
        } else if (blockRect.contains(ball.getPos(Corner.BOTTOM_LEFT))
                && blockRect.contains(ball.getPos(Corner.BOTTOM_RIGHT))) {
            // ブロックの上から衝突＝ボールの左下・右下の点がブロック内
            return Direction.UP;
        } else if (blockRect.contains(ball.getPos(Corner.TOP_RIGHT))) {
            // ブロックの左下から衝突＝ボールの右上の点がブロック内
            return Direction.DOWN_LEFT;
        } else if (blockRect.contains(ball.getPos(Corner.TOP_LEFT))) {
            // ブロックの右下から衝突＝ボールの左上の点がブロック内
            return Direction.DOWN_RIGHT;
        } else if (blockRect.contains(ball.getPos(Corner.BOTTOM_RIGHT))) {
            // ブロックの左上から衝突＝ボールの右下の点がブロック内
            return Direction.UP_LEFT;
        } else if (blockRect.contains(ball.getPos(Corner.BOTTOM_LEFT))) {
            // ブロックの右上から衝突＝ボールの左下の点がブロック内
            return Direction.UP_RIGHT;
        }

        return Direction.NONE;
    }

    /**
     * ブロックを消去
     *
     */
    public void delete() {
        // TODO: ここでブロックが壊れる効果音
        // TODO: ここで派手なアクション

        isDeleted = true;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}

