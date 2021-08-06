package mod.quizbot.me.math;

import java.util.ArrayList;
import java.util.List;

public abstract class MathTemplate {

    /**
     *
     * Handles setting and getting questions.
     *
     **/

    private List<String> questions = new ArrayList<>();

    private double ans;

    public void addQuestion(String q) {
        questions.add(q);
    }

    public List<String> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<String> arr) {
        this.questions = arr;
    }

    public double getAns() {
        return ans;
    }

    public void setAns(double ans) {
        this.ans = ans;
    }
}
