package net.quizbot.me.utils.mathstuff;

public class Cramer {

    public int x, y, x1, y1;

    public int ans1, ans2;

    public Cramer(int a, int b, int c, int d, int a1, int a2){
        this.x = a;
        this.y = b;
        this.x1 = c;
        this.y1 = d;
        this.ans1 = a1;
        this.ans2 = a2;
    }

    public float solveX(){
        int t = ans1*y1;
        int t1 = y*ans2;
        int w = x*y1;
        int w1 = y*x1;
        return (t-t1)/(w-w1);
    }

    public float solveY(){
        int t = x*ans2;
        int t1 = ans1*x1;
        int w = x*y1;
        int w1 = y*x1;
        return (t-t1)/(w-w1);
    }
}
