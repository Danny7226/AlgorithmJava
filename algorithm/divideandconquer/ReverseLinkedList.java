/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
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
    public ListNode reverseList(ListNode head) {
        // reverse a list means to reverse a sub-list that includes all nodes after current node
        // and then reverse the pointing direction between the current node and the very next node;

        // reverse a sub-list that includes all nodes after current node can also be sub-divided

        if (head == null || head.next == null) return head;
        final ListNode nextNode = head.next;
        final ListNode newHead = reverseList(nextNode);
        head.next = null;
        nextNode.next = head;
        return newHead;
    }
}