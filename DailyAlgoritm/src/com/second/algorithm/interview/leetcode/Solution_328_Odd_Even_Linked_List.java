package com.second.algorithm.interview.leetcode;

public class Solution_328_Odd_Even_Linked_List {
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

    public static void main(String[] args) {

    }

    private static ListNode oddEvenList(ListNode head) {
        // size 0-2는 이미 홀짝 순 정렬 상태
        if(head == null || head.next == null || head.next.next == null) return head;

        ListNode evenHead = new ListNode();
        ListNode even = evenHead;
        ListNode prev = head;

        while(prev.next != null && prev.next.next != null) {
            even.next = null;
            even.next = prev.next;

            // prev.next = null;
            prev.next = prev.next.next;

            even = even.next;
            prev = prev.next;
        }

        // 홀수 노드 마지막에 도달하는 경우 짝수 노드와 연결
        if(prev.next == null) {
            even.next = null;
            prev.next = evenHead.next;
        }
        else if(prev.next.next == null) {
            even.next = null;
            even.next = prev.next;

            prev.next = null;
            prev.next = evenHead.next;

        }

        return head;
    }
}
