class BST
{
    public class Node
    {
        int value;
        Node left;
        Node right;
        int height;

        public Node(int value)
        {
            this.value = value;
        }

    }

    Node root;

    public BST()
    {
    }

    public int height(Node node)
    {
        if (node == null)
            return -1;
        return node.height;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public void display()
    {
        display(this.root, "Root Node: ");
    }

    private void display(Node node, String details)
    {
        if (node == null)
            return;

        System.out.println(details + node.value);
        display(node.left, "Left child of " + node.value + " : ");
        display(node.right, "Right child of " + node.value + " : ");
    }

    public void insert(int value)
    {
        this.root = insert(value, this.root);
    }

    private Node insert(int value, Node node)
    {
        if (node == null)
        {
            node = new Node(value);
            return node;
        }
        if (value < node.value)
            node.left = insert(value, node.left);
        if (value > node.value)
            node.right = insert(value, node.right);

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void populate(int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
            this.insert(nums[i]);
    }

    public void populatedSorted(int[] nums)
    {
        populatedSorted(nums, 0, nums.length);
    }

    private void populatedSorted(int[] nums, int start, int end)
    {
        if (start >= end)
            return;

        int mid = (start + end) / 2;

        this.insert(nums[mid]);
        populatedSorted(nums, start, mid);
        populatedSorted(nums, mid + 1, end);
    }



}