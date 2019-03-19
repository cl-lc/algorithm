package net.cllc.structure.tree.node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySearchNode<V extends Comparable<V>> extends BaseBinaryNode<V, BinarySearchNode<V>> {
    public BinarySearchNode(V value) {
        super(value);
    }
}
