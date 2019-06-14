package com.example.breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by ryo on 2018/08/25.
 */
public class MainPanel extends JPanel implements Runnable, MouseMotionListener {
    public static final int WIDTH = 360;
    public static final int HEIGHT = 480;

    private static final int NUM_BLOCK_ROW = 10;
    private static final int NUM_BLOCK_COL = 7;

    private Racket racket;
    private Ball ball;
    private List<Block> blocks;

    // TODO privateフィールドに置き換え可能?
    private Thread gameloop;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);
        racket = new Racket();
        ball = new Ball();
        blocks = new ArrayList<>();

        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                int x = j * Block.WIDTH + Block.WIDTH;
                int y = i * Block.HEIGHT + Block.HEIGHT;
                blocks.add(new Block(x, y));
            }
        }

        gameloop = new Thread(this);
        gameloop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        racket.draw(g);
        ball.draw(g);

        blocks.stream().filter(b -> !b.isDeleted()).forEach(b -> b.draw(g));
    }

    public void run() {
        while (true) {
            ball.move();

            if (racket.collideWith(ball)) {
                ball.boundY();
            }

            Optional<Block> collideBlock = blocks.stream().filter(b -> !b.isDeleted()).filter(b -> b.isCollide(ball))
                    .findFirst();

            collideBlock.ifPresent(Block::delete);
            Optional<Direction> direction = collideBlock.map(b -> b.collideWith(ball));
            if (!direction.isPresent()) {

            }

            // ボールの当たった位置からボールの反射方向を計算
            else
                switch (direction.get()) {
                case UP:
                case DOWN:
                    ball.boundY();
                    break;
                case LEFT:
                case RIGHT:
                    ball.boundX();
                    break;
                case UP_LEFT:
                case UP_RIGHT:
                case DOWN_LEFT:
                case DOWN_RIGHT:
                    ball.boundXY();
                    break;
                default:
                    break;
                }

            repaint();
            try {
                // TODO ゲームスピード調整
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        racket.move(x);
        repaint();
    }
}
