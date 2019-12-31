package com.java;

public class AutoShoot {
    public Player p;
    public Arme m;

    public double getAngel() {
        return angel;
    }

    public void setAngel(double angel) {
        this.angel = angel;
    }

    //    public double x;
    private double angel;
    public double z;
    public double y;
    public double zy;
    public double x;
    public double t;

    public AutoShoot(Player p) {
        this.p = p;
    }

    public AutoShoot(Player p, Arme m) {
        this.p = p;
        this.m = m;
        this.z = m.getCorps().getTranslateY() - p.getCorps().getTranslateY();
        this.y = m.getCorps().getTranslateX() - p.getCorps().getTranslateX();
        this.t = (float)Math.atan2(y , z);
        this.setAngel((float) Math.toDegrees(t));
//        System.out.println(angel);
    }


//    public double getAngell(double z){
//        double a=Math.acos(z/(x));
//        return this.angel=a;
//    }
}
