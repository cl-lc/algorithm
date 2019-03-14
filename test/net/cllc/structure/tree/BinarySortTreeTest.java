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
        assert node.getLeft().getValue().equals(20);
        assert node.getRight().getValue().equals(35);

        node = tree.searchNode(70);
        assert node.getLeft() == null;
        assert node.getRight().getValue().equals(75);

        // 删除
        tree.deleteNode(30);
        node = tree.searchNode(30);
        assert node == null;
        node = tree.searchNode(50);
        assert node.getLeft().getValue().equals(32);
        node = tree.searchNode(32);
        assert node.getLeft().getValue().equals(20);
        assert node.getRight().getValue().equals(35);
        node = tree.searchNode(34);
        assert node.getLeft().getValue() == null;

        // 删除
        tree.deleteNode(80);
        node = tree.searchNode(80);
        assert node == null;
        node = tree.searchNode(50);
        assert node.getRight().getValue().equals(99);
        node = tree.searchNode(99);
        assert node.getLeft().getValue().equals(70);
        assert node.getRight().getValue() == null;
    }
}
