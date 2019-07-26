package fun.cllc.structure.tree;

import fun.cllc.structure.tree.node.BNode;


/**
 * @author chenlei
 * @date 2019-03-25
 */
public class BTree<V extends Comparable<V>> {
    private BNode<V> root;
    private int minKeyCount;
    private int maxKeyCount;

    private BTree() {
    }

    public BTree(int degree) {
        this.minKeyCount = (int) (Math.ceil(degree * 1.0f / 2) - 1);
        this.maxKeyCount = degree - 1;
    }

    /**
     * 插入节点
     *
     * @param value
     */
    public void insertNode(V value) {
        if (root == null) {
            root = new BNode<>(null, value);
            root.setLeaf(true);
            return;
        }

        insertNode(root, value);
    }

    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    private void insertNode(BNode<V> node, V value) {
        if (!node.isLeaf()) {
            // 递归找到需要插入的叶子节点
            insertNode(node.findChildNode(value), value);
        } else {
            node.addKeyToLeaf(value);
        }
        // 插入之后从下往上调整
        BNode<V> subRoot = adjustAfterInsert(node);
        if (node.equals(root)) {
            root = subRoot;
        }
    }

    /**
     * 插入之后的调整
     *
     * @param node
     * @return 返回调整之后的子树的新根
     */
    private BNode<V> adjustAfterInsert(BNode<V> node) {
        int keyCount = node.getKeyCount();
        // 插入导致的调整，那么keyCount只会超出返回，不会小于范围
        if (keyCount <= maxKeyCount) {
            return node;
        }

        // 当前节点分裂成两个节点
        int index = keyCount / 2 - 1;
        BNode<V> left = node.subNode(0, index);
        for (BNode<V> item : left.getNodes()) {
            item.setParent(node);
        }

        BNode<V> right = node.subNode(index + 1, keyCount);
        for (BNode<V> item : left.getNodes()) {
            item.setParent(node);
        }

        BNode<V> parent;
        if (node.getParent() != null) {
            parent = node.getParent();
        } else {
            parent = new BNode<>();
            parent.setLeaf(false);
        }
        parent.addKeyToNode(node.getKey(index), left, right);
        return parent;
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public BNode<V> searchNode(V value) {
        return searchNode(root, value);
    }

    /**
     * 查找节点
     *
     * @param node
     * @param value
     * @return
     */
    private BNode<V> searchNode(BNode<V> node, V value) {
        if (node == null) {
            return null;
        }
        if (node.keyInNode(value)) {
            return node;
        }

        return searchNode(node.findChildNode(value), value);
    }
}

