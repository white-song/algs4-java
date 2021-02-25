package practice.cache;

/***
 * 双向链表
 *
 * 需要有插入头结点，插入尾结点，删除结点
 */
public class DoubleList {
    public Node head, tail;
    public int size;

    public DoubleList() {
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.size = 0;
    }

    /**
     * 插入尾结点
     */
    public void addLast(Node x) {
        x.pre = tail.pre;
        x.next = tail;
        tail.pre.next = x;
        tail.pre = x;
        size += 1;
    }

    /**
     * 删除头部结点
     */
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node res = head.next;
        head.next = res.next;
        head.next.pre = head;
        size--;
        return res;
    }

    /**
     * 删除结点
     */
    public void remove(Node x) {
        x.pre.next = x.next;
        x.next.pre = x.pre;
        size -= 1;
    }
}
