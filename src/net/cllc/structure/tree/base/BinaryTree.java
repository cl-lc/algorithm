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

    /**
     * 获取高度
     *
     * @param node
     * @return
     */
    protected int getHeight(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }
}
