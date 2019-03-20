package net.cllc.structure.tree.node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class AVLNode<V extends Comparable<V>> extends BaseBinaryNode<V, AVLNode<V>> {
    private int height;

    public AVLNode(V value) {
        super(value);
        this.height = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
