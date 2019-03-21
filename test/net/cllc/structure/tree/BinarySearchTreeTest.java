package net.cllc.structure.tree;

import net.cllc.structure.tree.node.BinarySearchNode;


/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySearchTreeTest {
    private static final int[] INPUT = new int[]{50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 99, 33};

    public static void main(String[] args) {
        // 初始化
        BinarySearchTree<Integer, BinarySearchNode<Integer>> tree = new BinarySearchTree<>();
        for (int i : INPUT) {
            tree.insertNode(i);
        }
        tree.printLDR();

        // 查找
        BinarySearchNode<Integer> node = tree.searchNode(70);
        assert node.getParent().getValue() == 80;
        assert node.getLeft().isLeaf();
        assert node.getRight().getValue().equals(75);

        node = tree.searchNode(30);
        assert node.getParent().getValue() == 50;
        assert node.getLeft().getValue().equals(20);
        assert node.getRight().getValue().equals(35);

        node = tree.searchNode(35);
        assert node.getParent().getValue() == 30;
        assert node.getLeft().getValue().equals(34);
        assert node.getRight().getValue().equals(40);

        // 删除
        tree.deleteNode(30);

        node = tree.searchNode(30);
        assert node == null;

        node = tree.searchNode(50);
        assert node.getLeft().getValue().equals(32);

        node = tree.searchNode(32);
        assert node.getParent().getValue() == 50;
        assert node.getLeft().getValue().equals(20);
        assert node.getRight().getValue().equals(35);

        node = tree.searchNode(34);
        assert node.getLeft().getValue().equals(33);

        node = tree.searchNode(33);
        assert node.getParent().getValue().equals(34);

        // 删除
        tree.deleteNode(80);

        node = tree.searchNode(80);
        assert node == null;

        node = tree.searchNode(50);
        assert node.getRight().getValue().equals(99);

        node = tree.searchNode(99);
        assert node.getParent().getValue() == 50;
        assert node.getLeft().getValue().equals(70);
        assert node.getRight().isLeaf();
    }
}
