package mod.quizbot.me.math;

import java.util.Random;

public class MultDiv extends MathTemplate{

    private final Random r = new Random();

    public void doMult(){
        int a=r.nextInt(1000), b=r.nextInt(1000);
        setAns(a*b);
        addQuestion(" Solve: " + a + "*" + b);
    }

    public void doDiv(){
        int a=r.nextInt(1000), b=r.nextInt(1000);
        setAns(a);
        addQuestion(" Solve: " + a*b + "/" + b);
    }

}
