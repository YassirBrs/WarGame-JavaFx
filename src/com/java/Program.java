package com.java;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.shape.Line;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Program extends Application {
    //BackGround
    // create a input stream
    FileInputStream input;

    {
        try {
            input = new FileInputStream("GamePic/backk.gif");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // create a image
    Image image = new Image(input);

    // create a background image
    BackgroundImage backgroundimage = new BackgroundImage(image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    // create Background
    Background background = new Background(backgroundimage);

    //les elements de l'interface graphic
    private double widthWindow = 1200;
    private double heightWindow = 945;
    public Rectangle porte = new Rectangle(widthWindow - 23, 350, 0, 0);
    //    public  AutoShoot autoShoot=new AutoShoot(Player)
    private Pane container = new Pane();
    private GridPane gp = new GridPane();
    HBox toolBar = new HBox();
    public int nbBallesTires = 0;
    public static int nbMonstresTues = 0;
    private int nbLife = 5;
    private Text txtMonstresTues = new Text(" Monstres Killed : " + nbBallesTires + "               ");
    private Text txtBallesTires = new Text(" Balls Shooted : " + nbMonstresTues + "               ");
    private Text txtLife = new Text(" Life ( " + nbLife + " )");
    private Boolean isGameOver = false;

    private double proba_monster = 1909999999;

    public double getProba_monster() {
        return proba_monster;
    }

    public void setProba_monster(double proba_monster) {
        this.proba_monster = proba_monster;
    }

    // timer
    public static int heurs = 00;
    public static int minutes = 00;
    public static int seconds = 00;



    private static Text time = new Text(" Time (" + heurs + ":" + minutes + ":" + seconds + " )               ");

    BackgroundFill myBF = new BackgroundFill(Color.BLUEVIOLET, new CornerRadii(1), null);
    Line line = new Line(0, 350, widthWindow, 350);
    Zone zone1 = new Zone(0, 50, line.getEndX() - 50, line.getEndY() - 50);
    Zone zone2 = new Zone(line.getStartX(), line.getStartY(), line.getEndX() - 50, heightWindow - 50);
    // les objets de jeu
    private Player player = new Player(zone2);
    private List<Monster> monstres = new ArrayList<>();
    private List<Balle> balls = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private Arme arme = new Arme(player);
    private List<Arme> arme_enemy = new ArrayList<>();
    List<Balle> balls_enemy = new ArrayList<>();



    //Animation Timer of Gameover
    AnimationTimer shooting = new AnimationTimer() {
        private long lastUpdate = 0;

        @Override
        public void handle(long now) {
            if (lastUpdate == 0) {
                lastUpdate = now;
                return;
            }

            if (now - lastUpdate > 300000000) {
                if (player.isAlive()) {
//                    Balle ball = new Balle(arme);
                    Weapon weapon=new Weapon(arme);

                    container.getChildren().add(weapon.getCorps());
                    weapons.add(weapon);
                    //AutoShoot autoShoot = new AutoShoot(player);
//                    arme.resetArm(autoShoot.getAngel());
                    new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            if (player.isAlive()){
                                weapon.rotate();
                            }
                        }
                    }.start();
                }
                nbBallesTires++;
                txtBallesTires.setText(" Balles shooted : " + nbBallesTires + "               ");
                lastUpdate = now;
            }
        }
    };



    AnimationTimer animation = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (isGameOver) {
                animation.stop();
                VBox cc = new VBox();
                Image gameOverImage = null;
                try {
                    gameOverImage = new Image(new FileInputStream("GamePic/gameOver.png"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ImageView gameOver = new ImageView();
                gameOver.setImage(gameOverImage);

                Image replayImage = null;
                try {
                    replayImage = new Image(new FileInputStream("GamePic/play-again.png"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ImageView replay = new ImageView();
                replay.setImage(replayImage);
                replay.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        for (Monster monstre : monstres) {
                            monstre.setAlive(false);
                            container.getChildren().removeAll(monstre.getCorps());

                        }
                        for (Balle balle : balls) {
                            container.getChildren().remove(balle.getCorps());
                        }
                        for (Balle balls : balls_enemy) {
                            container.getChildren().remove(balls.getCorps());
                        }
                        for (Weapon weapon : weapons) {
                            container.getChildren().remove(weapon.getCorps());
                        }
                        for (Arme armeShoot : arme_enemy) {
                            container.getChildren().remove(armeShoot.getCorps());
                        }

                        heurs = 0;
                        minutes = 0;
                        seconds = 0;
                        nbLife = 5;
                        monstres.clear();
                        balls.clear();
                        balls_enemy.clear();
                        weapons.clear();
                        nbMonstresTues = 0;
                        txtMonstresTues.setText(" Monstres killed : 0                ");
                        nbBallesTires = 0;
                        txtBallesTires.setText(" Balles shooted : 0                ");
                        txtLife.setText(" Life ( 5 )");
                        player.setAlive(true);
                        isGameOver = false;
                        container.getChildren().remove(cc);
                        animation.start();
                    }
                });
                replay.setFitWidth(200);
                replay.setFitHeight(53);
//                replay.setStyle(se);
                replay.setLayoutY(replay.getLayoutY() + 10);
                Text finalTime = new Text("              Time ( " + heurs + ":" + minutes + ":" + seconds + " )               ");
                Text finalMonstre = new Text("              Monstres Killed : " + nbMonstresTues + "               ");
                finalTime.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                finalTime.setFill(Color.BLACK);
                Text finalBalles = new Text("              Balles Shooted : " + nbBallesTires + "               ");
                finalMonstre.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                finalMonstre.setFill(Color.BLACK);
                finalBalles.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
                finalBalles.setFill(Color.BLACK);
                cc.setPrefWidth(widthWindow);
                cc.setPrefHeight(heightWindow);
                cc.setAlignment(Pos.CENTER);
                cc.setBackground(background);
//                cc.setStyle("-fx-padding: 5 0 5 10;-fx-background-color: #000000;");
                cc.getChildren().addAll(gameOver, finalTime, finalMonstre, finalBalles, replay);
                container.getChildren().addAll(cc);
            } else {
                refreshContent();
            }
        }
    };
    EventHandler<KeyEvent> event = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.X) {
                arme.rotateLeft();

            }
            if (event.getCode() == KeyCode.W) {
                arme.rotateRight();
            }
            if (event.getCode() == KeyCode.LEFT) {
                player.getCorps().setTranslateX(player.corps.getTranslateX() - 5);
                arme.attachToPlayer(player);
                arme.updateSortie();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                player.getCorps().setTranslateX(player.corps.getTranslateX() + 5);
                arme.attachToPlayer(player);
                arme.updateSortie();
            }
            if (event.getCode() == KeyCode.UP) {
                player.getCorps().setTranslateY(player.corps.getTranslateY() - 5);
                arme.attachToPlayer(player);
                arme.updateSortie();
            }
            if (event.getCode() == KeyCode.DOWN) {
                player.getCorps().setTranslateY(player.corps.getTranslateY() + 5);
                arme.attachToPlayer(player);
                arme.updateSortie();
            }
        }
    };
    //follow mouse
    AnimationTimer followMouse =new AnimationTimer() {
        @Override
        public void handle(long now) {
//            System.out.println("position player = "+player.getCorps().getTranslateX());
//            System.out.println("position mouse = "+MouseInfo.getPointerInfo().getLocation().x);

            player.getCorps().setTranslateX(MouseInfo.getPointerInfo().getLocation().x-400) ;

            arme.attachToPlayer(player);
            arme.updateSortie();

        }
    };
    private void refreshContent() {
        //parcourir la collection des balles pour mettre a jour leur position
        //eliminer les monsters
        for (Weapon weapon : weapons) {
            for (Monster monstre : monstres) {

                if (weapon.touch(monstre)) {
                    container.getChildren().removeAll(weapon.getCorps(), monstre.getCorps());
                    weapon.setAlive(false);
                    monstre.setAlive(false);
                    nbMonstresTues++;
                    if(nbMonstresTues%15==0){
                        nbLife++;
                    }
                }
                txtLife.setText(" Life ( " + nbLife + " )");
                txtMonstresTues.setText(" Monstres Killed : " + nbMonstresTues + "               ");


            }
            //detruire les balles du monster
            for (Arme arme_shoot : arme_enemy) {
                for (Monster monstre : monstres) {
                    if (weapon.touch(monstre)) {
                        if (arme_shoot.isAttachedTo(monstre)) {
                            container.getChildren().remove(arme_shoot.getCorps());
                        }
                    }
                }
            }
        }

        //aimbot monster
        for (Balle balls : balls_enemy) {

            balls.update();
            for (Monster monstre : monstres) {
                for (Arme arme_shoot : arme_enemy) {
                    AutoShoot autoShoot = new AutoShoot(player, monstre);
                    arme_shoot.rotateArme(autoShoot.getAngel());
                }
            }

            if (balls.touch(player)) {
                container.getChildren().remove(balls.getCorps());
                balls.setAlive(false);
                Sounds.SoundPlayerHit();
                nbLife--;
                if (nbLife <= 0) {
                    player.setAlive(false);
                    for (Monster monstre : monstres) {
                        monstre.setAlive(false);
                        container.getChildren().remove(monstre.getCorps());
                    }
                    isGameOver = true;
                }
                txtLife.setText(" Life ( " + nbLife + " )");
            }
        }

        monstres.removeIf(GraphicObject::isDead);
        balls.removeIf(GraphicObject::isDead);
        balls_enemy.removeIf(GraphicObject::isDead);
        weapons.removeIf(GraphicObject::isDead);


        for (Weapon weapon : weapons) {
            weapon.update();
        }
        new AnimationTimer(){
            @Override
            public void handle(long now) {

                for(Monster monster:monstres){
                    AutoShoot autoShoot = new AutoShoot(player, monster);
                    Arme armed=new Arme(monster);
                    armed.rotateArme(autoShoot.getAngel() + 6);

                }
            }
        }.start();
        if (Math.random() < 0.005) {
            Monster monster = new Monster(zone1);
            AutoShoot autoShoot = new AutoShoot(player, monster);
            Arme armed = new Arme(monster);
            armed.attachToMonster(monster);
            container.getChildren().add(monster.getCorps());
            container.getChildren().add(armed.getCorps());
            monstres.add(monster);
//            arme.rotateArme(autoShoot.getAngel() + 180);
            armed.rotateArme(autoShoot.getAngel() +6);
            arme_enemy.add(armed);

            new AnimationTimer() {
                private long lastUpdate = 0;

                @Override
                public void handle(long now) {
                    if (lastUpdate == 0) {
                        lastUpdate = now;
                        return;
                    }
//                    if(minutes >=1){
//                        setProba_monster(1090000000);
//                    }
                    //test
                    if(seconds >=10){
                        setProba_monster(1090000000);
                    }
                    if (now - lastUpdate > proba_monster) {
//                        armed.rotateArme(autoShoot.getAngel() +6);
                        Balle ball = new Balle(armed);
                        //ball.MakeItMove(-autoShoot.getAngel());
                        if (monster.isAlive()) {
                            container.getChildren().add(ball.getCorps());
                            balls_enemy.add(ball);
                        }

                        lastUpdate = now;
                    }
                }
            }.start();

        }

    }

    public static void main(String[] args) {
        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                time.setText((" Time  " + heurs + ":" + minutes + ":" + seconds + "               "));
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
                if (minutes == 60) {
                    heurs++;
                    minutes = 0;
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
        // write your code here
        Application.launch(args);
    }

    private void createContent() {

        container.getChildren().add(gp);
        line.setStrokeWidth(0);
        container.getChildren().add(line);
        porte.setFill(Color.GREEN);
        container.getChildren().add(porte);
        container.getChildren().add(player.getCorps());
        container.getChildren().add(arme.getCorps());
        container.getChildren().add(arme.getSortie());

        toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setPrefHeight(25);
        toolBar.setMinHeight(25);
        toolBar.setMaxHeight(25);
        toolBar.getChildren().add(new topBar());
        container.getChildren().add(toolBar);
    }
    class topBar extends HBox {


        public topBar() {
            txtBallesTires.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            txtBallesTires.setFill(Color.WHITE);
            txtMonstresTues.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            txtMonstresTues.setFill(Color.WHITE);
            time.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            time.setFill(Color.WHITE);
            txtLife.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            txtLife.setFill(Color.WHITE);
            this.getChildren().addAll(txtMonstresTues, txtBallesTires, time, txtLife);
            this.setStyle("-fx-padding: 5 0 5 0;-fx-background-color: #000000  ;");
            this.setMinWidth(widthWindow);
        }
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setWidth(widthWindow);
        window.setHeight(heightWindow);
        window.setTitle("War Game!");

        // set background
        container.setBackground(background);

        createContent();

        HBox toolBar = new HBox();
        toolBar.setAlignment(Pos.CENTER_RIGHT);
        toolBar.setPrefHeight(25);
        toolBar.setMinHeight(25);
        toolBar.setMaxHeight(25);
        toolBar.getChildren().add(new topBar());
        container.getChildren().add(toolBar);


        Scene scene = new Scene(container);
        scene.setCursor(javafx.scene.Cursor.NONE);
        window.setScene(scene);

        followMouse.start();
        shooting.start();
        animation.start();
//        axer.start();
        scene.setOnKeyPressed(event);
        window.show();
    }
}
