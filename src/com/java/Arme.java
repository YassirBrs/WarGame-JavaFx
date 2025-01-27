package com.java;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.TimeUnit;


public class Arme {
    private Rectangle corps = new Rectangle(-5, 0, 0, 0);

    public Rectangle getCorps() {
        return corps;
    }

    public void setCorps(Rectangle corps) {
        this.corps = corps;
    }

    public Circle getSortie() {
        return sortie;
    }

    public void setSortie(Circle sortie) {
        this.sortie = sortie;
    }

    private Circle sortie = new Circle();

    public Arme(GraphicObject player) {
        corps.setTranslateX(player.getCorps().getTranslateX());
        corps.setTranslateY(player.getCorps().getTranslateY());
        corps.setFill(Color.DARKGREEN);
        sortie.setFill(Color.RED);
        updateSortie();
    }

    public void attachToPlayer(Player player) {
        corps.setTranslateX(player.corps.getTranslateX());
        corps.setTranslateY(player.corps.getTranslateY());
    }

    //////////////////////////////////////////////////
    public void attachToMonster(Monster monster) {
        corps.setTranslateX(monster.corps.getTranslateX());
        corps.setTranslateY(monster.corps.getTranslateY());
    }

    //////////////////////////////////////////////////
    public boolean isAttachedTo(Monster monster) {
        if (corps.getTranslateX() == monster.corps.getTranslateX() && corps.getTranslateY() == monster.corps.getTranslateY()) {
            return true;
        }
        return false;
    }

    //////////////////////////////////////////////////
    public void updateSortie() {
        sortie.setCenterX(corps.getTranslateX());
        sortie.setCenterY(corps.getTranslateY() );

    }

    //si on X:tourner a droite
    //si on Y:tourner a gauche

    public void rotateRight() {
        corps.setRotate(corps.getRotate() - 25);
    }

    public void rotateLeft() {
        corps.setRotate(corps.getRotate() + 25);
    }

    public double getRotate() {
        return corps.getRotate() - 90;
    }

    public void rotateArme(double angel) {
        corps.setRotate(-angel+3);
    }

    public void resetArm(double angel) {
        corps.setRotate(0);

    }
}

