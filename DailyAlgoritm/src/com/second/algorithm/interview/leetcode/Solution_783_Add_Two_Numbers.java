package com.second.algorithm.interview.leetcode;

public class Solution_783_Add_Two_Numbers {
    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0, null);
        ListNode node = head;

        // l1, l2중 짧은 길이까지 덧셈
        while (l1 != null && l2 != null) {
            node.val += l1.val + l2.val;

            // sum이 10 넘는 경우
            if (node.val > 9) {
                node.val -= 10;
                node.next = new ListNode(1, null);
            } else if (l1.next != null || l2.next != null) node.next = new ListNode(0, null);
            node = node.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        // l1이 긴 경우
        if(l1 != null) node = remainOperation(l1, node);
        else if(l2 != null) node = remainOperation(l2,node);

        return head;
    }

    private ListNode remainOperation(ListNode l1, ListNode node) {
        while (l1 != null) {
            node.val += l1.val;
            if (node.val > 9) {
                node.val -= 10;
                node.next = new ListNode(1, null);
            } else if (l1.next != null) node.next = new ListNode(0, null);

            node = node.next;
            l1 = l1.next;
        }
        return node;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
