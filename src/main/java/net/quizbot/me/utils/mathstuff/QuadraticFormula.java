package net.quizbot.me.utils.mathstuff;

public class QuadraticFormula {

    private final int a;
    private final int b;
    private final int c;

    public QuadraticFormula(int a1, int b1, int c1){
        this.a = a1;
        this.b = b1;
        this.c = c1;
    }

    public double plusQuad(){
        return (-b+Math.sqrt(b*b-4*a*c))/(2*a);
    }

    public double negativeQuad(){
        return (-b-Math.sqrt(b*b-4*a*c))/(2*a);
    }
}
