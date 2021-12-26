package MainPackage;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable{

    private Color color;
    private Field field;
    private Integer MAX_RADIUS;
    private Integer MIN_RADIUS;
    private Integer MAX_SPEED;
    private Double radius;
    private Integer speed;
    private Double speedX;
    private Double speedY;
    private Double x;
    private Double y;

    public BouncingBall(Field field) {

        this.field = field;

        radius = Math.random()*(MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;

        speed = Math.toIntExact((Math.round(5 * MAX_SPEED / radius)));
        if (speed>MAX_SPEED) {
            speed = MAX_SPEED;
        }
        double angle = Math.random()*2*Math.PI;
        speedX = 3*Math.cos(angle);
        speedY = 3*Math.sin(angle);
        color = new Color((float)Math.random(), (float)Math.random(),
                (float)Math.random());
        x = Math.random()*(field.getSize().getWidth() - 2*radius) + radius;
        y = Math.random()*(field.getSize().getHeight() - 2*radius) + radius;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }


    public void paint(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double ball = new Ellipse2D.Double(x - radius, y - radius,
                2*radius, 2*radius);
        canvas.draw(ball);
        canvas.fill(ball);
    }

    @Override
    public void run() {
        try {
            while (true) {
                field.canMove(this);
                if (x + speedX <= radius) {
                    speedX = -speedX;
                    x = radius;
                } else
                if (x + speedX >= field.getWidth() - radius) {
                    speedX = -speedX;
                    x = field.getWidth() - radius;
                } else
                if (y + speedY <= radius) {
                    speedY = -speedY;
                    y = radius;
                } else
                if (y + speedY >= field.getHeight() - radius) {
                    speedY = -speedY;
                    y = field.getHeight() - radius;
                } else {
                    x += speedX;
                    y += speedY;
                }
                Thread.sleep(16-speed);
            }
        } catch (InterruptedException ex) {
        }
    }

}
