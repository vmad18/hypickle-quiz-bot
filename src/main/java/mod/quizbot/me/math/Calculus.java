package mod.quizbot.me.math;

import me.quizbot.pack.utils.mathstuff.*;
import mod.quizbot.me.utils.mathstuff.*;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class Calculus extends MathTemplate {

    private List<String> equation;

    private final FunctionList funcs = new FunctionList();

    private final TrigFunction trigs = new TrigFunction();

    private final Random r = new Random();

    private final DecimalFormat df = new DecimalFormat("###.####");

    public void funcIntegral() throws InvocationTargetException, IllegalAccessException {
        int a = r.nextInt(10);
        int b = r.nextInt(10);
        equation = Functions.createRandomFunction(funcs);
        Integrals integrals = new Integrals(a, b, 550);

        setAns(Double.parseDouble(df.format(integrals.simpsons(equation))));
        addQuestion(" Integrate ∫ " + Functions.equationString(equation) + " dx over the intervals [" + a + "," + b + "]");
        addQuestion(" Round your answer to 4 decimals");
    }

    public void trigIntegral() throws InvocationTargetException, IllegalAccessException {
        int a = r.nextInt(360);
        int b = r.nextInt(360);
        equation = Functions.createRandomFunction(trigs);
        Integrals integrals = new Integrals(a * Math.PI / 180, b * Math.PI / 180, 550);

        setAns(Double.parseDouble(df.format(integrals.simpsons(equation))));
        addQuestion(" Integrate ∫ " + Functions.equationString(equation) + " dx over the intervals [" + a + "," + b + "]");
        addQuestion(" Round your answer to 4 decimals.");
    }

    public void funcDerivative() throws InvocationTargetException, IllegalAccessException {
        int a = r.nextInt(10);
        equation = Functions.createRandomFunction(funcs);
        Derivatives derivatives = new Derivatives();

        setAns(Double.parseDouble(df.format(derivatives.slope(a, equation))));
        addQuestion(" Take the derivative: d/dx " + Functions.equationString(equation) + " at point " + a);
        addQuestion(" Put your answer to three decimals.");
    }

    public void trigDerivative() throws InvocationTargetException, IllegalAccessException {
        int a = r.nextInt(360);
        equation = Functions.createRandomFunction(trigs);
        Derivatives derivatives = new Derivatives();

        setAns(Double.parseDouble(df.format(derivatives.slope(a * Math.PI / 180, equation))));
        addQuestion(" Take the derivative: d/dx " + Functions.equationString(equation) + " at point " + a);
        addQuestion(" Put your answer to three decimals.");
    }

}
