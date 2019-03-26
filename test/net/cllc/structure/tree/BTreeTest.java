package net.cllc.structure.tree;

import net.cllc.structure.tree.node.BNode;


/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BTreeTest {
    private static final int[] INPUT = new int[]{33, 50, 75, 88, 24, 16, 70, 92, 73, 90, 18, 99};

    public static void main(String[] args) {
        // 初始化
        BTree<Integer> tree = new BTree<>(4);
        for (int i : INPUT) {
            tree.insertNode(i);
        }

        // 查找
        BNode<Integer> node = tree.searchNode(50);
        assert !node.isLeaf();
        assert node.getKeyCount() == 1;
        assert node.getKey(0).equals(50);

        node = tree.searchNode(90);
        assert !node.isLeaf();
        assert node.getKeyCount() == 2;
        assert node.getKey(0).equals(75);
        assert node.getKey(1).equals(90);
        assert node.getNodes().size() == 3;

        node = tree.searchNode(24);
        assert node.isLeaf();
        assert node.getKeyCount() == 2;
        assert node.getKey(0).equals(24);
        assert node.getKey(1).equals(33);

        node = tree.searchNode(99);
        assert node.isLeaf();
        assert node.getKeyCount() == 2;
        assert node.getKey(0).equals(92);
        assert node.getKey(1).equals(99);
    }
}
