package net.cllc.structure.tree.base;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> left;
    private Node<T> right;
    private Node<T> parent;

    /**
     * 中序打印一棵树
     */
    public void printLDR() {
        printLDR(this);
    }

    private void printLDR(Node node) {
        if (node == null) {
            return;
        }

        printLDR(node.getLeft());
        System.out.println(node.getValue());
        printLDR(node.getRight());
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}
