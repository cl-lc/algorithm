package net.cllc.structure.tree.util;

import net.cllc.structure.tree.node.BaseBinaryNode;

/**
 * @author chenlei
 * @date 2019-03-19
 */
public class TreeHelper {
    /**
     * 更新孩子节点
     *
     * @param parent
     * @param child
     * @param <V>
     * @param <N>
     */
    public static <V extends Comparable<V>, N extends BaseBinaryNode<V, N>> void updateChild(N parent, N child) {
        if (parent == null) {
            return;
        }

        if (parent.getValue().compareTo(child.getValue()) > 0) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }

    /**
     * 获取节点的兄弟节点
     *
     * @param node
     * @param <V>
     * @param <N>
     * @return
     */
    public static <V extends Comparable<V>, N extends BaseBinaryNode<V, N>> N getBrother(N node) {
        N parent = node.getParent();
        if (parent == null) {
            return null;
        }

        if (node.equals(parent.getLeft())) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }
}