package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class JSONArray extends JSON implements Iterable<JSON> {
    private final List<JSON> list;

    public JSONArray() {
        this.list = new ArrayList<>();
    }

    public void addAll(List<JSON> elements) {
        list.addAll(elements);
    }

    public JSON get(int i) {
        return list.get(i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        AtomicInteger size = new AtomicInteger(list.size());
        list.forEach(e -> {
            sb.append(e.toString());
            if (size.decrementAndGet() != 0) {
                sb.append(", ");
            }
        });
        sb.append(" ]");
        return sb.toString();
    }

    @Override
    public Iterator<JSON> iterator() {
        return list.iterator();
    }
}
