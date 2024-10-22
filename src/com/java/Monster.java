package com.java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Monster extends GraphicObject {
    public Monster(Zone zone) {
        Image image = null;
        try {
            Image[] imgs = new Image[3];
            imgs[0] = new Image(new FileInputStream("GamePic/biirrd.gif"));
            imgs[1] = new Image(new FileInputStream("GamePic/birrd2.gif"));
            int a=(int)(Math.random()*2);
            image = imgs[a];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        corps = new ImageView(image);
        ((ImageView) corps).setX(0);
        ((ImageView) corps).setY(0);

        double x = zone.getX1() - 50 + (zone.getX2() - zone.getX1()) * Math.random();
        double y = zone.getY1() - 50 + (zone.getY2() - zone.getY1()) * Math.random();
        corps.setTranslateX(x);
        corps.setTranslateY(y);
    }
}
