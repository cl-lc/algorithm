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
     * 获取高度
     *
     * @param node
     * @return
     */
    protected int getHeight(Node<V> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * 更新节点高度
     *
     * @param node
     */
    protected void updateHeight(Node<V> node) {
        if (node == null) {
            return;
        }

        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
    }
}
