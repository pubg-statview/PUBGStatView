package zoo.pubg.vo.list;

import java.util.ArrayList;
import java.util.List;

public abstract class ValueList<T> {

    protected final List<T> list;

    public ValueList() {
        this.list = new ArrayList<>();
    }

    public ValueList(List<T> list) {
        this.list = list;
    }

    public void add(T t) {
        list.add(t);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(T t) {
        return list.contains(t);
    }

    public int size() {
        return list.size();
    }

    public List<T> getList() {
        return new ArrayList<>(list);
    }

    public abstract String joinToString();

}
