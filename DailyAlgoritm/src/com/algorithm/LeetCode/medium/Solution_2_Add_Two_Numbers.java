package com.algorithm.LeetCode.medium;

public class Solution_2_Add_Two_Numbers {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {


    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode head = node;
        int carry = 0;
        while(l1!=null || l2!=null){
            int sum = 0;
            if(l1!=null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                sum+= l2.val;
                l2 = l2.next;
            }
            ListNode sumNode = new ListNode(sum%10);

            carry = sum / 10;
            head.next = sumNode;
            head = head.next;
        }
        if(carry > 0){
            ListNode lastNode =new ListNode(carry);
            head.next = lastNode;
        }
        return node.next;
    }

}
