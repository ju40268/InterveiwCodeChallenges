class newLRU {
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
    // connect head & tail
    public LRUCache(int capacity) {
        size = capacity;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        // connect head and tail
        head.next = tail;
        tail.pre = head;
        map = new HashMap();
    }
    public void addNode(Node cur) {
        // insert node behind the head node
        cur.next = head.next;
        head.next.pre = cur;
        cur.pre = head;
        head.next = cur;
    }
    public void removeNode(Node cur) {
        // delete node from the list
        cur.pre.next = cur.next;
        cur.next.pre = cur.pre;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            Node cur = map.get(key);
            removeNode(cur);
            addNode(cur);
            return cur.value;
        }
        return -1;
    }
    
    // Careful here!!!!
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            Node cur = map.get(key);
            removeNode(cur);
            addNode(cur);
            cur.value = value;
        } else {
            Node cur = new Node(key, value);
            addNode(cur);
            // deal with map and size
            map.put(key, cur);
            if(map.size() > size) {
                map.remove(tail.pre.key);
                removeNode(tail.pre);
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