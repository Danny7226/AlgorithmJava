/**
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode next;
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode current = new ListNode(), res = current; // ! [3,5] left = 1, right = 2
        current.next = head;
        for (int i = 1; i < left; i++) {
            current = current.next;
        }

        final ListNode reversedTail = current.next;
        final ListNode reversedHead = reverseListAndReturnHead(current.next, right - left);
        current.next = reversedHead;
        reversedTail.next = next;
        return res.next;
    }

    private ListNode reverseListAndReturnHead(ListNode current, int count) {
        if (count == 0) {
            this.next = current.next;
            current.next = null;
            return current;
        }

        final ListNode currentNext = current.next;
        final ListNode newHead = reverseListAndReturnHead(current.next, count-1);
        currentNext.next = current;
        return newHead;
    }
}