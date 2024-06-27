package trees;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator
{
    private List<Integer> list;
    private int index;

    public BSTIterator(TreeNode root)
    {
        this.list = new ArrayList<>();
        this.inorder(list, root);
        this.index = -1;
    }

    private void inorder(List<Integer> list, TreeNode node)
    {
        if(node == null)
            return;

        inorder(list, node.left);
        list.add(node.val);
        inorder(list, node.right);
    }

    public int next()
    {
        try
        {
            return list.get(++index);
        }
        catch (Exception e)
        {
            return -1;
        }
    }

    public boolean hasNext()
    {
        return index + 1 <= list.size()-1;
    }
}