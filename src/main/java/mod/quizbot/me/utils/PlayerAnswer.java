package mod.quizbot.me.utils;

import java.util.ArrayList;
import java.util.List;

public class PlayerAnswer<T> {

    private List<T> l = new ArrayList<>();

    public void addElm(T e){
        l.add(e);
    }

    public void removeElm(T e){
        l.remove(e);
    }

    public T getIndex(int i){
        if(i<l.size()) return l.get(i);
        else return null;
    }

    public List<T> getArr(){
        return this.l;
    }

}
