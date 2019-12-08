package com.java;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme {
    private Rectangle corps=new Rectangle(-5,0,10,50);

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

    private Circle sortie=new Circle(-6,0,5);
    public Arme(GraphicObject player){
        corps.setTranslateX(player.getCorps().getTranslateX());
        corps.setTranslateY(player.getCorps().getTranslateY());
        corps.setFill(Color.DARKGREEN);
        sortie.setFill(Color.RED);
        updateSortie();
    }
    public void attachToPlayer(Player player){
        corps.setTranslateX(player.corps.getTranslateX());
        corps.setTranslateY(player.corps.getTranslateY());
    }
    //////////////////////////////////////////////////
    public void attachToMonster(Monster monster){
        corps.setTranslateX(monster.corps.getTranslateX());
        corps.setTranslateY(monster.corps.getTranslateY());
    }
    //////////////////////////////////////////////////
    public boolean isAttachedTo(Monster monster){
        if(corps.getTranslateX() == monster.corps.getTranslateX() && corps.getTranslateY() == monster.corps.getTranslateY()) {
            return true;
        }
        return false;
    }
    //////////////////////////////////////////////////
    public void updateSortie(){
        sortie.setCenterX(corps.getTranslateX());
        sortie.setCenterY(corps.getTranslateY()+25);

    }
    //si on X:tourner a droite
    //si on Y:tourner a gauche

    public void rotateRight(){
        corps.setRotate(corps.getRotate()-5);
    }public void rotateLeft(){
        corps.setRotate(corps.getRotate()+5);
    }
    public double getRotate(){
        return corps.getRotate()-90;
    }
}
