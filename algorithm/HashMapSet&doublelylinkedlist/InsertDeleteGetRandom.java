/**
 * Implement the RandomizedSet class:
 *
 *  RandomizedSet() Initializes the RandomizedSet object.
 *  bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 *  bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 *  int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 *
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
class RandomizedSet {
    // TLDR: removing an element from arraylist in O(1), swap the element with the last one and pop out the last;
    // ArrayList random access is faster than LinkedList, but remove from middle is slower than LinkedList

    // in order to get random with O(1), need to use array under the hood with O(1) random access, and arraylist to dynamically allocate memory
    // in order to remove value from both HashSet and array in the same time, need to store array index along with value, therefore need to use a HashMap<value, index>
    Map<Integer, Integer> valueToIndexMap;
    List<Integer> arraylist; // array stores the value of Set
    Random random;
    public RandomizedSet() {
        this.valueToIndexMap = new HashMap<>();
        this.arraylist = new ArrayList<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (this.valueToIndexMap.containsKey(val)) return false;
        final int arraySize = this.arraylist.size();
        this.valueToIndexMap.put(val, arraySize);
        this.arraylist.add(arraySize, val);
        return true;
    }

    public boolean remove(int val) {
        // if doesn't exist return false;
        // if exists, get the index of val, swap with last element, remove last element from arraylist, remove from Map
        // ! change the previous last element's index in Map
        final Integer toRemove = this.valueToIndexMap.get(val);
        if (toRemove == null) return false;
        final int arraySize = this.arraylist.size();
        final int lastElement = this.arraylist.get(arraySize - 1);
        this.arraylist.set(toRemove, lastElement);
        this.arraylist.remove(arraySize - 1);

        this.valueToIndexMap.put(lastElement, toRemove);
        this.valueToIndexMap.remove(val);
        return true;
    }

    public int getRandom() {
        final int arraySize = this.arraylist.size();
        final int randomIndex = random.nextInt(arraySize);
        return arraylist.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */