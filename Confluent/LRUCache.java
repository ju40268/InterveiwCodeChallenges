class LRUCache {
    private int max_size = 0;
    private int size = 0;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;
    public class Node {
        Node pre;
        Node next;
        int key, value;
        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }
    public LRUCache(int capacity) {
        max_size = capacity;
        size = 0;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        // connect head and tail
        head.next = tail;
        tail.pre = head;
        map = new HashMap();
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node cur = map.get(key);
            // delete node
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            // insert node
            cur.next = head.next;
            head.next.pre = cur;
            cur.pre = head;
            head.next = cur;
            return cur.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node cur = map.get(key);
            // delete node
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            // insert node
            cur.next = head.next;
            head.next.pre = cur;
            cur.pre = head;
            head.next = cur;
            cur.value = value;
        } else {
            Node cur = new Node(key, value);
            // insert node
            cur.next = head.next;
            head.next.pre = cur;
            cur.pre = head;
            head.next = cur;
            cur.value = value;
            // deal with map and size
            map.put(key, cur);
            size ++;
            if(size > max_size) {
                map.remove(tail.pre.key);
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
                size--;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */