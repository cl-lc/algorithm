package net.cllc.structure.tree;

import net.cllc.structure.tree.node.Node;
import net.cllc.structure.tree.util.RotateHelper;
import net.cllc.structure.tree.util.TreeHelper;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class AVLTree<V extends Comparable<V>> extends BinarySearchTree<V> {
    private static final int BALANCE_FACTOR = 2;

    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    @Override
    protected Node<V> insertNode(Node<V> node, V value) {
        // 调用父类的插入节点方法
        node = super.insertNode(node, value);

        // 判断是否需要旋转
        if (needRotate(node)) {
            return rotate(node);
        }

        return node;
    }

    /**
     * 删除节点
     *
     * @param node
     * @param value
     * @return
     */
    @Override
    protected Node<V> deleteNode(Node<V> node, V value) {
        // 调用父类的删除节点方法
        node = super.deleteNode(node, value);

        // 判断是否需要旋转
        if (needRotate(node)) {
            return rotate(node);
        }

        return node;
    }

    /**
     * 是否需要旋转
     *
     * @param node
     * @return
     */
    private boolean needRotate(Node<V> node) {
        int balanceFactor = getBalanceFactor(node);
        return Math.abs(balanceFactor) == BALANCE_FACTOR;
    }

    /**
     * 旋转
     *
     * @param node
     * @return
     */
    private Node<V> rotate(Node<V> node) {
        int balanceFactor = getBalanceFactor(node);
        int subBalanceFactor;
        if (balanceFactor > 0) {
            subBalanceFactor = getBalanceFactor(node.getLeft());
        } else {
            subBalanceFactor = getBalanceFactor(node.getRight());
        }

        if (balanceFactor > 0) {
            // 注意这里是>=
            if (subBalanceFactor >= 0) {
                return RotateHelper.rightRotate(node);
            } else {
                return RotateHelper.lrRotate(node);
            }
        } else {
            // 注意这里是<=
            if (subBalanceFactor <= 0) {
                return RotateHelper.leftRotate(node);
            } else {
                return RotateHelper.rlRotate(node);
            }
        }
    }

    /**
     * 获取平衡因子
     * -1/0/1:平衡 -2:右侧过深 2:左侧过深
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node<V> node) {
        if (node == null) {
            return 0;
        }

        return TreeHelper.getHeight(node.getLeft()) - TreeHelper.getHeight(node.getRight());
    }

}
