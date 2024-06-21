package trees;


import java.util.*;

public class Main
{
    private static boolean isSymmetricHelper(TreeNode t1, TreeNode t2)
    {
        if (t1 == null && t2 == null)
            return true;
        else if (t1 == null || t2 == null)
            return false;
        else
            return t1.val == t2.val &&
                    isSymmetricHelper(t1.left, t2.right) &&
                    isSymmetricHelper(t1.right, t2.left);

    }

    public static boolean isSymmetric(TreeNode root)
    {
        return isSymmetricHelper(root, root);
    }

    //108. Convert Sorted Array to Binary Search Tree
    private static TreeNode sortedArrayToBSTHelper(int start, int end, int arr[])
    {
        if (start > end)
            return null;

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBSTHelper(start, mid - 1, arr);
        root.right = sortedArrayToBSTHelper(mid + 1, end, arr);

        return root;
    }

    public static TreeNode sortedArrayToBST(int[] nums)
    {
        if (nums.length == 0)
            return null;
        if (nums.length == 1)
            return new TreeNode(nums[0]);

        return sortedArrayToBSTHelper(0, nums.length - 1, nums);
    }

    //112. Path Sum
    private static boolean hasPathSumHelper(TreeNode root, int target, int sum)
    {
        if (root == null)
            return target == sum;

        return hasPathSumHelper(root.left, target, sum + root.val) || hasPathSumHelper(root.right, target, sum + root.val);
    }

    public static boolean hasPathSum(TreeNode root, int targetSum)
    {
        if (root == null)
            return false;
        return hasPathSumHelper(root, targetSum, 0);
    }

    //98. Validate Binary Search Tree
    public static boolean isValidBST(TreeNode root)
    {
        return isValidBST(root, null, null);
    }
    private static boolean isValidBST(TreeNode node, Integer low, Integer high)
    {
        if(node == null)
            return true;

        if((low != null && node.val <= low) || (high != null && node.val >= high))
            return false;

        return isValidBST(node.left, low, node.val) && isValidBST(node.right, node.val, high);
    }

    private static int maxDepth(int count, TreeNode node)
    {
        if (node == null)
            return count;
        else
            return Math.max(maxDepth(count + 1, node.left),
                    maxDepth(count + 1, node.right));
    }

    public static long kthLargestLevelSum(TreeNode root, int k)
    {
        int depth = maxDepth(0, root);
        long arr[] = new long[depth];
        levelSum(root, arr, 0, depth);

        Arrays.sort(arr);

        return k > depth ? -1 : arr[depth - k];
    }

    private static void levelSum(TreeNode node, long arr[], int currentLevel, int maxLevel)
    {
        if (node == null || currentLevel >= maxLevel)
            return;

        arr[currentLevel] += node.val;

        levelSum(node.left, arr, currentLevel + 1, maxLevel);
        levelSum(node.right, arr, currentLevel + 1, maxLevel);
    }

    public static int pathSum(TreeNode root, int targetSum)
    {
        if(root == null)
            return 0;
        int count = 0;

        count += pathSum(root, targetSum, 0);
        count += pathSum(root.left, targetSum) + pathSum(root.right, targetSum);

        return count;
    }
    private static int pathSum(TreeNode root, int targetSum, long sum)
    {
        if (root == null)
            return 0;
        int count = 0;
        sum += root.val;
        if (sum == targetSum)
            count++;

        count += pathSum(root.left, targetSum, sum);
        count += pathSum(root.right, targetSum, sum);

        return count;
    }

    //          BFS QUESTIONS

    //637. Average of Levels in Binary Tree
    public static List<Double> averageOfLevels(TreeNode root)
    {
        List<Double> ans = new ArrayList<>();

        if(root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            int levelSize = queue.size();
            double currentLevelSum = 0;
            for (int i = 0; i < levelSize; i++)
            {
                TreeNode currentNode = queue.poll();
                currentLevelSum += currentNode.val;

                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            ans.add(currentLevelSum / levelSize);
        }
        return ans;
    }

    //107. Binary Tree Level Order Traversal II
    public static List<List<Integer>> levelOrderBottom(TreeNode root)
    {
        List<List<Integer>> ans = new ArrayList<>();

        if(root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++)
            {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            ans.add(0, currentLevel);
        }
        return ans;
    }

    //103. Binary Tree Zigzag Level Order Traversal
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        List<List<Integer>> ans = new ArrayList<>();
        boolean leftToRight = true;
        if(root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++)
            {
                TreeNode currentNode = queue.poll();
                if(leftToRight)
                    currentLevel.add(currentNode.val);
                else
                    currentLevel.add(0, currentNode.val);

                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            ans.add(currentLevel);
            leftToRight = !leftToRight;
        }
        return ans;
    }

    //116. Populating Next Right Pointers in Each Node
    public static Node connect(Node root)
     {
        if(root == null)
            return root;

        Node leftMost = root;
        while(leftMost.left != null)
        {
            Node current = leftMost;
            while (current != null)
            {
                current.left.next = current.right;
                if(current.next != null)
                    current.right.next = current.next.left;

                current = current.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    //199. Binary Tree Right Side View
    public static List<Integer> rightSideView(TreeNode root)
    {
        List<Integer> ans = new ArrayList<>();
        if(root == null)
            return ans;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty())
        {
            int levelSize = queue.size();
            TreeNode currentNode = null;
            for (int i = 0; i < levelSize; i++)
            {
                currentNode = queue.poll();

                if(currentNode.left != null)
                    queue.offer(currentNode.left);
                if(currentNode.right != null)
                    queue.offer(currentNode.right);
            }
            ans.add(currentNode.val);
        }
        return ans;
    }

    //993. Cousins in Binary Tree
    public static boolean isCousins(TreeNode root, int x, int y)
    {
        TreeNode xx = findNode(root, x);
        TreeNode yy = findNode(root, y);
        if(findLevel(root, x, 0) == findLevel(root, y, 0) && !isSiblings(root, xx, yy))
            return true;
        return false;
    }
    private static int findLevel(TreeNode node, int val, int level)
    {
        if(node == null)
            return 0;

        if(node.val == val)
            return level;

        return findLevel(node.left, val, level+1) + findLevel(node.right, val, level+1);
    }
    private static TreeNode findNode(TreeNode root, int val)
    {
        if(root == null)
            return null;
        if(root.val == val)
            return root;
        TreeNode n = findNode(root.left, val);
        if(n != null)
            return n;
        return findNode(root.right, val);
    }
    private static boolean isSiblings(TreeNode root, TreeNode x, TreeNode y)
    {
        if(root == null)
            return false;

        return (root.left == x && root.right == y) || (root.left == y && root.right == x) ||
                isSiblings(root.left, x, y) || isSiblings(root.right, x, y);
    }

    //114. Flatten Binary Tree to Linked List
    public static void flatten(TreeNode root)
    {
        if (root == null)
            return;
        TreeNode node = root;
        while (node != null)
        {
            if (node.left != null)
            {
                TreeNode temp = node.left;
                while (temp.right != null)
                    temp = temp.right;

                temp.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }

    //236. Lowest Common Ancestor of a Binary Tree
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root == null)
            return null;

        if(root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null)
            return root;

        return left == null ? right : left;
    }

    //230. Kth Smallest Element in a BST
    public static int kthSmallest(TreeNode root, int k)
    {
        int count[] = new int[1];
        TreeNode node  = kthSmallest(root, k, count);
        return node.val;
    }
    private static TreeNode kthSmallest(TreeNode root, int k, int count[])
    {
        if(root == null)
            return null;

        TreeNode left = kthSmallest(root.left, k, count);

        if(left != null)
            return left;

        count[0]++;

        if(k == count[0])
            return root;

        return kthSmallest(root.right, k, count);
    }

    //105. Construct Binary Tree from Preorder and Inorder Traversal
    public static TreeNode buildTree(int[] preorder, int[] inorder)
    {
        if(preorder.length == 0)
            return null;

        int r = preorder[0];
        int index = 0;
        for (int i = 0; i < inorder.length; i++)
        {
            if(inorder[i] == r)
                index = i;
        }
        TreeNode node = new TreeNode(r);

        node.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1),
                Arrays.copyOfRange(inorder, 0 ,index));

        node.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
                Arrays.copyOfRange(inorder, index + 1 ,inorder.length));

        return node;
    }

    //129. Sum Root to Leaf Numbers
    public static int sumNumbers(TreeNode root)
    {
        if(root == null)
            return 0;

        List<Integer> list = new ArrayList<>();
        sumNumbers(root, list, 0);
        int sum = 0;
        for(Integer e: list)
            sum += e;

        return sum;

    }
    private static void sumNumbers(TreeNode root, List<Integer> list, int num)
    {
        if(root == null)
            return;

        num = num * 10 + root.val;
        if(root.left == null && root.right == null)
        {
            list.add(num);
            return;
        }

        sumNumbers(root.left, list, num);
        sumNumbers(root.right, list, num);
        num = (num - root.val) / 10;

    }

    //124. Binary Tree Maximum Path Sum
    public static int maxPathSum(TreeNode root)
    {
        int maxSum[] = new int[1];
        maxSum[0] = Integer.MIN_VALUE;
        maxPathSum(root, maxSum);

        return maxSum[0];
    }
    private static int maxPathSum(TreeNode root, int maxSum[])
    {
        if(root == null)
            return 0;

        int left = maxPathSum(root.left, maxSum);
        int right = maxPathSum(root.right, maxSum);

        left = Math.max(0, left);
        right = Math.max(0, right);
        int pathSum = left + right + root.val;

        maxSum[0] = Math.max(maxSum[0], pathSum);

        return Math.max(left, right) + root.val;
    }

    //257. Binary Tree Paths
    public static List<String> binaryTreePaths(TreeNode root)
    {
        List<String> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        binaryTreePaths(root, result, path);
        return result;
    }
    private static void binaryTreePaths(TreeNode root, List<String> list, List<Integer> path)
    {
        if(root == null)
            return;

        path.add(root.val);

        if(root.left == null && root.right == null)
        {
            list.add(listToPath(path));
            path.remove(path.size()-1);
            return;
        }

        binaryTreePaths(root.left, list, path);
        binaryTreePaths(root.right, list, path);
        path.remove(path.size()-1);
    }
    private static String listToPath(List<Integer> list)
    {
        StringBuilder sb = new StringBuilder();
        for( Integer e: list)
            sb.append(e + "->");

        return sb.substring(0, sb.length()-2);
    }

//    687. Longest Univalue Path
    public static int longestUnivaluePath(TreeNode root)
    {
        int[] maxCount = new int[1];
        longestUnivaluePath(root, maxCount);
        return maxCount[0];
    }
    private static int longestUnivaluePath(TreeNode root, int maxCount[])
    {
        if(root == null)
            return 0;

        int left = longestUnivaluePath(root.left, maxCount);
        int right = longestUnivaluePath(root.right, maxCount);

        int leftPath = 0, rightPath = 0;
        if(root.left != null && root.val == root.left.val)
            leftPath = left + 1;
        if(root.right != null && root.val == root.right.val)
            rightPath = right + 1;

        maxCount[0] = Math.max(maxCount[0], leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }

    //1367. Linked List in Binary Tree
    public static boolean isSubPath(ListNode head, TreeNode root)
    {
        if(head == null)
            return true;

        if(root == null)
            return false;

        if(isSamePath(head, root))
            return true;

        return isSubPath(head, root.left) || isSubPath(head, root.right);

    }
    private static boolean isSamePath(ListNode head, TreeNode root)
    {
        if(head == null)
            return true;

        if(root == null)
            return false;

        if(head.val != root.val)
            return false;

        return isSamePath(head.next, root.left) || isSamePath(head.next, root.right);
    }

    //1373. Maximum Sum BST in Binary Tree
    public static int maxSumBST(TreeNode root)
    {
        if(root == null || root.val < 0)
            return 0;

        if(isValidBST(root, null, null))
            return getSum(root);
        else
            return Math.max(maxSumBST(root.left), maxSumBST(root.right));

    }
    private static int getSum(TreeNode node)
    {
        if(node == null)
            return 0;

        return node.val + getSum(node.left) + getSum(node.right);
    }

    public static void main(String[] args)
    {
        Integer arr[] = { -4,-2,-5 };
        TreeNode root = createTree(arr);



        System.out.println(maxSumBST(root));
    }

    public static TreeNode createTree(Integer values[])
    {
        if (values == null || values.length == 0)
            return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (i < values.length)
        {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null)
            {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;

            if (i < values.length && values[i] != null)
            {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void displayTree(TreeNode node)
    {
        if (node != null)
        {
            displayTree(node.left);
            System.out.print(node.getVal() + " ");
            displayTree(node.right);
        }
    }
}
