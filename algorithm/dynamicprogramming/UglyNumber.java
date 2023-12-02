/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

 * Given an integer n, return the nth ugly number.
 *
 * Example 1:
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 */
class Solution {
    // TLDR; an ugly number can only derive from the previous ugly number
    // maintain a data structures to retrive the smallest ugly number and then * 2;3;5 to push back in the data structure, need to eliminate duplicates
    // or maintain a data structure (3 points p2 p3 p5) to calculate the smallest ugly number and then update the data structure, need to elinimate duplicates
    private static final UglyNumber uglyNumber = new UglyNumber();

    public int nthUglyNumber(int n) {
        return uglyNumber.getNumber(n);
    }
}

class UglyNumber {
    int[] numbers;
    UglyNumber(){
        this.numbers = new int[1690];
        this.numbers[0] = 1;
        initialize();
    }

    public int getNumber(final int n) {
        return numbers[n - 1];
    }

    private void initialize(){
        int p2 = 0, p3 = 0, p5 = 0;
        int nextUgly = 1, ugly2 = 2, ugly3 = 3, ugly5 = 5;
        for (int i = 1; i < 1690; i++) {
            nextUgly = Math.min(ugly2, Math.min(ugly3, ugly5));
            this.numbers[i] = nextUgly;
            if(this.numbers[p2]*2 == nextUgly) {
                p2++;
                ugly2 = this.numbers[p2]*2;
            }
            if(this.numbers[p3]*3 == nextUgly) {
                p3++;
                ugly3 = this.numbers[p3]*3;
            }
            if(this.numbers[p5]*5 == nextUgly) {
                p5++;
                ugly5 = this.numbers[p5]*5;
            }
        }
    }
}