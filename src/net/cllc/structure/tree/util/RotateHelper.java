package net.cllc.structure.tree.util;

import net.cllc.structure.tree.node.Node;

/**
 * @author chenlei
 * @date 2019-03-19
 */
public class RotateHelper {

    /**
     * 右旋
     *
     * @param node
     * @return 返回新的根节点
     */
    public static <V extends Comparable<V>> Node<V> rightRotate(Node<V> node) {
        Node<V> parent = node.getParent();
        Node<V> a = node.getLeft();
        Node<V> b = a.getRight();

        a.setRight(node);
        a.setParent(parent);

        if (b != null) {
            b.setParent(node);
        }

        node.setLeft(b);
        node.setParent(a);

        // 更新两个节点的高度
        TreeHelper.updateHeight(node);
        TreeHelper.updateHeight(a);

        return a;
    }

    /**
     * 左旋
     *
     * @param node
     * @return 返回新的根节点
     */
    public static <V extends Comparable<V>> Node<V> leftRotate(Node<V> node) {
        Node<V> parent = node.getParent();
        Node<V> a = node.getRight();
        Node<V> b = a.getLeft();

        a.setLeft(node);
        a.setParent(parent);

        if (b != null) {
            b.setParent(node);
        }

        node.setRight(b);
        node.setParent(a);

        // 更新两个节点的高度
        TreeHelper.updateHeight(node);
        TreeHelper.updateHeight(a);

        return a;
    }

    /**
     * LR旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    public static <V extends Comparable<V>> Node<V> lrRotate(Node<V> node) {
        // 先左旋一次左子节点
        node.setLeft(leftRotate(node.getLeft()));
        node.getLeft().setParent(node);
        TreeHelper.updateHeight(node);

        // 再右旋一次
        return rightRotate(node);
    }

    /**
     * RL旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    public static <V extends Comparable<V>> Node<V> rlRotate(Node<V> node) {
        // 先右旋一次右子节点
        node.setRight(rightRotate(node.getRight()));
        node.getRight().setParent(node);
        TreeHelper.updateHeight(node);

        // 再左旋一次
        return leftRotate(node);
    }
}
