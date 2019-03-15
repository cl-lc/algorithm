package net.cllc.structure.tree;

import net.cllc.structure.tree.base.Node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    private static final int BALANCE_FACTOR = 2;

    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    @Override
    protected Node<T> insertNode(Node<T> node, T value) {
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
    protected Node<T> deleteNode(Node<T> node, T value) {
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
    private boolean needRotate(Node<T> node) {
        int balanceFactor = getBalanceFactor(node);
        return Math.abs(balanceFactor) == BALANCE_FACTOR;
    }

    /**
     * 旋转
     *
     * @param node
     * @return
     */
    private Node<T> rotate(Node<T> node) {
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
                return LLRotate(node);
            } else {
                return LRRotate(node);
            }
        } else {
            // 注意这里是<=
            if (subBalanceFactor <= 0) {
                return RRRotate(node);
            } else {
                return RLRotate(node);
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
    private int getBalanceFactor(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    /**
     * LL旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    private Node<T> LLRotate(Node<T> node) {
        Node<T> left = node.getLeft();
        Node<T> right = left.getRight();

        left.setRight(node);
        node.setLeft(right);

        // 更新两个节点的高度
        updateHeight(node);
        updateHeight(left);

        return left;
    }

    /**
     * RR旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    private Node<T> RRRotate(Node<T> node) {
        Node<T> right = node.getRight();
        Node<T> left = right.getLeft();

        right.setLeft(node);
        node.setRight(left);

        // 更新两个节点的高度
        updateHeight(node);
        updateHeight(right);

        return right;
    }

    /**
     * LR旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    private Node<T> LRRotate(Node<T> node) {
        // 先左旋一次
        Node<T> left = node.getLeft();
        Node<T> right = left.getRight();

        node.setLeft(right);
        left.setRight(right.getLeft());
        right.setLeft(left);

        // 更新两个节点的高度
        updateHeight(left);
        updateHeight(right);

        return LLRotate(node);
    }

    /**
     * RL旋转
     *
     * @param node
     * @return 返回新的根节点
     */
    private Node<T> RLRotate(Node<T> node) {
        // 先右旋一次
        Node<T> right = node.getRight();
        Node<T> left = right.getLeft();

        node.setRight(left);
        right.setLeft(left.getRight());
        left.setRight(right);

        // 更新两个节点的高度
        updateHeight(right);
        updateHeight(left);

        return RRRotate(node);
    }
}
