package net.quizbot.me.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CONFIG_UTILS {

    private QuizBot bot;

    public CONFIG_UTILS(QuizBot b) {
        this.bot = b;
    }

    public File c = new File(bot.getMc().mcDataDir + "/config/quizbot.cfg");

    public void getConfig() {
        try {
            if (!c.exists())
                c.createNewFile();

            try (Stream<String> lines = Files.lines(Paths.get(c.getPath()))) {
                List<String> lineList = new ArrayList<>();
                lines.forEach(lineList::add);
                for (String s : lineList) {
                    String[] a = s.split(" ");
                    String name = a[0];
                    ArrayList<String> values = new ArrayList<>();
                    for (int i = 1; i < a.length; i++) {
                        values.add(a[i]);
                    }
                    bot.configAns.addElm(name, values);
                }
            } catch (IOException e) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeConfig() {
        try {
            if (!c.exists())
                getConfig();
            File conf = new File(c.getPath());
            FileWriter write = new FileWriter(conf);
            for (String s : ((ArrayList<String>) bot.configAns.getM().keySet())) {
                StringBuilder build = new StringBuilder();
                build.append(s + " ");
                ArrayList<String> a = (ArrayList<String>) bot.configAns.getO(s);
                a.forEach(build::append);
                write.write(System.lineSeparator() + build.toString());
                build.setLength(0);
                build.trimToSize();
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
