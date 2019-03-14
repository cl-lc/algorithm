package net.cllc.structure.tree.base;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> left;
    private Node<T> right;
    private int height;

    public Node(T value) {
        this.value = value;
        this.height = 0;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
