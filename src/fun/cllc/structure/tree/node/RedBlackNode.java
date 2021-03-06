package fun.cllc.structure.tree.node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class RedBlackNode<V extends Comparable<V>> extends BaseBinaryNode<V, RedBlackNode<V>> {
    private boolean red;

    public RedBlackNode(RedBlackNode<V> parent, V value) {
        super(parent, value);

        this.red = true;
    }

    public boolean isRed() {
        return red;
    }

    public void setRed(boolean red) {
        this.red = red;
    }
}
