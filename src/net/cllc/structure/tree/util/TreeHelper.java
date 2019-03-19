package net.cllc.structure.tree.util;

import net.cllc.structure.tree.node.BaseBinaryNode;

/**
 * @author chenlei
 * @date 2019-03-19
 */
public class TreeHelper {
    /**
     * 获取高度
     *
     * @param node
     * @return
     */
    public static <V extends Comparable<V>, N extends BaseBinaryNode<V, N>> int getHeight(N node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * 更新节点高度
     *
     * @param node
     */
    public static <V extends Comparable<V>, N extends BaseBinaryNode<V, N>> void updateHeight(N node) {
        if (node == null) {
            return;
        }

        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
    }
}
