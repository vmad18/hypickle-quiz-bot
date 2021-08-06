package mod.quizbot.me.utils;

import mod.quizbot.me.Calculator.CalculatorSettings;

public class PlayerData {

    /**
     *
     * Handles player data.
     *
     **/

    private String subject;

    private String player;

    private int count;

    private int phase=0;

    private PlayerAnswer answers = new PlayerAnswer();

    private PlayerAnswer key = new PlayerAnswer();

    private CalculatorSettings calculatorSettings = new CalculatorSettings();

    private String configAns;

    public PlayerData(String s){
        this.player = s;
        nextPhase();
    }

    public void setSubject(String subj){
        this.subject = subj;
    }

    public void setCount(int c){
        this.count = c;
    }

    public void nextPhase(){
        phase++;
    }

    public String getSubject(){
        return this.subject;
    }

    public int getCount(){
        return this.count;
    }

    public CalculatorSettings getSettings(){
        return this.calculatorSettings;
    }

    public PlayerAnswer getAnswers(){
        return this.answers;
    }

    public PlayerAnswer getKey(){
        return this.key;
    }

    public int getPhase(){
        return this.phase;
    }

}
