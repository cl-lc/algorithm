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
    protected abstract N newNode(V value);
}
