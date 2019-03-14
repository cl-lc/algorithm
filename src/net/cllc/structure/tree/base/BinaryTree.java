package net.cllc.structure.tree.base;

/**
 * @author chenlei
 * @date 2019-03-14
 */
public class BinaryTree<T extends Comparable<T>> {
    protected Node<T> root;

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
    private void printLDR(Node node) {
        if (node == null) {
            return;
        }

        printLDR(node.getLeft());
        System.out.println(node.getValue());
        printLDR(node.getRight());
    }
}
