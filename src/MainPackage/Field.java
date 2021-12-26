package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {

    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);

    private boolean paused;

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ev) {

            repaint();
        }
    });

    public Field(){
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void addBalls(){
        balls.add(new BouncingBall(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball: balls) {
            ball.paint(canvas);
        }
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void canMove(BouncingBall ball)
            throws InterruptedException {
        if (paused) {
            wait();
        }
    }

    public synchronized void resume() {
        paused = false;
        notifyAll();
    }


}
