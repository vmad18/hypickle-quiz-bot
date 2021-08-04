package net.quizbot.me.math;

import java.util.Random;

public class AddSub extends MathTemplate{

    public void sum() {
        double val = 0;
        String q = " Solve: ";
        Random r = new Random();
        int rand = r.nextInt(2) + 2;
        for (int i = 0; i < rand; i++) {
            int a = r.nextInt(500);
            val += a;
            if (i == rand - 1) {
                q = q + a;
            } else {
                q = q + a + " + ";
            }
        }
        setAns(val);
        addQuestion(q);
    }

    public void sub() {
        String q = " Solve: ";
        Random r = new Random();
        double val = r.nextInt(500);
        q += val + " - ";
        int rand = r.nextInt(2) + 2;
        for (int i = 1; i < rand; i++) {
            int a = r.nextInt(500);
            val += a;
            if (i == rand - 1) {
                q = q + a;
            } else {
                q = q + a + " - ";
            }
        }
        setAns(val);
        addQuestion(q);
    }
}
