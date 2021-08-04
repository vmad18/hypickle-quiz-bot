package net.quizbot.me.Threads;

import net.minecraft.client.Minecraft;

public class MessageRunnable implements Runnable {

    private final double delay;

    private final String msg;

    private final Minecraft minecraft;

    public MessageRunnable(double d, String m, Minecraft mc) {
        delay = d;
        msg = m;
        minecraft = mc;
    }

    public MessageRunnable(int d, String m, Minecraft mc, Runnable r) {
        this.delay = d;
        this.msg = m;
        this.minecraft = mc;
        r.run();
    }

    public void run() {
        try {
            Thread.sleep((long) delay * 1000);
            minecraft.thePlayer.sendChatMessage(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
