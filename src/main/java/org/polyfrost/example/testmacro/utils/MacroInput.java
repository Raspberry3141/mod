package org.polyfrost.example.testmacro.utils;

public class MacroInput {
    private boolean W = false;
    private boolean A = false;
    private boolean S = false;
    private boolean D = false;
    private boolean Jump = false;
    private boolean Sneak = false;
    private boolean Sprint = false;
    private float angle = 0.0F;


    public void setW(boolean W) {
        this.W = W;
    }

    public void setA(boolean A) {
        this.A = A;
    }
    public void setS(boolean S) {
        this.S = S;
    }
    public void setD(boolean D) {
        this.D = D;
    }
    public void setJump(boolean Jump) {
        this.Jump = Jump;
    }
    public void setSneak(boolean Sneak) {
        this.Sneak = Sneak;
    }
    public void setSprint(boolean Sprint) {
        this.Sprint = Sprint;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isW() {
        return W;
    }

    public boolean isA() {
        return A;
    }
    public boolean isS() {
        return S;
    }
    public boolean isD() {
        return D;
    }
    public boolean isJump() {
        return Jump;
    }
    public boolean isSneak() {
        return Sneak;
    }
    public boolean isSprint() {
        return Sprint;
    }
    public float getAngle() {
        return angle;
    }

}