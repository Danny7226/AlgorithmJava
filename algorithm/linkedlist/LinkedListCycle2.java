// slow pointer traveled (x+y), fast pointer traveled 2(x+y) when they meet
// x is the distance from head to cycle,
// y is the distance from head of cycle to where slow pointer is
// 2(x+y) - (x+y) = C => x = C-Y;
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head, res = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // there is a cycle
                while (slow != res) {
                    slow = slow.next;
                    res = res.next;
                }
                return res;
            }
        }

        return null;
    }
}