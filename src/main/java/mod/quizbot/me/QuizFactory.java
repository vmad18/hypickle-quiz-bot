package mod.quizbot.me;

import me.quizbot.pack.math.*;
import mod.quizbot.me.math.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class QuizFactory extends MathTemplate {

    public QuizFactory(String subj) throws InvocationTargetException, IllegalAccessException {
        switch (subj.toLowerCase()) {
            case ("addition/subtract"):
                invokeRandomMethod(new AddSub());
                break;
            case ("multiplication/division"):
                invokeRandomMethod(new MultDiv());
                break;
            case ("geometry"):
                invokeRandomMethod(new Geometry());
                break;
            case ("algebra"):
                invokeRandomMethod(new Algebra());
                break;
            case ("pre-calculus"):
                invokeRandomMethod(new PreCalc());
                break;
            case ("calculus"):
                invokeRandomMethod(new Calculus());
                break;
        }
    }

    /**
     -------------------
     Reflection Handling
     -------------------
     **/

    private void invokeRandomMethod(MathTemplate o) throws InvocationTargetException, IllegalAccessException {
        Random r = new Random();
        Method[] methods = o.getClass().getDeclaredMethods();
        int m = r.nextInt(methods.length);
        methods[m].invoke(o);
        setQuestions(o.getQuestions());
        setAns(o.getAns());
    }
}
