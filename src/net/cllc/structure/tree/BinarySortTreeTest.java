package net.cllc.structure.tree;

import net.cllc.structure.tree.base.Node;


/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySortTreeTest {
    private static final int[] INPUT = new int[]{50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 99};

    private static Node<Integer> buildTree() {
        Node<Integer> root = null;
        for (int i : INPUT) {
            Node<Integer> node = BinarySortTreeUtil.insertNode(root, i);
            if (root == null) {
                root = node;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Node<Integer> root = buildTree();
        root.printLDR();

        // 查找
        Node<Integer> node = BinarySortTreeUtil.searchNode(root, 30);
        assert (node.getLeft().getValue().equals(20));
        assert (node.getRight().getValue().equals(35));

        node = BinarySortTreeUtil.searchNode(root, 70);
        assert (node.getLeft() == null);
        assert (node.getRight().getValue().equals(75));

        // 删除
        BinarySortTreeUtil.deleteNode(root, 35);
        node = BinarySortTreeUtil.searchNode(root, 30);
        assert (node.getRight().getValue().equals(40));

        node = BinarySortTreeUtil.searchNode(root, 40);
        assert (node.getLeft().getValue().equals(34));
        assert (node.getRight() == null);
    }
}
