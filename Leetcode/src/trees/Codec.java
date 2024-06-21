package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//297. Serialize and Deserialize Binary Tree
public class Codec
{
    public List<String> serialize(TreeNode root)
    {
        List<String> list = new ArrayList<>();
        serialize(root, list);
        return list;
    }
    private void serialize(TreeNode root, List<String> list)
    {
        if(root == null)
        {
            list.add("null");
            return;
        }

        list.add(String.valueOf(root.val));
        serialize(root.left, list);
        serialize(root.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(List<String> list)
    {
        Collections.reverse(list);
        TreeNode node = deserializeHelper(list);
        return node;
    }
    private TreeNode deserializeHelper(List<String> list)
    {
        String val = list.remove(list.size() - 1);

        if(val.charAt(0) == 'n')
            return null;

        TreeNode node  = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(list);
        node.right = deserializeHelper(list);

        return node;
    }
}
