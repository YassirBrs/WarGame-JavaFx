package com.java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Player extends GraphicObject {
    public Player(Zone zone) {
        Image image = null;
        try {
            image = new Image(new FileInputStream("GamePic/vatican3.gif"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        corps = new ImageView(image);
        ((ImageView) corps).setX(0);
        ((ImageView) corps).setY(0);

        double x = zone.getX1() + 50 + (zone.getX2() - zone.getX1());
        double y = zone.getY1() + (zone.getY2() - zone.getY1());
        corps.setTranslateX((zone.getX2() - 50) / 2);
        corps.setTranslateY((zone.getY2() -195) );
    }
}
