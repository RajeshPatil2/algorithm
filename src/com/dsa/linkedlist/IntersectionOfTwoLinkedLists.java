package com.dsa.linkedlist;

class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
		this.next = null;
	}
}

public class IntersectionOfTwoLinkedLists {

	// LeetCode logic method
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		if (headA == null || headB == null) {
			return null;
		}

		ListNode pA = headA;
		ListNode pB = headB;

		while (pA != pB) {
			pA = (pA == null) ? headB : pA.next;
			pB = (pB == null) ? headA : pB.next;
		}

		return pA; // intersection node OR null
	}

	// MAIN METHOD (for local testing)
	public static void main(String[] args) {

		// Common part (intersection)
		ListNode c1 = new ListNode(8);
		c1.next = new ListNode(4);
		c1.next.next = new ListNode(5);

		// List A: 4 → 1 → 8 → 4 → 5
		ListNode headA = new ListNode(4);
		headA.next = new ListNode(1);
		headA.next.next = c1;

		// List B: 5 → 6 → 1 → 8 → 4 → 5
		ListNode headB = new ListNode(5);
		headB.next = new ListNode(6);
		headB.next.next = new ListNode(1);
		headB.next.next.next = c1;

		ListNode result = getIntersectionNode(headA, headB);

		if (result != null) {
			System.out.println("Intersection Node Value: " + result.val);
		} else {
			System.out.println("No Intersection");
		}
	}
}
