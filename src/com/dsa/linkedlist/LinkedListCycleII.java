package com.dsa.linkedlist;

/*class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}
*/
public class LinkedListCycleII {

	// LeetCode required method
	public static ListNode detectCycle(ListNode head) {

		if (head == null || head.next == null) {
			return null;
		}

		ListNode slow = head;
		ListNode fast = head;

		// Step 1: Detect cycle
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) { // cycle found
				break;
			}
		}

		// No cycle
		if (fast == null || fast.next == null) {
			return null;
		}

		// Step 2: Find cycle starting node
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow; // cycle start node
	}

	// MAIN METHOD (for local testing)
	public static void main(String[] args) {

		// Example: [3,2,0,-4], pos = 1
		ListNode head = new ListNode(3);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(0);
		ListNode node4 = new ListNode(-4);

		head.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node2; // cycle here

		ListNode result = detectCycle(head);

		if (result != null) {
			System.out.println("Cycle starts at node value: " + result.val);
		} else {
			System.out.println("No cycle detected");
		}
	}
}
