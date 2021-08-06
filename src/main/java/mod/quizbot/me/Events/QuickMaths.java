package mod.quizbot.me.Events;

import mod.quizbot.me.Calculator.Calculator;
import mod.quizbot.me.QuizBot;
import mod.quizbot.me.Threads.MessageRunnable;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.List;

public class QuickMaths {

    /*
     For Hypixel Pit Quick Maths Event (Use at your own risk)
     I do not advise using this feature.
     */

    private final QuizBot bot;

    public QuickMaths(QuizBot b){
        this.bot = b;
    }

    @SubscribeEvent
    public void QuickMathsCall(ClientChatReceivedEvent e){
        if(!bot.isEnabled() || !bot.isPickle() || !bot.isQuickmaffs()) return;
        String msg = StringUtils.stripControlCodes(e.message.getUnformattedText());
        List<String> split = Arrays.asList(msg.split(" "));
        if(!split.subList(0,2).equals(Arrays.asList("QUICK", "MATHS!"))) return;

        String eq = split.get(2);
        Calculator calculator = new Calculator(eq,false);
        bot.submitThread(new MessageRunnable(bot.getDelay(), calculator.str.get(0), bot.getMc()));
    }
}
