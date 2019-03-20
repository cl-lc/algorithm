package net.cllc.structure.tree.node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public abstract class BaseBinaryNode<V extends Comparable<V>, N extends BaseBinaryNode<V, N>> {
    private V value;
    private N left;
    private N right;
    private N parent;

    public BaseBinaryNode(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public N getLeft() {
        return left;
    }

    public void setLeft(N left) {
        this.left = left;
    }

    public N getRight() {
        return right;
    }

    public void setRight(N right) {
        this.right = right;
    }

    public N getParent() {
        return parent;
    }

    public void setParent(N parent) {
        this.parent = parent;
    }
}
