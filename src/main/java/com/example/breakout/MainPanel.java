package com.example.breakout;

import lombok.SneakyThrows;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

/**
 * Created by ryo on 2018/08/25.
 */
public class MainPanel extends JPanel implements Runnable, MouseMotionListener {
    private static final int WIDTH = 360;
    private static final int HEIGHT = 480;

    private final Racket racket;
    private final Ball ball;
    private final Blocks blocks;
    private final Background background;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);

        racket = new Racket(WIDTH / 2 - Racket.WIDTH/2, HEIGHT - Racket.HEIGHT);
        ball = new Ball();
        blocks = new Blocks();
        background = new Background(WIDTH, HEIGHT);

        Thread gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        background.draw(g);
        racket.draw(g);
        ball.draw(g);
        blocks.draw(g);
    }

    @SneakyThrows
    @Override
    public void run() {
        while (ball.isContinue(background)) {
            ball.move();

            ball.bounceOf(background);

            ball.bounceOf(racket);

            // ボールと衝突したブロックを取得し、ブロックを削除する
            Optional<Block> collideBlock = blocks.getCollideBlock(ball);
            collideBlock.ifPresent(Block::delete);

            // ブロックと衝突後、ボールを跳ね返す
            Optional<Direction> direction = collideBlock.map(b -> b.getDirectionToCollide(ball));
            direction.ifPresent(d -> ball.bounceTo(d));

            repaint();
            Thread.sleep(20);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        racket.move(x, WIDTH);
        repaint();
    }
}
