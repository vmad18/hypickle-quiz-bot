package net.quizbot.me.utils.mathstuff;

import net.quizbot.me.Calculator.Calculator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Functions {

    public static String equationString(List<String> eq){
        StringBuilder build = new StringBuilder();
        for(String i:eq){
            build.append(i);
        }
        return build.toString();
    }

    public static List<String> createRandomFunction(Object o) throws InvocationTargetException, IllegalAccessException {
        Method[] m = o.getClass().getDeclaredMethods();
        List<Method> methods = new ArrayList<>();
        List<String> operators = Arrays.asList("+", "-", "/", "*");
        Random r = new Random();
        List<String> equation = new ArrayList<>();
        int terms = r.nextInt(2) + 1;
        for (int i = 0; i < terms; i++) {
            int rand = r.nextInt(m.length);
            methods.add(m[rand]);
        }
        for (int i = 0; i < methods.size() - 1; i++) {
            equation.add((String) methods.get(i).invoke(o));
            equation.add(operators.get(r.nextInt(operators.size())));
        }
        equation.add((String) methods.get(methods.size()-1).invoke(o));
        return equation;
    }

    public static double evaluateFunction(double num, List<String> equation) {
        List<String> eq = new ArrayList(equation);
        for(String i:eq){eq.set(eq.indexOf(i),i.replaceAll("x",String.valueOf(num)));}
        StringBuilder build = new StringBuilder();
        eq.forEach(build::append);

        Calculator calculator = new Calculator(build.toString().replaceAll("\\s"," "),true);
        return Double.parseDouble(calculator.str.get(0));
    }

}
