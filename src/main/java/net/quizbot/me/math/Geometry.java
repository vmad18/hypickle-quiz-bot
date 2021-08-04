package net.quizbot.me.math;

import java.util.Random;

public class Geometry extends MathTemplate {

    private final Random r = new Random();

    public void circleArea() {
        int radius = r.nextInt(29) + 1;
        setAns(3.14 * radius * radius);
        addQuestion(" What is the area of a circle with radius " + radius + ". Use 3.14 as an approximation of PI.");
    }

    public void circumphrence() {
        int radius = r.nextInt(29) + 1;
        setAns(3.14 * 2 * radius);
        addQuestion(" What is the circumference of a circle with radius " + radius + ". Use 3.14 as an approximation of PI.");
    }

    public void pCircumphrence() {
        int radius = r.nextInt(29) + 1;
        int deg = r.nextInt(360);
        setAns(Math.floor(Math.toRadians(deg) * radius));
        addQuestion(" What is the circumference of a circle with radius " + radius + " and arc measure of " + deg + ". Use 3.14 as an approximation of PI and round your answer down.");
    }

    public void pCircleArea() {
        int radius = r.nextInt(29) + 1;
        int deg = r.nextInt(360);
        setAns(Math.floor(Math.toRadians(deg) * .5 * radius * radius));
        addQuestion(" What is the area of a circle with radius " + radius + " and arc measure of " + deg + ". Use 3.14 as an approximation of PI and round your answer down.");
    }

    public void arcMeasure() {
        int ia = r.nextInt(359) + 1;
        setAns(2 * ia);
        addQuestion(" What is the arc measure of a circle with an inscribed angle of measure " + ia + ".");
    }

    public void cylinder() {
        int h = r.nextInt(10) + 1;
        int rad = r.nextInt(15) + 1;
        setAns(3.14 * rad * rad * h);
        addQuestion(" What is the volume of a cylinder with radius " + rad + " and height " + h + ". Use 3.14 as an approximation of PI.");
    }

    public void sphere() {
        int radius = r.nextInt(10) + 1;
        setAns(1.3 * 3.14 * radius * radius * radius);
        addQuestion(" What is the volume of a sphere with radius " + radius + ". Use 3.14 as an approximation of PI and 1.6 for 4/3.");
    }

    public void cone() {
        int radius = r.nextInt(10) + 1;
        int height = r.nextInt(10) + 1;
        setAns(.333 * 3.14 * height * radius * radius);
        addQuestion(String.format(" What is the volume of a cone with radius %d and height %d", radius, height));
        addQuestion(String.format(" Use .333 for 1/3 and 3.14 for pi"));
    }

    public void thirtyNinty() {
        int hyp = r.nextInt(7) + 1;
        int x = hyp / 2;
        setAns(1.73 * x + x);
        addQuestion(" What is the sum of the legs of a 30-60-90 triangle with hypotenuse length " + hyp + ". Use 1.73 as a substitute for sqrt(3).");
    }

    public void fourtyFive() {
        int leg = r.nextInt(7) + 1;
        setAns(leg * 1.4);
        addQuestion(" What is the hypotenuse of a 45-45-90 triangle of leg length " + leg + ". Use 1.4 as a substitute for sqrt(2).");
    }

    public void pyth() {
        int a = r.nextInt(20) + 1;
        int b = r.nextInt(20) + 1;
        setAns(Math.floor(Math.sqrt(a * a + b * b)));
        addQuestion(" What is the hypotenuse of a right triangle with legs " + a + " & " + b + ". Round your answer down.");
    }

    public void tetrahedron() {
        int sideL = r.nextInt(7) + 1;
        setAns(sideL * sideL * sideL * 1.4 / 12);
        addQuestion(" What is the volume of a regular tetrahedron with side length " + sideL + ". Use 1.4 for sqrt(2).");
    }

    /*

    Give harder problems soon ig idk

     */
}
