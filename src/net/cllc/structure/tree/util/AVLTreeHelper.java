package net.cllc.structure.tree.util;

import net.cllc.structure.tree.node.AVLNode;

/**
 * @author chenlei
 * @date 2019-03-20
 */
public class AVLTreeHelper {
    /**
     * 获取高度
     *
     * @param node
     * @return
     */
    public static <V extends Comparable<V>> int getHeight(AVLNode<V> node) {
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
    public static <V extends Comparable<V>> void updateHeight(AVLNode<V> node) {
        if (node == null) {
            return;
        }

        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
    }
}
