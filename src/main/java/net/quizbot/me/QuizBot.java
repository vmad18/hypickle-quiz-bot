package net.quizbot.me;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.quizbot.me.Events.PlayerJoinEvent;
import net.quizbot.me.Events.QuickMaths;
import net.quizbot.me.Events.QuizEvents;
import net.quizbot.me.utils.CONFIG_UTILS;
import net.quizbot.me.utils.PlayerStoring;
import net.quizbot.me.utils.Threading;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author v18
 *
 **/

@Mod(modid = "QuizBot", version = "1.0", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class QuizBot {

    //TODO fix config stuff.

    /**
    singleton bad, we do dependency injection here
     **/

    public static final String MODID = "QuizBot";

    public static final String VERSION = "1.0";

    private final Minecraft mc = Minecraft.getMinecraft();

    private final ExecutorService THREAD_POOL = Executors.newCachedThreadPool(new Threading());

    private boolean pickle = false;

    private boolean quickmaffs = false;

    private double delay = 0.35;

    private boolean enabled = false;

    private boolean admin = false;

    public PlayerStoring testers = new PlayerStoring();

    public PlayerStoring subjects = new PlayerStoring();

    public PlayerStoring count = new PlayerStoring();

    public PlayerStoring answers = new PlayerStoring();

    public PlayerStoring key = new PlayerStoring();

    public PlayerStoring calcSets = new PlayerStoring();

    public PlayerStoring configAns = new PlayerStoring();

    //Will maybe fix the config stuff later.
    //private final CONFIG_UTILS CONFIGUTILS = new CONFIG_UTILS(this);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        MinecraftForge.EVENT_BUS.register(new PlayerJoinEvent(this));
        MinecraftForge.EVENT_BUS.register(new QuizEvents(this));
        MinecraftForge.EVENT_BUS.register(new QuickMaths(this));
        ClientCommandHandler.instance.registerCommand(new Commands(this));
        //CONFIGUTILS.getConfig();
    }
    
    public void submitThread(Runnable r) {
        this.THREAD_POOL.submit(r);
    }

    /*
    -------------------
    Getters and Setters
    -------------------
    */

    public void setPickle(boolean b) {
        this.pickle = b;
    }

    public boolean isPickle() {
        return this.pickle;
    }

    public void setEnabled(boolean b) {
        this.enabled = b;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setDelay(double d){this.delay=d;}

    public double getDelay(){return this.delay;}

    public void setQuickmaffs(boolean b){
        this.quickmaffs=b;
    }

    public boolean isQuickmaffs(){
        return this.quickmaffs;
    }

    public void setAdmin(boolean b){
        this.admin = b;
    }

    public boolean isAdmin(){
        return this.admin;
    }

    public Minecraft getMc() {
        return this.mc;
    }
}
