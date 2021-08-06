package mod.quizbot.me.utils.mathstuff;

import java.util.List;

public class Derivatives {

    final private double h = 1e-8;

    public double slope(double num, List<String> eq) {
        return (Functions.evaluateFunction(num+h,eq)-Functions.evaluateFunction(num,eq))/h;
    }

}
