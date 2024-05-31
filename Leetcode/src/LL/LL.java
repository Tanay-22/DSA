package LL;

public class LL
{

      public static class ListNode
      {
          int val;
          ListNode next;

          ListNode()
          {

          }

          ListNode(int val)
          {
              this.val = val;
          }

          ListNode(int val, ListNode next)
          {
              this.val = val; this.next = next;
          }

      }

    //1290. Convert Binary Number in a Linked List to Integer
    public static int getDecimalValue(ListNode head)
    {
        int size = (head != null) ? 1 : 0;
        ListNode temp = head;
        while(temp.next != null)
        {
            size++;
            temp = temp.next;
        }
        int result = 0;
        for (int i = size - 1; i >= 0; i--)
        {
            result += (int)Math.pow(2, i) * head.val;
            head = head.next;
        }

        return result;
    }

    //206. Reverse Linked List
    public static ListNode reverseList(ListNode head)
    {
        if(head == null)
            return head;

        int size = (head != null) ? 1 : 0;
        ListNode temp = head;
        while(temp.next != null)
        {
            size++;
            temp = temp.next;
        }

        ListNode head1 = head;
        for (int i = 0; i < size/2; i++)
        {
            ListNode target = head1;
            for (int j = i; j < size - i - 1; j++)
                target = target.next;

            int t = head1.val;
            head1.val = target.val;
            target.val = t;

            head1 = head1.next;
        }
        return head;
    }

    //876. Middle of the Linked List
    public static ListNode middleNode(ListNode head)
    {
        int size = (head != null) ? 1 : 0;
        ListNode temp = head;
        while(temp.next != null)
        {
            size++;
            temp = temp.next;
        }
        temp = head;
        for (int i = 1; i <= size/2; i++)
            temp = temp.next;

        return temp;
    }

    //21. Merge Two Sorted Lists
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2)
    {
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        while(list1 != null && list2 != null)
        {
            if(list1.val <= list2.val)
            {
                temp.next = list1;
                list1 = list1.next;
            }
            else
            {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 != null)
            temp.next = list1;

        if(list2 != null)
            temp.next = list2;
        return head.next;
    }
    public static void display(ListNode head)
    {
        while(head != null)
        {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }

    public static void main(String[] args)
    {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(1);


        node1.next = node2;
        node2.next = node4;
        node6.next = node3;
        node3.next = node5;


        display(mergeTwoLists(node1, node6));
    }
}
