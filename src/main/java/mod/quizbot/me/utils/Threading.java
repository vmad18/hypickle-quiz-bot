package mod.quizbot.me.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class Threading implements ThreadFactory {

    private final AtomicInteger at = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) { return new Thread(r,"QuizBot" + this.at.getAndAdd(1));}
}
