package com.java;

public class AutoShoot {
    public Player p;
    public Monster m;

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

    public AutoShoot(Player p, Monster m) {
        this.p = p;
        this.m = m;
        this.z = p.getCorps().getTranslateY() - m.getCorps().getTranslateY();
        this.y = p.getCorps().getTranslateX() - m.getCorps().getTranslateX();
//        this.zy=(int)Math.pow(y,2)-(int)Math.pow(z,2);
//        if (this.zy>=0){
//            this.x=Math.sqrt(zy);
//        }else{
//            this.x=Math.sqrt(-zy);
//            this.x=-this.x;
//        }
        this.t = (float)Math.atan2(y , z);
        this.setAngel((float) Math.toDegrees(t));
        System.out.println(angel);
    }


//    public double getAngell(double z){
//        double a=Math.acos(z/(x));
//        return this.angel=a;
//    }
}