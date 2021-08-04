package net.quizbot.me.utils.mathstuff;

import java.util.Random;

public class Vec {

    private double x, y, z;

    public Vec(double v, double u, double w) {
        this.x = v;
        this.y = u;
        this.z = w;
    }

    public Vec() {
        Random r = new Random();
        setX(r.nextInt(5) + 1);
        setY(r.nextInt(5) + 1);
        setZ(r.nextInt(5) + 1);
    }

    public void setX(double i) {
        this.x = i;
    }

    public void setY(double i) {
        this.y = i;
    }

    public void setZ(double i) {
        this.z = i;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double mag2D() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public double getDir2D() {
        return Math.atan2(y, x);
    }

    public Vec normalize() {
        return new Vec(getX() / mag3D(), getY() / mag3D(), getZ() / mag3D());
    }

    public void multiply(double d) {
        setX(getX() * d);
        setY(getY() * d);
        setZ(getZ() * d);
    }

    public void add(Vec a) {
        setX(getX() + a.getX());
        setY(getY() + a.getY());
        setZ(getZ() + a.getZ());
    }

    public void subtract(Vec a) {
        setX(getX() - a.getX());
        setY(getY() - a.getY());
        setZ(getZ() - a.getZ());
    }

    public double dot(Vec a) {
        return getX() * a.getX() + getY() * a.getY();
    }

    public double dot3D(Vec a) {
        return getX() * a.getX() + getY() * a.getY() + getZ() * a.getZ();
    }

    public double angleBetween(Vec a) {
        return Math.cos((dot(a) / (mag2D() * a.mag2D())));
    }

    public double mag3D() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

}
