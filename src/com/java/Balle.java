package com.java;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Balle extends GraphicObject {
    private Point2D direction=new Point2D(0,0);
    public Balle(Arme arme){
        corps=new Circle(-6,0,4, Color.YELLOW);
        corps.setTranslateX(arme.getCorps().getTranslateX());
        corps.setTranslateY(arme.getCorps().getTranslateY());
        updateDirection(arme.getRotate());

    }
    public void updateDirection(double rotation){
        Point2D p;
        double x=Math.cos(Math.toRadians(rotation));
        double y=Math.sin(Math.toRadians(rotation));
        p=new Point2D(x,y);
        direction= p.normalize().multiply(7);
    }
    //////////////////////////////////////////////////
    public void MakeItMove(double rotation)
    {
        Point2D p;
        double x=Math.cos(Math.toRadians(rotation));
        double y=Math.sin(Math.toRadians(rotation));
        p=new Point2D(x,y);
        direction= p.normalize().multiply(9);
    }
    //////////////////////////////////////////////////
    public void update(){
        corps.setTranslateX(corps.getTranslateX()+direction.getX());
        corps.setTranslateY(corps.getTranslateY()+direction.getY());
    }

    public void rotateRight(){
        corps.setRotate(corps.getRotate()-5);
    }public void rotateLeft(){
        corps.setRotate(corps.getRotate()+5);
    }
}
