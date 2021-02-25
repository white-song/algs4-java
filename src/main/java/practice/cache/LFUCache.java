package practice.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    public Map<Integer, Node> key2val;
    public Map<Integer, Integer> key2freq;
    public Map<Integer, DoubleList> freq2key;
    public int capacinty;
    public int minFreq;

    public LFUCache(int cap) {
        key2val = new HashMap<>();
        key2freq = new HashMap<>();
        freq2key = new HashMap<>();

        capacinty = cap;
    }

    public void incrFreq(int key, Node node) {
        int freq = key2freq.get(key);
        key2freq.put(key, freq + 1);

        //删除老的，增加新的
        DoubleList doubleList = freq2key.get(key);
        doubleList.remove(node);
        if (doubleList.size == 0) {
            freq2key.remove(key);
            if (minFreq == freq) {
                minFreq++;
            }
        }

        freq2key.putIfAbsent(freq + 1, new DoubleList());
        freq2key.get(freq + 1).addLast(node);

    }

    public void removeMinFreqKey() {
        DoubleList doubleList = freq2key.get(minFreq);
        Node node = doubleList.removeFirst();
        if (doubleList.size == 0) {
            freq2key.remove(node.key);
        }
        key2val.remove(node.key);
        key2freq.remove(node.key);
    }

    public int get(int key) {
        if (!key2val.containsKey(key)) {
            return -1;
        }
        //增长频次
        Node node = key2val.get(key);
        incrFreq(key, node);
        return node.val;
    }

    public void put(int key, int val) {
        // 包含
        Node node = new Node(key, val);
        if (key2val.containsKey(key)) {
            key2val.put(key, node);

            incrFreq(key, node);
            return;
        }
        // 不包含, 是否需要删除
        if (key2val.size() >= this.capacinty) {
            removeMinFreqKey();
        }

        key2val.put(key, node);
        key2freq.put(key, 1);
        freq2key.putIfAbsent(1, new DoubleList());
        freq2key.get(1).addLast(node);
        minFreq = 1;
    }

}
