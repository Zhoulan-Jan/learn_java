package linkedList.exercise;

import linkedList.internal.ListNode;
import linkedList.internal.SingleLinkedList;

public class Demo {
//    class ListNode {
//        int val;
//        ListNode next;
//
//        public ListNode(int val) {
//            this.val = val;
//        }
//    }

    //删除链表中等于给定值 val 的所有结点
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            if (head.val == val) {
                return null;
            } else {
                return head;
            }
        }
        ListNode cur = head;
        //1.当开始若干个结点等于给定值
        while (cur.val == val) {
            cur = cur.next;
        }

        //2.当中间有结点等于给定值
        head = cur;
        ListNode prev = head;
        while (prev != null) {
            if (prev.next != null && prev.next.val == val) {
                ListNode c = prev.next;
                //存在中间连续几个结点等于给定值的情况
                while (c != null && c.val == val) {
                    c = c.next;
                }

                //删除等于给定值的结点
                prev.next = c;
            }
            prev = prev.next;
        }
        return head;
    }

    //反转单链表
    public static ListNode reverseList(ListNode head) {
        SingleLinkedList linkedList = new SingleLinkedList();
        //1.空链表
        if (head == null) {
            return head;
        }
        //2.只有一个节点
        if (head.next == null) {
            return head;
        }
        //3.多节点
        ListNode prev = null;
        ListNode cur = head;
        ListNode next;
        ListNode newHead = null;
        while (cur != null) {
            next = cur.next;
            if (cur.next == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = next;

        }
        return newHead;
    }

    //链表的中间节点
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //链表倒数第K个结点
    public static int size(ListNode head) {
        int len = 0;
        for (ListNode node = head; node != null; node = node.next) {
            len++;
        }
        return len;
    }
    public static ListNode FindKthToTail(ListNode head,int k) {
        int len = size(head);
        //判断合法性
        if (k < 0 || k > len) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        //快指针先走k步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast != null ) {//&& fast.next != null
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode newHead = new ListNode(-1);
        ListNode newTail = newHead;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                newTail.next = new ListNode(cur1.val);
                newTail = newTail.next;
                cur1 = cur1.next;
            } else {
                newTail.next = new ListNode(cur2.val);
                newTail = newTail.next;
                cur2 = cur2.next;
            }
        }

        if (cur1 != null) {
            while (cur1 != null) {
                newTail.next = new ListNode(cur1.val);
                newTail = newTail.next;
                cur1 = cur1.next;
            }
        }

        if (cur2 != null) {
            while (cur2 != null) {
                newTail.next = new ListNode(cur2.val);
                newTail = newTail.next;
                cur2 = cur2.next;
            }
        }
        return newHead.next;
    }

    //相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = size(headA);
        int lenB = size(headB);
        int k = 0;
        int flg = 1;
        if (lenA>lenB) {
            k = lenA - lenB;
        } else {
            k = lenB - lenA;
            flg = 0;
        }

        if (flg == 1) {
            for (int i = 0; i < k; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < k; i++) {
                headB = headB.next;
            }
        }

        while (headA != null && headB != null) {
            if (headB == headA) {
                return headB;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    //判断链表是否有环
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //返回链表入环的第一个节点
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        //不带环的情况
        // fast == slow 不能证明链表带环，比如链表只有一个节点
        if (fast == null || fast.next == null) {
            return null;
        }

        //cur 和 fast 同时走相同的步数
        //循环条件：cur ！= fast，若一开始 cur = fast说明两者已在入环结点
        ListNode cur = head;
        while (cur != fast) {
            cur = cur.next;
            fast = fast.next;
        }
        return cur;
    }


    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        ListNode head = null;
        head = linkedList.addLast(head,1);
        head = linkedList.addLast(head,2);
        head = linkedList.addLast(head,3);
        head = linkedList.addLast(head,4);
        head = linkedList.addLast(head,4);
        head = linkedList.addLast(head,5);
        //linkedList.display(head);

        //head = removeElements(head, 7);
        //head = reverseList(head);
        ListNode mid = middleNode(head);
        linkedList.display(mid);
    }
}

