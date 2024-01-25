import java.util.ArrayList;
import java.util.List;

public class Implementation2 {
    private List<Integer> list;

    public Implementation2() {
        list = new ArrayList<>();
    }

    public void add(int value) {
        int i = 0;
        while (i < list.size() && list.get(i) < value) {
            i++;
        }
        list.add(i, value);
    }

    public int remove() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.remove(0);
    }
}
