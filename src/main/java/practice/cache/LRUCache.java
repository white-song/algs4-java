package practice.cache;


import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public DoubleList list;
    public Map<Integer, Node> map;
    public int cap;

    public LRUCache(int cap) {
        this.list = new DoubleList();
        this.map = new HashMap<>();
        this.cap = cap;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        list.remove(node);
        list.addLast(node);
        return node.val;
    }

    public void put(int key, int val) {
        Node newnode = new Node(key, val);

        if (map.containsKey(key)) {
            Node oldnode = map.get(key);
            list.remove(oldnode);

            map.put(key, newnode);
            list.addLast(newnode);

            return;
        }

        // 需要淘汰
        if (cap == list.size) {
            Node node = list.removeFirst();
            map.remove(node.key);
        }

        list.addLast(newnode);
        map.put(key, newnode);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node node = list.head.next; node.next != null; node = node.next) {
            sb.append(node.key).append(":").append(node.val).append(",");
        }
        return sb.append("]").toString();
    }
}