package fun.cllc.structure.tree;

import fun.cllc.structure.tree.node.RedBlackNode;


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
        assert node.getLeft().isLeaf();
        assert node.getRight().getValue().equals(40);

        // 删除
        tree.deleteNode(75);
        node = tree.searchNode(80);
        assert node.getParent().getValue().equals(50);
        assert node.isRed();
        assert node.getLeft().getValue().equals(70);
        assert node.getRight().getValue().equals(99);

        tree.deleteNode(34);
        node = tree.searchNode(50);
        assert node.getParent() == null;
        assert !node.isRed();
        assert node.getLeft().getValue().equals(32);
        assert node.getRight().getValue().equals(80);

        node = tree.searchNode(32);
        assert !node.isRed();
        assert node.getLeft().getValue().equals(30);
        assert node.getRight().getValue().equals(35);

        node = tree.searchNode(20);
        assert node.getParent().getValue().equals(30);
        assert node.isRed();
        assert node.getLeft().isLeaf();
        assert node.getRight().isLeaf();

        tree.deleteNode(50);

        node = tree.searchNode(40);
        assert node.getParent() == null;
        assert !node.isRed();
        assert node.getLeft().getValue().equals(32);
        assert node.getRight().getValue().equals(80);

        node = tree.searchNode(35);
        assert node.getRight().isLeaf();
    }
}
