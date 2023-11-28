/**
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 *     ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 *     boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 *     int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 * Example 1:
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 */
public class ZigzagIterator {
    ArrayList<Iterator<Integer>> iterators;
    int totlaElements;
    int indexCounter;
    int elementCounter;
    int totalStreams;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.iterators = new ArrayList<>();
        this.iterators.add(v1.iterator());
        this.iterators.add(v2.iterator());
        this.totlaElements = v1.size() + v2.size();
        this.indexCounter = 0;
        this.elementCounter = 0;
        this.totalStreams = 2;
    }

    public int next() {
        if (!this.hasNext()) {
            throw new IllegalStateException();
        }

        while (!this.iterators.get(this.indexCounter % totalStreams).hasNext()) {
            this.indexCounter++; // instead of using an index counter, could also use a 'current' variable storing the current iterator
        }

        this.elementCounter++;
        return this.iterators.get(this.indexCounter++ % totalStreams).next();
    }

    public boolean hasNext() {
        // use total element counter to reduce from O(n) to O(1), n is the number of total data streams
        return this.elementCounter < this.totlaElements;
        // for (iterator: iterators) {
        //     if (iterators.hasNext) return true;
        // };
        // return false;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */