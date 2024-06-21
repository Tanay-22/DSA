package LL;

public class QuickSort
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


    private static ListNode getNode(ListNode head, int index)
    {
        while(index-- > 0)
            head = head.next;
        return head;
    }

    private static ListNode getMiddle(ListNode head, ListNode tail)
    {
        ListNode midPrev = null;
        while (head != tail && head.next != tail)
        {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }

    private static ListNode getEnd(ListNode head, ListNode end)
    {
        while(head.next != end)
            head = head.next;
        return head;
    }
    public static ListNode sortList(ListNode head)
    {
        if(head == null || head.next == null)
            return head;
        ListNode tail = head;
        int size = 0;
        while(tail.next != null)
            tail = tail.next;
        sort(head, tail);

        return head;
    }
    private static void sort(ListNode low, ListNode high)
    {
        if(low == high)
            return;

        ListNode start = low, end = high;
        ListNode mid = getMiddle(start, end);
        ListNode pivot = mid;

        while(start.val <= end.val)
        {

            while(start.val < pivot.val)
                start = start.next;

            while(end.val > pivot.val)
                end = getEnd(start, end);

            if(start.val <= end.val)
            {
                int t = start.val;
                start.val = end.val;
                end.val = t;
                start = start.next;
                end = getEnd(start, end);
            }
        }
        sort(low, end);
        sort(start, high);
    }

    public static void main(String[] args)
    {
        int arr[] ={4,2,1,3};

        ListNode head = createLL(arr);
        head = sortList(head);
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
