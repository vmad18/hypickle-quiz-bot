package net.quizbot.me.Events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.quizbot.me.QuizBot;

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
