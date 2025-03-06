package org.polyfrost.example.testmacro.utils;

public class posXYZ {
    private double x = 0D;
    private double y = 0D;
    private double z = 0D;

    public void printer() {
        System.out.println("x=" + x + ", y=" + y + ", z=" + z);
    }

    public posXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}