package net.cllc.structure.tree;

import net.cllc.structure.tree.base.Node;


/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySortTreeTest {
    private static final int[] INPUT = new int[]{50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 99};

    public static void main(String[] args) {
        // 构造
        BinarySortTree<Integer> tree = new BinarySortTree<>();
        for (int i : INPUT) {
            tree.insertNode(i);
        }
        tree.printLDR();

        // 查找
        Node<Integer> node = tree.searchNode(30);
        assert (node.getLeft().getValue().equals(20));
        assert (node.getRight().getValue().equals(35));

        node = tree.searchNode(70);
        assert (node.getLeft() == null);
        assert (node.getRight().getValue().equals(75));

        // 删除
        tree.deleteNode(35);
        node = tree.searchNode(30);
        assert (node.getRight().getValue().equals(40));

        node = tree.searchNode(40);
        assert (node.getLeft().getValue().equals(34));
        assert (node.getRight() == null);
    }
}
