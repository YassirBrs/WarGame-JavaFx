package com.java;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class Program extends Application {
    //les elements de l'interface graphic
private double widthWindow=800;
private double heightWindow=600;
private Pane container=new Pane();
    BackgroundFill myBF = new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(1),null);
Line line=new Line(0,200,widthWindow,200);
Zone zone1=new Zone(0,0,line.getEndX()-50,line.getEndY()-50);
Zone zone2=new Zone(line.getStartX(),line.getStartY(),line.getEndX()-50,heightWindow-50);
// les objets de jeu
private Player player=new Player(zone2);
private List<Monster> monstres=new ArrayList<>();
private List<Balle> balls=new ArrayList<>();
private Arme arme=new Arme(player);
EventHandler<KeyEvent> event=new EventHandler<KeyEvent>() {
    @Override
    public void handle(KeyEvent event) {
        if(event.getCode()== KeyCode.X){
            arme.rotateLeft();
        }
        if(event.getCode()== KeyCode.W){
            arme.rotateRight();
        }
        if(event.getCode()== KeyCode.SPACE){
            Balle ball=new Balle(arme);
            container.getChildren().add(ball.getCorps());
            balls.add(ball);
        }
        if(event.getCode()== KeyCode.LEFT){
            player.getCorps().setTranslateX(player.corps.getTranslateX()-5);
            arme.attachToPlayer(player);
            arme.updateSortie();
        }
        if(event.getCode()== KeyCode.RIGHT){
            player.getCorps().setTranslateX(player.corps.getTranslateX()+5);
            arme.attachToPlayer(player);
            arme.updateSortie();
        }if(event.getCode()== KeyCode.UP){
            player.getCorps().setTranslateY(player.corps.getTranslateY()-5);
            arme.attachToPlayer(player);
            arme.updateSortie();
        }
        if(event.getCode()== KeyCode.DOWN){
            player.getCorps().setTranslateY(player.corps.getTranslateY()+5);
            arme.attachToPlayer(player);
            arme.updateSortie();
        }
    }
};


//Animation Timer

    AnimationTimer animation=new AnimationTimer() {
        @Override
        public void handle(long now) {
            refreshContent();
        }
    };
    private void refreshContent(){
        //parcourir la collection des balles pour mettre a jour leur position

        for (Balle balle:balls){
            for(Monster monstre:monstres){
                if (balle.touch(monstre)){
                    container.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
                    balle.setAlive(false);
                    monstre.setAlive(false);
                }
            }
        }
        monstres.removeIf(GraphicObject::isDead);
        balls.removeIf(GraphicObject::isDead);
        for (Balle balle:balls){
            balle.update();
        }
        if(Math.random()<0.01) {
            Monster monster = new Monster(zone1);
            container.getChildren().add(monster.getCorps());
            monstres.add(monster);
        }
    }
    public static void main(String[] args) {
	// write your code here
        Application.launch(args);
    }
private void createContent(){
        container.setBackground(new Background(myBF));
        container.getChildren().add(line);
        container.getChildren().add(player.getCorps());
        container.getChildren().add(arme.getCorps());
        container.getChildren().add(arme.getSortie());
}
    @Override
    public void start(Stage window) throws Exception {
        window.setWidth(widthWindow);
        window.setHeight(heightWindow);
        window.setTitle("War Game!");
        createContent();
        Scene scene=new Scene(container);
        window.setScene(scene);
        animation.start();
        scene.setOnKeyPressed(event);
        window.show();
    }
}
