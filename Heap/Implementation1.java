import java.util.ArrayList;
import java.util.List;

public class Implementation1 {
    private List<Integer> list;

    public Implementation1() {
        list = new ArrayList<>();
    }

    public void add(int value) {
        list.add(value);
    }

    public int remove() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        int minIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(minIndex)) {
                minIndex = i;
            }
        }
        return list.remove(minIndex);
    }
}
