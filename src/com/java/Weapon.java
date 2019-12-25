package com.java;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;

public class Weapon extends GraphicObject{
    private Point2D direction = new Point2D(0, 0);

    public Weapon(Arme m){
        Image image = null;
        try {
            image = new Image(new FileInputStream("GamePic/axe1.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        corps = new ImageView(image);
        rotate();
        ((ImageView)corps).setX(0);
        ((ImageView)corps).setY(0);
        ((ImageView)corps).setFitWidth(27);
        ((ImageView)corps).setFitHeight(27);
        corps.setTranslateX(m.getCorps().getTranslateX()+10);
        corps.setTranslateY(m.getCorps().getTranslateY()+10);
        updateDirection(m.getRotate());
    }

    private void updateDirection(double rotation){
        Point2D p;
        double x = Math.cos(Math.toRadians(rotation));
        double y = Math.sin(Math.toRadians(rotation));
        p = new Point2D(x, y);
        direction = p.normalize().multiply(7);
    }

    public void update(){
        corps.setTranslateX(corps.getTranslateX() + direction.getX());
        corps.setTranslateY(corps.getTranslateY() + direction.getY());
    }
    public void rotate(){
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                corps.setRotate(45);
            }


//            RotateTransition rotation = new RotateTransition(Duration.seconds(2), corps.setRotate(););
//        rotation.setCycleCount(Animation.INDEFINITE);
//        rotation.setByAngle(360);
        }.start();
    }
}
