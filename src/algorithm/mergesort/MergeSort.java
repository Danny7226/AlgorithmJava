package algorithm.mergesort;

/**
 * Merge sort is better for linked list where there is pointers to next index for its divide and merge (merge requires cursor moving by 1 step)
 * Quick sort is better for array for its in-place swap operation
 */
public class MergeSort {
    public static ListNode sortList(ListNode head) {
        // complexity O(NlogN), space O(N)
        if (head == null || head.next == null) return head;

        // cut list into 2 halves
        ListNode prev = null, slow = head, fast = head;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // sort each half recuisively
        final ListNode sorted1stHalf = sortList(head);
        final ListNode sorted2ndHalf = sortList(slow);

        // merge 2 sorted list and return
        return merge(sorted1stHalf, sorted2ndHalf);
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode curser = new ListNode(), dummyHead = curser;

        // compare current node of 2 sorted list, connect curser with the smaller one
        // move curser and current smaller node forward by 1.
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curser.next = l1;
                l1 = l1.next;
            } else {
                curser.next = l2;
                l2 = l2.next;
            }
            curser = curser.next;
        }

        if (l1 != null) {
            curser.next = l1;
        }

        if (l2 != null) {
            curser.next = l2;
        }

        return dummyHead.next;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
