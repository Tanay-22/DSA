package LL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //160. Intersection of Two Linked Lists
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        if(headA == null || headB == null)
            return null;

        ListNode tempA = headA, tempB = headB;

        while(tempA != null)
        {
            while (tempB != null)
            {
                if(tempA == tempB)
                    return tempA;
                else
                    tempB = tempB.next;
            }
            tempB = headB;
        }
        return null;
    }

    //141. Linked List Cycle
    public boolean hasCycle(ListNode head)
    {
        ListNode ref1 = head;
        ListNode ref2 = head;

        while (ref2 != null && ref2.next != null)
        {
            ref1 = ref1.next;
            ref2 = ref2.next.next;

            if(ref1 == ref2)
                return true;
        }
        return false;
    }

    //83. Remove Duplicates from Sorted List
    public ListNode deleteDuplicates(ListNode head)
    {
        ListNode temp = head;
        while (temp != null && temp.next != null)
        {
            if(temp.val == temp.next.val)
            {
                temp.next = temp.next.next;
            }
            else
                temp = temp.next;
        }
        return head;
    }

    //203. Remove Linked List Elements
    public ListNode removeElements(ListNode head, int val)
    {
        ListNode temp1 = new ListNode(0);
        temp1.next = head;
        ListNode temp2 = temp1;
        while (temp2.next != null)
        {
            if(temp2.next.val == val)
                temp2.next = temp2.next.next;
            else
                temp2 = temp2.next;
        }
        return temp1.next;
    }
    public static void display(ListNode head)
    {
        while(head != null)
        {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }

    //92. Reverse Linked List II
    public ListNode reverseBetween(ListNode head, int left, int right)
    {
        ListNode start = head;
        int size = right - left + 1;
        for (int i = 2; i <= left; i++)
        {
            if(i <= left)
                start = start.next;
        }
        ListNode temp = start;
        for (int i = 0; i < size/2; i++)
        {
            ListNode target = temp;
            for (int j = i; j < size - i - 1; j++)
                target = target.next;

            int t = temp.val;
            temp.val = target.val;
            target.val = t;

            temp = temp.next;
        }
        return head;
    }

    //143. Reorder List
    public static void reorderList(ListNode head)
    {
        ListNode temp = head;
        int size = 0;
        while (temp != null)
        {
            temp = temp.next;
            size++;
        }
        if((size & 1) == 0)
            reorderListHelper(head, size/2 - 1);
        else
            reorderListHelper(head, size/2);
    }
    public static void reorderListHelper(ListNode head, int i)
    {
        if(i == 0)
        {
            return;
        }

        ListNode last2 = head, refNext = head.next;
        while (last2.next.next != null)
            last2  = last2.next;

        ListNode last = last2.next;

        last.next = head.next;
        head.next = last;
        last2.next = null;

        reorderListHelper(refNext, i-1);

    }

    //19. Remove Nth Node From End of List
    public static ListNode removeNthFromEnd(ListNode head, int n)
    {
        int size = 0;
        ListNode temp = head;
        while (temp != null)
        {
            temp = temp.next;
            size++;
        }
        if(n > size || (n == 1 && size == 1))
            return null;
        if(n == size)
        {
            head = head.next;
            return head;
        }

        temp = head;
        while (size-- > n+1)
            temp = temp.next;
        temp.next = temp.next.next;

        return head;
    }

    //1721. Swapping Nodes in a Linked List
    public static ListNode swapNodes(ListNode head, int k)
    {
        int size = 0;
        ListNode temp1 = head, temp2;
        while (temp1 != null)
        {
            temp1 = temp1.next;
            size++;
        }
        temp1 = head;
        temp2 = head;
        for (int i = 2; i <= k || i <= size + 1 - k; i++)
        {

            if(i <= k)
                temp1 = temp1.next;

            if(i <= size - k + 1)
                temp2 = temp2.next;

        }

        int t = temp1.val;
        temp1.val = temp2.val;
        temp2.val = t;
        return head;
    }

    //2. Add Two Numbers
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode sum = new ListNode(Integer.MIN_VALUE);
        ListNode temp1 = sum, temp2 = null;
        int carry = 0;
        while (l1 != null && l2 != null)
        {
            int s = l1.val + l2.val + carry % 10;
            carry = s / 10;

            temp2 = new ListNode(s % 10);
            temp1.next = temp2;
            temp1 = temp2;

            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null)
        {
            int s = l1.val + carry % 10;
            carry = s / 10;

            temp2 = new ListNode(s % 10);
            temp1.next = temp2;
            temp1 = temp2;

            l1 = l1.next;
        }
        while (l2 != null)
        {
            int s = l2.val + carry % 10;
            carry = s / 10;
            temp2 = new ListNode(s % 10);
            temp1.next = temp2;
            temp1 = temp2;

            l2 = l2.next;
        }
        while(carry > 0)
        {
            temp2 = new ListNode(carry % 10);
            temp1.next = temp2;
            temp1 = temp2;
            carry /= 10;
        }
        return sum.next;
    }

    //61. Rotate List
    public static ListNode rotateRight(ListNode head, int k)
    {
        if(head == null)
            return null;

        if(k == 0)
            return head;

        ListNode temp = head;
        int size = 0;
        while (temp != null)
        {
            size++;
            temp = temp.next;
        }
        temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = head;

        temp = head;
        int i = size - 1 - k % size;
        while (i-- > 0)
            temp = temp.next;

        head = temp.next;
        temp.next = null;

        return head;
    }

    //82. Remove Duplicates from Sorted List II
    public static ListNode deleteDuplicates2(ListNode head)
    {
        if(head == null || head.next == null)
            return head;

        Set<Integer> set = new HashSet<>();
        ListNode temp = head;
        while (temp.next != null)
        {
            if(temp.val == temp.next.val)
                set.add(temp.val);
            temp = temp.next;
        }

        ListNode newHead = new ListNode(Integer.MAX_VALUE);
        temp = newHead;
        while (head != null)
        {
            if(!set.contains(head.val))
            {
                temp.next = head;
                temp = temp.next;
            }
            head = head.next;
        }
        temp.next = null;
        return newHead.next;
    }

    //86. Partition List
    public static ListNode partition(ListNode head, int x)
    {
        ListNode temp = head;
        ListNode less = new ListNode(Integer.MIN_VALUE);
        ListNode greater = new ListNode(Integer.MIN_VALUE);
        ListNode res1 = less, res2 = greater;

        while (temp != null)
        {
            ListNode newNode = new ListNode(temp.val);
            if(temp.val < x)
            {
                less.next = newNode;
                less = less.next;
            }
            else
            {
                greater.next = newNode;
                greater = greater.next;
            }
            temp = temp.next;
        }
        less.next = res2.next;
        return res1.next;
    }

    //24. Swap Nodes in Pairs
    public static ListNode swapPairs(ListNode head)
    {
        if(head == null || head.next == null)
            return head;

        ListNode odd = new ListNode(Integer.MAX_VALUE);
        ListNode even = new ListNode(Integer.MAX_VALUE);
        ListNode o = odd, e = even, h = head;

        int pos = 1;
        while (h != null)
        {
            if((pos & 1) == 1)
            {
                o.next = h;
                o = o.next;
            }
            else
            {
                e.next = h;
                e = e.next;
            }
            h = h.next;
            pos++;
        }
        o.next = e.next = null;
        head = new ListNode(Integer.MIN_VALUE);
        h = head;
        e = even.next;
        o = odd.next;
        while (o != null && e != null)
        {
            h.next = e;
            e = e.next;
            h = h.next;

            h.next = o;
            o = o.next;
            h = h.next;
        }
        return head.next;
    }

    //1171. Remove Zero Sum Consecutive Nodes from Linked List
    public static ListNode removeZeroSumSublists(ListNode head)
    {
        ListNode h = head;
        while(h != null)
        {
            removeZeroSumSublistsHelper(h);
            h = h.next;
        }

        while (head != null && head.val == 0)
            head = head.next;
        if(head == null)
            return null;
        h = head;
        while(h.next != null)
        {
            if(h.next.val == 0)
                h.next = h.next.next;
            else
                h = h.next;
        }

        return head;
    }
    private static void removeZeroSumSublistsHelper(ListNode head)
    {
        int sum = 0;
        ListNode end = null, h = head;
        while (h != null)
        {
            sum += h.val;
            h = h.next;

            if(sum == 0)
            {
                end = h;
                h = head;
                while (h != end)
                {
                    h.val = 0;
                    h = h.next;
                }
                break;
            }
        }
    }


    public static void main(String[] args)
    {
        int arr[] = {1,3,2,-3,-2,5,5,-5,1};
        ListNode head = createLL(arr);

        head = removeZeroSumSublists(head);
        displayLL(head);
    }

    public static ListNode createLL(int arr[])
    {
        ListNode head = null;
        ListNode current = null;
        for (int val : arr)
        {
            if (head == null)
            {
                head = new ListNode(val);
                current = head;
            }
            else
            {
                current.next = new ListNode(val);
                current = current.next;
            }
        }
        return head;
    }

    public static void displayLL(ListNode head)
    {
        ListNode current = head;
        while (current != null)
        {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();

    }
}
