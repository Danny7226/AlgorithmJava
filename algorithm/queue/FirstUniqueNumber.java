/**
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 *
 * Implement the FirstUnique class:
 *
 *     FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
 *     int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
 *     void add(int value) insert value to the queue.
 *
 * Example 1:
 *
 * Input:
 * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
 * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
 * Output:
 * [null,2,null,2,null,3,null,-1]
 * Explanation:
 * FirstUnique firstUnique = new FirstUnique([2,3,5]);
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(5);            // the queue is now [2,3,5,5]
 * firstUnique.showFirstUnique(); // return 2
 * firstUnique.add(2);            // the queue is now [2,3,5,5,2]
 * firstUnique.showFirstUnique(); // return 3
 * firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
 * firstUnique.showFirstUnique(); // return -1
 */
class FirstUnique {
    LinkedHashSet<Integer> uniqueLinkedSet; // FIFO data structure, with ability to remove certain element
    Set<Integer> seenSet;
    public FirstUnique(int[] nums) {
        this.uniqueLinkedSet = new LinkedHashSet<>();
        this.seenSet = new HashSet<>();
        for (int num: nums) {
            this.add(num);
        }
    }

    public int showFirstUnique() {
        // O(1)
        return this.uniqueLinkedSet.size() == 0 ? -1 : this.uniqueLinkedSet.iterator().next(); // similar to peek, iterator() method returns a new iterator each time
    }

    public void add(int value) {
        // O(1)
        // do not use uniqueLinkedSet.contains() to check, for [[1][1][1]] issue
        if (this.seenSet.contains(value)) {
            this.uniqueLinkedSet.remove(value);
        } else {
            this.seenSet.add(value);
            this.uniqueLinkedSet.add(value);
        }
    }
}

class FirstUnique {
    Queue<Integer> queue; // the question description requires a FIFO IO, therefore using a queue
    Map<Integer, Integer> duplicationMap; // need a data structure to check unique or not. HashMap fits
    public FirstUnique(int[] nums) {
        this.queue = new LinkedList();
        this.duplicationMap = new HashMap<>();
        for (int num: nums) {
            queue.add(num);
            this.duplicationMap.put(num, this.duplicationMap.getOrDefault(num, 0) + 1);
        }
    }

    public int showFirstUnique() {
        // O (n) worst case
        Integer curr = this.queue.peek();
        while (curr != null) {
            if (this.duplicationMap.get(curr) == 1) {
                return curr;
            } else {
                this.queue.poll();
                curr = queue.peek();
            }
        }
        return -1;
    }

    public void add(int value) {
        queue.add(value);
        this.duplicationMap.put(value, this.duplicationMap.getOrDefault(value, 0) + 1);
    }
}
/**
 * Your FirstUnique object will be instantiated and called as such:
 * FirstUnique obj = new FirstUnique(nums);
 * int param_1 = obj.showFirstUnique();
 * obj.add(value);
 */