/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * Implement the LRUCache class:
 *
 *     LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 *     int get(int key) Return the value of the key if the key exists, otherwise return -1.
 *     void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * The functions get and put must each run in O(1) average time complexity.
 */
class LRUCache {
    // least recently used is different from least frequently used (LFU), which requires a counter to keep track of how many

    // Doublely linked list to provide O(1) move to head and remove operation
    // Regular HashMap to provide key-value look up operation
    DLLNode head;
    DLLNode tail;
    Map<Integer, DLLNode> map;
    final int capacity;
    public LRUCache(int capacity) {
        this.head = new DLLNode(0,0);
        this.tail = new DLLNode(0,0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        final DLLNode node = this.map.get(key);
        if (node != null) {
            putFront(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        // O(1)
        // if key exist, update and move up front, if
        if (this.map.containsKey(key)) {
            final DLLNode node = this.map.get(key);
            node.value = value;
            putFront(node);
        } else {
            this.map.put(key, addNode(key, value));
            if (map.size() > this.capacity) {
                final DLLNode removed = removeLast();
                this.map.remove(removed.key);
            }
        }

    }

    class DLLNode {
        public int key;
        public int value;
        public DLLNode prev;
        public DLLNode next;
        DLLNode (final int key, final int value) {
            this.key = key;
            this.value = value;
        }
    }

    private DLLNode addNode(final int key, final int value) {
        final DLLNode res = new DLLNode(key, value);
        final DLLNode headNext = this.head.next;
        headNext.prev = res;
        res.next = headNext;
        this.head.next = res;
        res.prev = this.head;

        DLLNode x = this.head;
        return res;
    }

    private void putFront(final DLLNode node) {
        final DLLNode nodePrev = node.prev;
        final DLLNode nodeNext = node.next;

        // do detach first in case node is the only node in DLL
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;

        // read head next here instead of at beginning in case node is the only node in DLL
        final DLLNode headNext = this.head.next;
        this.head.next = node;
        node.prev = this.head;
        headNext.prev = node;
        node.next = headNext;
    }

    private DLLNode removeLast() {
        final DLLNode res = this.tail.prev;
        res.prev.next = this.tail;
        this.tail.prev = res.prev;
        return res;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */