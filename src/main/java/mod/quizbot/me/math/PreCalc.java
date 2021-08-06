package mod.quizbot.me.math;

import mod.quizbot.me.utils.mathstuff.Matrices;
import mod.quizbot.me.utils.mathstuff.Vec;


public class PreCalc extends MathTemplate {

    public void vectorMag() {
        Vec vec = new Vec();
        setAns(vec.mag2D());
        addQuestion(String.format(" What is the magnitude of vector <%d,%d>?", vec.getX(), vec.getY()));
    }

    public void vectorDot() {
        Vec vec1 = new Vec();
        Vec vec2 = new Vec();
        setAns(vec1.dot(vec2));
        addQuestion(String.format(" Find the dot product of the vectors <%d,%d> & <%d,%d>.", vec1.getX(), vec1.getY(), vec2.getX(), vec2.getY()));
    }

    public void normalizedVector() {
        Vec vec = new Vec();
        setAns(Math.floor(vec.normalize().getX()));
        addQuestion(String.format(" Find the x component of the vector: <%d,%d>", vec.getX(), vec.getY()));
    }

    public void vectorDir() {
        Vec vec = new Vec();
        setAns(Math.floor(Math.toDegrees(vec.getDir2D())));
        addQuestion(String.format(" What is the direction of the vector: <%d,%d> in degrees?", vec.getX(), vec.getY()));
        addQuestion(String.format(" Round your answer down."));
    }

    public void multVector() {
        Vec vec = new Vec();
        vec.multiply(2);
        setAns(vec.getX());
        addQuestion(String.format(" What is the x component of vector: 2 * <%d,%d>", vec.getX(), vec.getY()));
    }

    /*
    Spent way too long on this, only matrix thing im going to do.
     */
    public void matrixMultiplication() {
        Matrices matrices = new Matrices();
        addQuestion(" Multiply the Matrices and find determinate.");
        setAns(matrices.matrixMultiplication());
        String s = null;
        for (int[] i : matrices.getMat1()) {
            for (int x : i) {
                s += x + " ";
            }
            addQuestion(" " + s);
            s = null;
        }
        for (int[] i : matrices.getMat2()) {
            for (int x : i) {
                s += x + " ";
            }
            addQuestion(" " + s);
            s = null;
        }
    }
}
