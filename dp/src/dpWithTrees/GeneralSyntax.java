package dpWithTrees;

public class GeneralSyntax
{
    //  543. Diameter of Binary Tree
    public static int diameterOFTree(TreeNode root, int[] res)
    {
        if(root == null)
            return 0;

        int left = diameterOFTree(root.left, res);
        int right = diameterOFTree(root.right, res);

        int height = Math.max(left, right) + 1;

        int ans = Math.max(height, left + right + 1);

        res[0] = Math.max(res[0], ans);

        return res[0] - 1;
    }
}
