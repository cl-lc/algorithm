package net.cllc.structure.tree;

import net.cllc.structure.tree.node.RedBlackNode;


/**
 * @author chenlei
 * @date 2019-03-13
 */
public class RedBlackTreeTest {
    private static final int[] INPUT = new int[]{50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 99};

    public static void main(String[] args) {
        // 初始化
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        for (int i : INPUT) {
            tree.insertNode(i);
        }
        tree.printLDR();

        // 查找
        RedBlackNode<Integer> node = tree.searchNode(34);
        assert node.getParent() == null;
        assert !node.isRed();
        assert node.getLeft().getValue().equals(30);
        assert node.getRight().getValue().equals(50);

        node = tree.searchNode(30);
        assert node.getParent().getValue().equals(34);
        assert !node.isRed();
        assert node.getLeft().getValue().equals(20);
        assert node.getRight().getValue().equals(32);

        node = tree.searchNode(75);
        assert node.getParent().getValue().equals(50);
        assert node.isRed();
        assert node.getLeft().getValue().equals(70);
        assert node.getRight().getValue().equals(80);

        node = tree.searchNode(35);
        assert node.getParent().getValue().equals(50);
        assert !node.isRed();
        assert node.getLeft() == null;
        assert node.getRight().getValue().equals(40);
    }
}
