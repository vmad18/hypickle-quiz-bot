package mod.quizbot.me.utils.mathstuff;

import java.util.Random;

public class FunctionList {

    //TODO: stuff and...more stuff

    public String e(){return "e^x";}

    public String rational(){return "(1/x)";}

    public String ln(){return "ln(x)";}

    public String poly(){
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        int degree = r.nextInt(4)+1;
        for(int x=degree-1;x>=0;x--){
            int coeff = r.nextInt(9)+1;
            if(!(x==0)) stringBuilder.append(String.format("%d*x^%d + ",coeff,x));
            else stringBuilder.append(String.format("%d",coeff,x));
        }
        return "("+stringBuilder.toString()+")";
    }

    public String partialFraction(){
        Random r = new Random();
        int x1 = r.nextInt(9)+1;
        int x2 = r.nextInt(9)+1;

        int a1 = r.nextInt(15)+1;
        int b1 = r.nextInt(15)+1;
        int c1 = x1*a1 + x2*b1;
        int coeff = a1*b1;

        int b = (-1*x1+-1*x2);
        int c = (-1*x1*-1*x2);

        String eq = String.format("(%d*x+%d)/(x^2 + %d*x + %d)",coeff,c1,b,c);
        return eq;
    }

}