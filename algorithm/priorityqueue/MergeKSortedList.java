/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
class Solution {
    // TLDR; if input not sorted, O(knlog(kn)), if input sorted, we simply need to merge them
    // mering requires comparing and writing, comparing takes O(k) in general, which leads to O(kkn)
    // by using a priority queue, optimize comparing into O(logk), which leads to a total of O(knlogk)
    // when reading long sorted data stream, use PQ can be optimum v.s. sorting at the end
    public ListNode mergeKLists(ListNode[] lists) {
        // maintain a priority queue, with all elements from the head of each list
        // pop smallest in the response and add the next one, when it's not null
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val; // ascending order
        });

        for (ListNode node: lists) {
            if (node != null) pq.add(node);
        }

        ListNode response = new ListNode(0);
        ListNode cursor = response;
        ListNode poll;
        while (!pq.isEmpty()) {
            // O(nmlogn)
            poll = pq.poll();
            cursor.next = poll;
            cursor = cursor.next;
            if (poll.next != null) {
                pq.add(poll.next);
            }
        }

        return response.next;
    }
}