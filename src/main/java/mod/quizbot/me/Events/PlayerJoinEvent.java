package mod.quizbot.me.Events;

import mod.quizbot.me.QuizBot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class PlayerJoinEvent {

    public QuizBot bot;

    public PlayerJoinEvent(QuizBot b){bot = b;}

    @SubscribeEvent
    public void hypickleJoin(FMLNetworkEvent.ClientConnectedToServerEvent e){bot.setPickle(!bot.getMc().isSingleplayer() && e.manager.getRemoteAddress().toString().contains("hypixel.net"));}

    @SubscribeEvent
    public void serverLeave(FMLNetworkEvent.ClientDisconnectionFromServerEvent e){
        bot.setPickle(false);
    }
}
