package net.cllc.structure.tree.node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class Node<V extends Comparable<V>> {
    private V value;
    private Node<V> left;
    private Node<V> right;
    private Node<V> parent;
    private int height;

    public Node(V value) {
        this.value = value;
        this.height = 0;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<V> getLeft() {
        return left;
    }

    public void setLeft(Node<V> left) {
        this.left = left;
    }

    public Node<V> getRight() {
        return right;
    }

    public void setRight(Node<V> right) {
        this.right = right;
    }

    public Node<V> getParent() {
        return parent;
    }

    public void setParent(Node<V> parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
