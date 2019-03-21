package net.cllc.structure.tree;

import net.cllc.structure.tree.node.BaseBinaryNode;

/**
 * @author chenlei
 * @date 2019-03-14
 */
public abstract class BaseBinaryTree<V extends Comparable<V>, N extends BaseBinaryNode<V, N>> {
    protected N root;

    /**
     * 中序打印
     */
    public void printLDR() {
        printLDR(root);
    }

    /**
     * 中序打印
     *
     * @param node
     */
    private void printLDR(N node) {
        if (node == null || node.isLeaf()) {
            return;
        }

        printLDR(node.getLeft());
        System.out.println(node.getValue());
        printLDR(node.getRight());
    }

    /**
     * 新建一个节点
     *
     * @param parent
     * @param value
     * @return
     */
    protected abstract N newNode(N parent, V value);

    /**
     * 新建一个叶子
     *
     * @param parent
     * @return
     */
    protected N newLeaf(N parent) {
        N node = newNode(parent, null);
        node.setLeaf(true);
        return node;
    }

    /**
     * 新建一个带有叶子节点的节点
     *
     * @param parent
     * @param value
     * @return
     */
    protected N newNodeWithLeaf(N parent, V value) {
        N node = newNode(parent, value);
        node.setLeft(newLeaf(parent));
        node.setRight(newLeaf(parent));
        return node;
    }

    /**
     * 是否是个节点
     *
     * @param node
     * @return
     */
    protected boolean isANode(N node) {
        return node != null && !node.isLeaf();
    }
}
