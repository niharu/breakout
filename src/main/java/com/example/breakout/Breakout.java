package com.example.breakout;

import javax.swing.*;

/**
 * Created by ryo on 2018/08/25.
 */
public class Breakout extends JFrame {
    private static final long serialVersionUID = 1L;

    public Breakout() {
        setTitle("move racket");
        setResizable(false);

        MainPanel panel = new MainPanel();
        getContentPane().add(panel);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
