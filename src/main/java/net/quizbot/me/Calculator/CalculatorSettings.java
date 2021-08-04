package net.quizbot.me.Calculator;

public class CalculatorSettings {

    private boolean isRad;

    private String prevAns;

    public CalculatorSettings(boolean r, String pa){
        this.isRad = r;
        this.prevAns = pa;
    }

    public boolean isRad() {
        return isRad;
    }

    public void setRad(boolean rad) {
        isRad = rad;
    }

    public String getPrevAns() {
        return prevAns;
    }

    public void setPrevAns(String prevAns) {
        this.prevAns = prevAns;
    }
}
