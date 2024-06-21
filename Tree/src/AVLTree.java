class AVLTree
{
    class Node
    {
        int value, height;
        Node left, right;

        Node(int d)
        {
            value = d;
            height = 1;
        }
    }

    Node root;

    // A utility function to get the height of the tree
    int height(Node N)
    {
        if (N == null)
            return 0;

        return N.height;
    }
    Node rightRotate(Node p)
    {
        Node c = p.left;
        Node t = c.right;

        c.right = p;
        p.left = t;

        p.height = Math.max(height(p.left), height(p.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;

        return c;
    }

    Node leftRotate(Node c)
    {
        Node p = c.right;
        Node t = p.left;

        p.left = c;
        c.right = t;

        c.height = Math.max(height(c.left), height(c.right)) + 1;
        p.height = Math.max(height(p.left), height(p.right)) + 1;

        return p;
    }

    int getBalance(Node N)
    {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public void insert(int value)
    {
        this.root = insert(this.root, value);
    }

    Node insert(Node node, int value)
    {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new Node(value));

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + Math.max(height(node.left),
                height(node.right));

		/* 3. Get the balance factor of this ancestor 
			node to check whether this node became 
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && value < node.left.value)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && value > node.right.value)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && value > node.left.value)
        {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && value < node.right.value)
        {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    public void populate(int[] nums)
    {
        for (int i = 0; i < nums.length; i++)
            this.insert(nums[i]);
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preOrder(Node node)
    {
        if (node != null)
        {
            System.out.print(node.value + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}
