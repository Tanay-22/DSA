package trees;

import java.util.Stack;

public class LockingTree
{
    public class Node
    {
        int data;
        int lock;
        Node left;
        Node right;

        public Node(int data)
        {
            this.data = data;
        }
    }
    private Node root;

    public LockingTree(int[] parent)
    {
        this.root = createTree(parent, 0);
    }

    private Node createTree(int arr[], int num)
    {
        if(num >= arr.length)
            return null;

        Node node = new Node(num);
        node.lock = arr[num];

        node.left= createTree(arr, num * 2 + 1);
        node.right = createTree(arr, num * 2 + 2);

        return node;
    }
    private Node findNode(int num, Node node)
    {
        Stack<Character> path = new Stack<>();
        while(num > 0)
        {
            if((num & 1) == 0)
            {
                path.push('r');
                num = (num - 2) / 2;
            }
            else
            {
                path.push('l');
                num = (num - 1) / 2;
            }
        }
        while (!path.isEmpty())
        {
            if(path.peek() == 'r')
                node = node.right;
            else
                node = node.left;
            path.pop();
        }
        return node;
    }

    public boolean lock(int num, int user)
    {
        Node node = findNode(num, root);

        if(node.lock == -1)
        {
            node.lock = user;
            return true;
        }
        return false;
    }

    public boolean unlock(int num, int user)
    {
        Node node = findNode(num, root);

        if(node.lock == user)
        {
            node.lock = -1;
            return true;
        }
        return false;
    }

    private boolean unlockAll(Node node)
    {
        if(node == null)
            return false;

        if(node.lock == -1)
        {
            return true;
        }
        else
            return unlockAll(node.left) || unlockAll(node.right);
    }
    private boolean checkAncestor(int num)
    {
        Stack<Character> path = new Stack<>();
        while(num > 0)
        {
            if((num & 1) == 0)
            {
                path.push('r');
                num = (num - 2) / 2;
            }
            else
            {
                path.push('l');
                num = (num - 1) / 2;
            }
        }
        boolean f = true;
        Node node = root;
        while (!path.isEmpty())
        {
            f = f && (node.lock == -1);
            if(path.pop() == 'r')
                node = node.right;
            else
                node = node.left;
        }
        return f;
    }
    public boolean upgrade(int num, int user)
    {
        Node node = findNode(num, root);

        if(node.lock == -1)
        {
            node.lock = user;
            return true && unlockAll(node.left) && unlockAll(node.right) && checkAncestor(num);
        }
        return false;
    }
}