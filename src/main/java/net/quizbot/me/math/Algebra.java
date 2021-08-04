package net.quizbot.me.math;

import net.quizbot.me.utils.mathstuff.Cramer;
import net.quizbot.me.utils.mathstuff.QuadraticFormula;

import java.util.Random;

public class Algebra extends MathTemplate{

    private final Random r = new Random();

    public void systemEquations(){

        int x1 = r.nextInt(19)+1;
        int y1 = r.nextInt(19)+1;
        int x2 = r.nextInt(19)+1;
        int y2 = r.nextInt(19)+1;
        int ans1 = r.nextInt(40)+1;
        int ans2 = r.nextInt(40)+1;
        Cramer c = new Cramer(x1,y1,x2,y2,ans1,ans2);
        setAns(c.solveX());
        addQuestion(String.format(" Solve for 'x' in this system of equation: %dx+%dy=%d | %dx+%dy=%d.",x1,y1,ans1,x2,y2,ans2));
        addQuestion(" Keep your answer in fraction form if needed.");
    }

    public void solveEquation(){
        int constant = r.nextInt(50)+1;
        int coeff = r.nextInt(20)+1;
        int x = r.nextInt(15)+1;
        int val = constant + coeff*x;
        setAns(x);
        addQuestion(String.format(" Solve for 'x' %dx+%d=%d",coeff,constant,val));
    }

    public void quadratic(){
        int x1 = r.nextInt(20)+1;
        int x2 = r.nextInt(20)+1;
        int a = r.nextInt(2)+1;
        long x1x2 = a*-x1*-x2;
        int addStuff = a*(-x1+-x2);
        setAns(Math.max(x1,x2));
        addQuestion(String.format(" What is the largest 0 in this quadratic %dx^2 + %dx + %d",a,addStuff,x1x2));
    }

    public void harderQuadratic(){
        int a,b,c;
        do {
            a = r.nextInt(4)+1;
            b = r.nextInt(10)+1;
            c = r.nextInt(200)+1;
        } while (b*b<=4*a*c);
        QuadraticFormula q = new QuadraticFormula(a,b,c);
        setAns(Math.floor(Math.max(q.plusQuad(),q.negativeQuad())));
        addQuestion(String.format(" What is the largest 0 in this quadratic %dx^2 + %dx + %d | Round your answer down.",a,b,c));
    }

}
