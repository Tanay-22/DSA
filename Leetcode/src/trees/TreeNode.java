package trees;

public class TreeNode
{
    Integer val;
    TreeNode left;
    TreeNode right;

    public TreeNode()
    {

    }
    public TreeNode(Integer val)
    {
        this.val = val;
    }

    public TreeNode(Integer val, TreeNode left, TreeNode right)
    {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Integer getVal()
    {
        return val;
    }

    public void setVal(Integer val)
    {
        this.val = val;
    }

    public TreeNode getLeft()
    {
        return left;
    }

    public void setLeft(TreeNode left)
    {
        this.left = left;
    }

    public TreeNode getRight()
    {
        return right;
    }

    public void setRight(TreeNode right)
    {
        this.right = right;
    }
}
