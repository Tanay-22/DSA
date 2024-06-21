
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//  1. When answer lies near the root
//  2. When asked to search by level
public class Search
{
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

    public static List<List<Integer>> breadthForSearch(TreeNode root)
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
            ans.add(currentLevel);
        }
        return ans;
    }

    public static void depthForSearch(TreeNode root)
    {

    }


    public static void main(String[] args)
    {
        Integer arr[] = { 3,9,20,null,null,15,7 };

        TreeNode root = createTree(arr);
        List<List<Integer>> list = breadthForSearch(root);

        list.stream().forEach(System.out::println);
    }

}
