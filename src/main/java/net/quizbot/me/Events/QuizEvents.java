package net.quizbot.me.Events;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.quizbot.me.Threads.MessageRunnable;
import net.quizbot.me.QuizBot;
import net.quizbot.me.QuizFactory;
import net.quizbot.me.Threads.PlayerMessageThread;
import net.quizbot.me.Calculator.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuizEvents {

    private final QuizBot bot;

    //There's no MVC, LA, TC, or Physics. May add in the future...(probably not)
    private final List<String> sarr = Arrays.asList("Addition/Subtract", "Multiplication", "Division", "Algebra", "Geometry", "Calculus", "Vector Calculus", "Linear Algebra", "Tensor Calculus", "Physics");

    private final List<String> blackList = Arrays.asList("Addition/Subtraction", "Multiplication", "Division");

    public QuizEvents(QuizBot b) {
        bot = b;
    }

    public String getName(String text, int stage) {
        text = StringUtils.stripControlCodes(text);
        try {
            String[] splits = text.split(" ");
            if (splits[0].equals("From")) {
                String name = splits[2].substring(0, splits[2].length() - 1).replaceAll("\\s", "");
                if (bot.testers.hasKey(name) && (((int) bot.testers.getO(name)) == stage || stage == 0)) return name;
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }

    public void runMessages(List<String> q, String name) {
        for (int i = 0; i < q.size(); i++) bot.submitThread(new MessageRunnable(4 + 3 * i, "/msg " + name + q.get(i), bot.getMc()));
    }

    @SubscribeEvent
    public void onFriend(ClientChatReceivedEvent e) {
        if (!bot.isEnabled() || !bot.isPickle()) return;
        Pattern pattern = Pattern.compile("Friend request from (?<name>.+)\\[ACCEPT\\]  \\[DENY\\]  \\[IGNORE\\].*");
        String msg = e.message.getUnformattedText();
        if (!msg.contains("F")) return;

        msg = msg.replace("\n", "");
        msg = msg.replaceAll("-", "");
        msg = msg.substring(msg.indexOf("F"));

        Matcher friendMatcher = pattern.matcher(msg);
        if (friendMatcher.matches()) {
            String name = friendMatcher.group("name");
            if (name.startsWith("["))
                name = name.substring(name.indexOf("] ") + 2).replaceAll("\\s", "");

            List<String> hold1 = sarr.subList(0, 3);
            List<String> hold2 = sarr.subList(3, 6);
            List<String> hold3 = sarr.subList(6, sarr.size());
            StringBuilder s = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            StringBuilder s3 = new StringBuilder();
            hold1.forEach(x -> s.append(x + " "));
            hold2.forEach(x -> s2.append(x + " "));
            hold3.forEach(x -> s3.append(x + " "));

            bot.getMc().thePlayer.sendChatMessage("/friend accept " + name);
            bot.submitThread(new MessageRunnable(2, "/msg " + name + " Type the subject the you would like to be quizzed on.", bot.getMc()));
            bot.submitThread(new MessageRunnable(4, "/msg " + name + " Here is the list of subjects: " + s.toString(), bot.getMc()));
            bot.submitThread(new MessageRunnable(6, "/msg " + name + " " + s2.toString(), bot.getMc()));
            bot.submitThread(new MessageRunnable(8, "/msg " + name + " " + s3.toString(), bot.getMc()));
            bot.testers.addElm(name, 1);
        }
    }

    @SubscribeEvent
    public void setSubject(ClientChatReceivedEvent e) {
        if (!bot.isEnabled() || !bot.isPickle() || e.message.getUnformattedText().toLowerCase().contains("calculate:"))
            return;
        String name = getName(e.message.getUnformattedText(), 1);
        if (name.equals("")) return;

        String[] s = StringUtils.stripControlCodes(e.message.getUnformattedText()).split(" ");
        ArrayList<String> str = new ArrayList<>(Arrays.asList(s));

        if (str.stream().noneMatch(l -> sarr.contains(l))) return;

        Optional<String> b = str.stream().filter(l -> sarr.contains(l)).findFirst();
        String a = b.get().replaceAll("\\s", "");
        bot.subjects.addElm(name, a);
        bot.submitThread(new MessageRunnable(3, "/msg " + name + " You have chosen the " + a + " test.", bot.getMc()));
        bot.submitThread(new MessageRunnable(5, "/msg " + name + " Enter the number of questions you want.", bot.getMc(), () -> {
            try {
                Thread.sleep(7 * 1000);
                bot.testers.replaceElm(name, ((int) bot.testers.getO(name)) + 1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }));
    }

    @SubscribeEvent
    public void setQuestionSize(ClientChatReceivedEvent e) throws InvocationTargetException, IllegalAccessException {
        if (!bot.isEnabled() || !bot.isPickle() || e.message.getUnformattedText().toLowerCase().contains("calculate:"))
            return;
        String name = getName(e.message.getUnformattedText(), 2);
        if (name.equals("")) return;
        String[] s = StringUtils.stripControlCodes(e.message.getUnformattedText()).split(" ");
        s[1] = "";
        int num = 0;
        for (String i : s) {
            try {
                num = Integer.parseInt(i);
                break;
            } catch (NumberFormatException ignored) {
            }
        }

        if (num == 0) {
            bot.submitThread(new MessageRunnable(3, "/msg " + name + " The number of questions must be greater than 0.", bot.getMc()));
            return;
        }

        //Set number of question size
        bot.count.addElm(name, num);
        bot.submitThread(new MessageRunnable(2, "/msg " + name + " Keep your answer in the form of 'Answer: <ans>", bot.getMc()));

        //Create answer space
        bot.answers.addElm(name, new ArrayList<Double>());

        //Create key space
        bot.key.addElm(name, new ArrayList<Double>());

        //Fire first question
        QuizFactory q = new QuizFactory((String) bot.subjects.getO(name));
        runMessages(q.getQuestions(), name);

        //Store first answer
        ((ArrayList<Double>) bot.key.getO(name)).add(q.getAns());

        //Go to next stage
        bot.testers.replaceElm(name, ((int) bot.testers.getO(name)) + 1);
    }

    @SubscribeEvent
    public void ansList(ClientChatReceivedEvent e) throws InvocationTargetException, IllegalAccessException {
        if (!bot.isEnabled() || !bot.isPickle() || e.message.getUnformattedText().toLowerCase().contains("calculate:"))
            return;

        String name = getName(StringUtils.stripControlCodes(e.message.getUnformattedText()), 3);
        String m = StringUtils.stripControlCodes(e.message.getUnformattedText()).toLowerCase();

        if (name.equals("")) return;

        String[] s = m.split(" ");
        ArrayList<String> str = new ArrayList<>(Arrays.asList(s));

        if (!(str.contains("answer:"))) return;
        int index = str.indexOf("answer:");

        StringBuilder builder = new StringBuilder();
        str.subList(index + 1, str.size()).forEach(builder::append);
        String ans = builder.toString();

        //Add the answer to player answer hashmap, do next question or end test.

        Calculator calc = new Calculator(ans.replaceAll("\\s", "").replaceAll(" ", ""), true);
        String a = calc.str.get(0);

        ((ArrayList<Double>) bot.answers.getO(name)).add(Double.parseDouble(a));

        if (((ArrayList<Double>) bot.answers.getO(name)).size() == (int) bot.count.getO(name)) {
            bot.submitThread(new MessageRunnable(3, "/msg " + name + " You scored " + validate(name) + "%.", bot.getMc()));
            reset(name);
        } else {
            QuizFactory q = new QuizFactory((String) bot.subjects.getO(name));
            runMessages(q.getQuestions(), name);
            ((ArrayList<Double>) bot.key.getO(name)).add(q.getAns());
        }
    }

    @SubscribeEvent
    public void calculator(ClientChatReceivedEvent e) {
        String msg = StringUtils.stripControlCodes(e.message.getUnformattedText());
        String msg2 = StringUtils.stripControlCodes(e.message.getUnformattedText().toLowerCase());
        if (!bot.isEnabled() || !msg2.contains("calculate:"))
            return;

        String name = getName(e.message.getUnformattedText(), 0);

        if (name.equals("") && !msg.contains(bot.getMc().thePlayer.getName())) return;

        StringBuilder eq = new StringBuilder();

        int l = msg2.indexOf("calculate:") + 10;
        eq.append(msg.substring(l, msg2.length()));

        if (msg.contains(bot.getMc().thePlayer.getName())) {
            try {
                Calculator calc = new Calculator(eq.toString().toLowerCase(), true);
                bot.submitThread(new PlayerMessageThread(bot, .125, EnumChatFormatting.DARK_AQUA + String.valueOf(calc.parseEquation(calc.str).get(0))));
            } catch (Exception w) {
                bot.submitThread(new PlayerMessageThread(bot, .125, EnumChatFormatting.RED + " Error: Unable to Interpret Equation Syntax."));
            }
        } else {
            if (blackList.contains(bot.subjects.getO(name))) {
                bot.submitThread(new MessageRunnable(3, "/msg " + name + " Sorry, the calculator is now allowed on this test.", bot.getMc()));
                return;
            }
            try {
                Calculator calc = new Calculator(eq.toString().toLowerCase(), true);
                bot.submitThread(new MessageRunnable(3, "/msg " + name + " Evaluation: " + calc.str.get(0), bot.getMc()));
            } catch (Exception w) {
                bot.submitThread(new MessageRunnable(3, "/msg " + name + " Unable to Evaluate Expression", bot.getMc()));
            }
        }
    }

    private double validate(String name) {
        ArrayList<Double> ans = (ArrayList<Double>) bot.answers.getO(name), key = (ArrayList<Double>) bot.key.getO(name);
        double correct = 0;
        for (int i = 0; i < ans.size(); i++)
            //Account for any small errors.
            if (Math.abs(ans.get(i) - key.get(i)) <= 0.015) correct++;
        return Math.round((correct / ans.size()) * 1000) / 1000;
    }

    //TODO Add a Rating System (lol)
    //TODO Save person's score to a file ~
    private void reset(String name) {
        bot.testers.removeElm(name);
        bot.subjects.removeElm(name);
        bot.count.removeElm(name);
        bot.answers.removeElm(name);
        bot.key.removeElm(name);
        bot.calcSets.removeElm(name);
        bot.submitThread(new MessageRunnable(3, "/msg " + name + " Thank you for taking your test. Goodbye", bot.getMc()));
        bot.submitThread(new MessageRunnable(6, "/f remove " + name, bot.getMc()));
    }
}