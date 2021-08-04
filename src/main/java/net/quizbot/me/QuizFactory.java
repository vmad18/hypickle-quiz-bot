package net.quizbot.me;

import net.quizbot.me.math.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class QuizFactory extends MathTemplate {

    public QuizFactory(String subj) throws InvocationTargetException, IllegalAccessException {
        switch (subj.toLowerCase()) {
            case ("addition/subtract"):
                sumHandle();
                break;
            case ("multiplication/division"):
                multDivHandle();
                break;
            case ("geometry"):
                geoHandle();
                break;
            case ("algebra"):
                algHandle();
                break;
            case ("pre-calculus"):
                pcalcHandle();
                break;
            case ("calculus"):
                calcHandle();
                break;
        }
    }

    /*
     -----------
     Reflections
     -----------
     */

    private void invokeRandomMethod(MathTemplate o) throws InvocationTargetException, IllegalAccessException {
        Random r = new Random();
        Method[] methods = o.getClass().getDeclaredMethods();
        int m = r.nextInt(methods.length);
        methods[m].invoke(o);
        setQuestions(o.getQuestions());
        setAns(o.getAns());
    }

    private void sumHandle() throws InvocationTargetException, IllegalAccessException {
        invokeRandomMethod(new AddSub());
    }

    private void multDivHandle() throws InvocationTargetException, IllegalAccessException {
        invokeRandomMethod(new MultDiv());
    }

    private void geoHandle() throws InvocationTargetException, IllegalAccessException {
        invokeRandomMethod(new Geometry());
    }

    private void algHandle() throws InvocationTargetException, IllegalAccessException {
        invokeRandomMethod(new Algebra());
    }

    private void pcalcHandle() throws InvocationTargetException, IllegalAccessException {
        invokeRandomMethod(new PreCalc());
    }

    private void calcHandle() throws InvocationTargetException, IllegalAccessException {
        invokeRandomMethod(new Calculus());
    }
}
