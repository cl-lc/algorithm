package net.cllc.structure.tree;

import net.cllc.structure.tree.node.AVLNode;
import net.cllc.structure.tree.util.RotateHelper;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class AVLTree<V extends Comparable<V>> extends BinarySearchTree<V, AVLNode<V>> {
    private static final int BALANCE_FACTOR = 2;

    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    @Override
    protected AVLNode<V> insertNode(AVLNode<V> parent, AVLNode<V> node, V value) {
        // 调用父类的插入节点方法
        node = super.insertNode(parent, node, value);
        updateHeight(node);

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
    protected AVLNode<V> deleteNode(AVLNode<V> node, V value) {
        // 调用父类的删除节点方法
        node = super.deleteNode(node, value);
        updateHeight(node);

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
    private boolean needRotate(AVLNode<V> node) {
        int balanceFactor = getBalanceFactor(node);
        return Math.abs(balanceFactor) == BALANCE_FACTOR;
    }

    /**
     * 旋转
     *
     * @param node
     * @return
     */
    private AVLNode<V> rotate(AVLNode<V> node) {
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
                return rightRotate(node);
            } else {
                return lrRotate(node);
            }
        } else {
            // 注意这里是<=
            if (subBalanceFactor <= 0) {
                return leftRotate(node);
            } else {
                return rlRotate(node);
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
    private int getBalanceFactor(AVLNode<V> node) {
        if (!isANode(node)) {
            return 0;
        }

        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    /**
     * 右旋
     *
     * @param node
     * @return 返回新的根节点
     */
    private AVLNode<V> rightRotate(AVLNode<V> node) {
        RotateHelper.rightRotate(node);
        updateHeight(node);
        updateHeight(node.getParent());

        return node.getParent();
    }

    /**
     * 左旋
     *
     * @param node
     * @return 返回新的根节点
     */
    private AVLNode<V> leftRotate(AVLNode<V> node) {
        RotateHelper.leftRotate(node);
        updateHeight(node);
        updateHeight(node.getParent());

        return node.getParent();
    }

    /**
     * LR旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    private AVLNode<V> lrRotate(AVLNode<V> node) {
        // 先左旋一次左子节点
        leftRotate(node.getLeft());

        // 再右旋一次
        return rightRotate(node);
    }

    /**
     * RL旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    private AVLNode<V> rlRotate(AVLNode<V> node) {
        // 先右旋一次右子节点
        rightRotate(node.getRight());

        // 再左旋一次
        return leftRotate(node);
    }

    /**
     * 获取高度
     *
     * @param node
     * @return
     */
    private int getHeight(AVLNode<V> node) {
        if (!isANode(node)) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * 更新节点高度
     *
     * @param node
     */
    private void updateHeight(AVLNode<V> node) {
        if (!isANode(node)) {
            return;
        }

        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
    }

    /**
     * 新建一个节点
     *
     * @param value
     * @return
     */
    @Override
    protected AVLNode<V> newNode(AVLNode<V> parent, V value) {
        return new AVLNode<>(parent, value);
    }
}
