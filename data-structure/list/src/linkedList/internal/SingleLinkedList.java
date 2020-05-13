package linkedList.internal;




//无头单向非循环链表
public class SingleLinkedList {
    //public ListNode head = null;

    //头插法
    public ListNode addFirst(ListNode head, int data) {
        ListNode node = new ListNode(data);
        if (head == null) {
            head = node;
            return head;
        }
        node.next = head;
        head = node;
        return head;
    }

    //尾插法
    public ListNode addLast(ListNode head, int data) {
        ListNode node = new ListNode(data);
        if (head == null) {
            head = node;
            return head;
        }
        //找到最后一个结点
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
        return head;
    }

    //任意位置插入
    public void addIndex(ListNode head, int index, int data) {
        //合法性校验
        int len = length(head);
        if (index < 0 || index > len) {
            System.out.println("插入位置不正确");
            return;
        }
        //头插
        if (index == 0) {
            addFirst(head, data);
            return;
        }
        //尾插
        if (index == len) {
            addLast(head, data);
            return;
        }
        //中间插入
        //找到 index-1 这个位置
        ListNode prev = head;
        while (index != 1) {
            prev = prev.next;
            index--;
        }
        ListNode node = new ListNode(data);
        //增加操作
        node.next = prev.next;
        prev.next = node;
    }

    //查找是否包含某关键字
    public boolean contains(ListNode head, int key) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == key) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    //删除第一次出现关键字 key 的结点
    public void remove(ListNode head, int key) {
        //当头结点为关键字 key
        if (head.val == key) {
            head = head.next;
            return;
        }
        ListNode prev =  head;
        while (prev.next != null) {
            if (prev.next.val == key) {
                //进行删除操作
                prev.next = prev.next.next;
                return;
            }
            prev = prev.next;
        }
        System.out.println("该链表没有该关键字");
    }

    //删除所有值为 key 的结点
    public ListNode removeAllKey(ListNode head, int key) {
        //当开头存在连续几个为关键字 key
        ListNode h = head;
        while (h.val == key) {
            h = h.next;
        }
        head = h;

        ListNode prev =  head;
        while (prev.next != null) {
            if (prev.next.val == key) {
                ListNode cur = prev.next;
                while (cur.val == key) {
                    cur = cur.next;
                }
                //进行删除操作
                prev.next = cur;
            }
            prev = prev.next;
        }
        return head;
    }

    //长度
    public int length(ListNode head) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    //打印
    public void display(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    //清除
    public void clear(ListNode head) {
        head = null;
    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        //linkedList.head = null;
//        linkedList.addFirst(1);
//        linkedList.addFirst(2);
//        linkedList.addFirst(3);
        ListNode head = null;
        head = linkedList.addLast(head, 1);
        head = linkedList.addLast(head,1);
        head = linkedList.addLast(head,2);
        head = linkedList.addLast(head,3);
        head = linkedList.addLast(head,4);
        head = linkedList.addLast(head,5);
        linkedList.display(head);

        linkedList.addIndex(head,3, 88);
        linkedList.addIndex(head,4,88);
        linkedList.display(head);

        System.out.println(linkedList.contains(head,88));
        System.out.println(linkedList.contains(head,77));

//        linkedList.remove(head,88);
//        linkedList.display(head);

        head = linkedList.removeAllKey(head,1);
        //linkedList.removeAllKey(head,88);
        linkedList.display(head);
    }
}
