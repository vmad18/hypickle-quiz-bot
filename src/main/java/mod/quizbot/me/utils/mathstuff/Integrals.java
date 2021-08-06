package mod.quizbot.me.utils.mathstuff;

import java.util.ArrayList;
import java.util.List;

public class Integrals {

    private final double a;

    private final double b;

    private final double n;

    public Integrals(double a1, double b1, double n1) {
        this.a = a1;
        this.b = b1;
        this.n = n1;
    }

    /**
     Simpson's Rule to approximate definite integrals
     **/
    public double simpsons(List<String> eq) {
        List<String> clone = new ArrayList(eq);

        double h = (b - a) / n;

        double[] x = new double[800];
        double[] y = new double[800];

        for (int i = 0; i <= n; i++) {
            x[i] = a + i * h;
            y[i] = Functions.evaluateFunction(x[i], clone);
        }

        double summation = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0 || i == n)
                summation += y[i];
            else if (i % 2 != 0)
                summation += 4 * y[i];
            else
                summation += 2 * y[i];
        }
        summation = summation * (h / 3);
        double res = Math.round(100000 * summation);
        return res / 100000;
    }
}
