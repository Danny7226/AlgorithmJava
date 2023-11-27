/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cursorA = headA, cursorB = headB;
        boolean isA = false, isB = false;
        while(cursorA != null && cursorB != null && !cursorA.equals(cursorB)) {
            cursorA = cursorA.next;
            cursorB = cursorB.next;
            if (cursorA == null && !isA) {
                cursorA = headB;
                isA = true;
            }
            if (cursorB == null && !isB) {
                cursorB = headA;
                isB = true;;
            }
        }
        if (cursorA != null && cursorB != null && cursorA.equals(cursorB)) return cursorA;
        // no intersection
        return null;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cursorA = headA, cursorB = headB;
        while (cursorA != cursorB) {
            cursorA = cursorA == null ? headB : cursorA.next;
            cursorB = cursorB == null ? headA : cursorB.next;
        }
        return cursorA;
    }
}