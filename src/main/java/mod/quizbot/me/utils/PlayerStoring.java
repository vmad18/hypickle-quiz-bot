package mod.quizbot.me.utils;

import java.util.HashMap;

public class PlayerStoring<T> {

    /**
     * trash thing, idfk why i made it LOL
     **/

    private final HashMap<T, Object> m = new HashMap<>();

    public HashMap<T, Object> getM() {
        return this.m;
    }

    public void addElm(T s, Object i) {
        this.m.put(s, i);
    }

    public Object getO(T s) {
        if (hasKey(s)) return this.m.get(s);
        else return null;
    }

    public void removeElm(T s) {
        if (hasKey(s))
            this.m.remove(s);
    }

    public void replaceElm(T s, Object i) {
        getM().replace(s, i);
    }

    public boolean hasKey(T s) {
        return this.m.containsKey(s);
    }

    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }
}
