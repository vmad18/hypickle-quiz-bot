package mod.quizbot.me.Threads;

import mod.quizbot.me.QuizBot;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class PlayerMessageThread implements Runnable{

    private final QuizBot bot;

    private final double delay;

    private final String message;

    public PlayerMessageThread(QuizBot b, double d, String m){
        this.bot = b;
        this.delay = d;
        this.message = m;
    }

    public void run(){
        try {
            Thread.sleep((long) (delay*1000));
            bot.getMc().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "QuizBot: " + message));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
