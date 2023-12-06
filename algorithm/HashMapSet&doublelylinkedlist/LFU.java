/**
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 *     LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 *     int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 *     void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 *
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 */
// Followup, what if there is a remove() operation? Then we need to keep track of the previous LFU element
// use a Map<Integer, [value, frequence]> with a treeset<[frequency, key]> to remove and add new freq in O(logn) (dont update inplace), hashed based on freq -> counter
class LFUCache {
    // put() with O(1) therefore no tree data / priority queue structure
    // Least frequently used requres a counter to access the all least frequent nodes,
    // Doublely linked list to make a decision when there is a tie;

    Map<Integer, LinkedHashSet<Integer>> counter; // frequency, Set<key>
    Map<Integer, int[]> map; // key, [value, frequency];
    int leastFrequency = 1; // we can do this cuz no delete() operation
    final int capacity;
    public LFUCache(int capacity) {
        this.counter = new HashMap<>();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        // increase frequency by one,
        // if current counter entry is the least frequent entry, and becomes empty, increase least frequency by one
        // if current counte entry is the least frequent entry, and not empyt, remain
        // if current counter entry is not the least frequenty entry, remain;
        final int[] valueFrequencyPair = this.map.get(key);
        if (valueFrequencyPair == null) {
            return -1;
        }
        // System.out.println(valueFrequencyPair[0] + " " + valueFrequencyPair[1]);
        LinkedHashSet<Integer> currentCounterEntry = this.counter.get(valueFrequencyPair[1]);
        LinkedHashSet<Integer> nextCounterEntry = this.counter.getOrDefault(valueFrequencyPair[1] + 1, new LinkedHashSet<>());
        nextCounterEntry.add(key);
        this.counter.put(valueFrequencyPair[1] + 1, nextCounterEntry);
        this.map.put(key, new int[] {valueFrequencyPair[0], valueFrequencyPair[1] + 1});

        currentCounterEntry.remove(key);
        if (currentCounterEntry.isEmpty()) {
            // remove current counter entry
            this.counter.remove(currentCounterEntry); // clear memory

            // if current counter == least frequent, bump up least frequent by 1;
            if (this.leastFrequency == valueFrequencyPair[1]) this.leastFrequency++;
        }
        return valueFrequencyPair[0];
    }

    public void put(int key, int value) {
        // if existing, update value and frequency + 1;

        // if not overflow and new, set least frequency to 1;
        // if overflow, remove the least frequent one
        // if least frequent set is empty, reset leastFrequency to 1 and add new key-value;
        if (this.map.containsKey(key)) {
            final int[] valuePair = this.map.get(key);
            this.map.put(key, new int[]{value, valuePair[1]});
            get(key); // increment frequency by 1;
            return;
        }

        // new key
        if (this.map.size() == this.capacity) { // remove LFU
            Set<Integer> leastFrequencySet = this.counter.get(this.leastFrequency);
            int toRemove = leastFrequencySet.iterator().next();
            this.map.remove(toRemove);
            leastFrequencySet.remove(toRemove);
            if (leastFrequencySet.isEmpty()) {
                this.counter.remove(this.leastFrequency);
            }
        }

        this.map.put(key, new int[] {value, 1});
        final LinkedHashSet<Integer> leastEntry = this.counter.getOrDefault(1, new LinkedHashSet<>());
        leastEntry.add(key);
        this.counter.put(1, leastEntry);
        // System.out.println("added 1 entry: " + key);
        this.leastFrequency = 1;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */