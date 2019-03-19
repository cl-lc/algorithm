package net.cllc.structure.tree;

import net.cllc.structure.tree.node.Node;

/**
 * @author chenlei
 * @date 2019-03-14
 */
public abstract class BaseBinaryTree<V extends Comparable<V>> {
    protected Node<V> root;

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
    private void printLDR(Node<V> node) {
        if (node == null) {
            return;
        }

        printLDR(node.getLeft());
        System.out.println(node.getValue());
        printLDR(node.getRight());
    }

    /**
     * 新建一个节点
     *
     * @param value
     * @return
     */
    protected abstract Node<V> newNode(V value);
}
