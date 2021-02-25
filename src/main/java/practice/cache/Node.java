package practice.cache;

/***
 * 使用 double list + hash map 的方式实现LRU缓存
 */

public class Node {
    public Node pre, next;
    public int key, val;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
