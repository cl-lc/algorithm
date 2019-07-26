package fun.cllc.structure.tree;

import fun.cllc.structure.tree.node.AVLNode;


/**
 * @author chenlei
 * @date 2019-03-13
 */
public class AVLTreeTest {
    private static final int[] INPUT = new int[]{3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};

    public static void main(String[] args) {
        // 初始化
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i : INPUT) {
            tree.insertNode(i);
        }
        tree.printLDR();

        // 查找
        AVLNode<Integer> node = tree.searchNode(6);
        assert node.getHeight() == 1;
        assert node.getParent().getValue() == 4;
        assert node.getLeft().getValue().equals(5);
        assert node.getRight().isLeaf();

        node = tree.searchNode(4);
        assert node.getHeight() == 2;
        assert node.getParent().getValue() == 7;
        assert node.getLeft().getValue().equals(2);
        assert node.getRight().getValue().equals(6);

        node = tree.searchNode(13);
        assert node.getHeight() == 3;
        assert node.getParent().getValue() == 7;
        assert node.getLeft().getValue().equals(11);
        assert node.getRight().getValue().equals(15);

        node = tree.searchNode(15);
        assert node.getHeight() == 1;
        assert node.getParent().getValue() == 13;
        assert node.getLeft().getValue().equals(14);
        assert node.getRight().getValue().equals(16);

        // 删除
        tree.deleteNode(5);
        tree.deleteNode(3);
        tree.deleteNode(1);
        node = tree.searchNode(11);
        assert node.getHeight() == 3;
        assert node.getParent() == null;
        assert node.getLeft().getValue().equals(7);
        assert node.getRight().getValue().equals(13);

        node = tree.searchNode(7);
        assert node.getHeight() == 2;
        assert node.getParent().getValue() == 11;
        assert node.getLeft().getValue().equals(4);
        assert node.getRight().getValue().equals(9);
        tree.printLDR();

        // 删除
        tree.deleteNode(16);
        tree.deleteNode(14);
        tree.deleteNode(15);
        tree.deleteNode(12);

        node = tree.searchNode(11);
        assert node.getHeight() == 2;
        assert node.getParent().getValue() == 7;
        assert node.getLeft().getValue().equals(9);
        assert node.getRight().getValue().equals(13);

        node = tree.searchNode(7);
        assert node.getHeight() == 3;
        assert node.getParent() == null;
        assert node.getLeft().getValue().equals(4);
        assert node.getRight().getValue().equals(11);
        tree.printLDR();
    }
}
