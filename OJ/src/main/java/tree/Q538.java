package tree;

public class Q538 {
    public TreeNode convertBST(TreeNode root) {
        f(root,0);
        return root;
    }
    int f(TreeNode node, int sum) {
        if (node == null) return sum;
        sum = f(node.right, sum);
        node.val += sum;
        sum = f(node.left, node.val);
        return sum;
    }


}
