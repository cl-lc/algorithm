package net.cllc.structure.tree;

import net.cllc.structure.tree.node.RedBlackNode;
import net.cllc.structure.tree.util.RotateHelper;
import net.cllc.structure.tree.util.TreeHelper;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class RedBlackTree<V extends Comparable<V>> extends BinarySearchTree<V, RedBlackNode<V>> {
    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    @Override
    protected RedBlackNode<V> insertNode(RedBlackNode<V> parent, RedBlackNode<V> node, V value) {
        // 调用父类的插入节点方法
        node = super.insertNode(parent, node, value);

        return adjustAfterInsert(node);
    }

    /**
     * 插入之后的调整
     *
     * @param node
     * @return
     */
    private RedBlackNode<V> adjustAfterInsert(RedBlackNode<V> node) {
        RedBlackNode<V> parent = node.getParent();
        // a: 空树插入第一个根节点
        if (parent == null) {
            node.setRed(false);
        }

        if (isRedNode(node)) {
            return adjustRedNodeAfterInsert(node);
        } else {
            return adjustBlackNodeAfterInsert(node);
        }
    }

    /**
     * 处理红色节点，只有以下几种情况需要处理
     * * 儿子节点，父节点，叔叔节点都是红色，需要变换颜色
     * * 儿子节点，父节点是红色，叔叔节点是黑色或者缺失，则要根据是LL/RR还是LR/RL
     * * * LR/RL，先转一次，然后返回，交由递归父节点的时候继续处理
     * * * LL/RR，不处理，交由递归父节点的时候继续处理
     *
     * @param node
     * @return
     */
    private RedBlackNode<V> adjustRedNodeAfterInsert(RedBlackNode<V> node) {
        if (!hasRedChild(node)) {
            return node;
        }

        RedBlackNode<V> parent = node.getParent();
        RedBlackNode<V> brother = TreeHelper.getBrother(node);
        // 子节点是红色，父节点是红色，叔叔节点是红色
        if (isRedNode(brother)) {
            node.setRed(false);
            brother.setRed(false);
            node.getParent().setRed(true);
            return node;
        }

        // LR或者RL的情况，旋转一次形成LL或者RR，然后再由父节点递归处理
        boolean isLR = node.equals(parent.getLeft()) && isRedNode(node.getRight());
        boolean isRL = node.equals(parent.getRight()) && isRedNode(node.getLeft());
        if (isLR) {
            return RotateHelper.leftRotate(node);
        } else if (isRL) {
            return RotateHelper.rightRotate(node);
        }

        return node;
    }

    /**
     * 处理黑色节点，只有以下情况
     * * 儿子节点，父节点是红色，叔叔节点是黑色或者缺失，则是LL或者LR
     * （LR/RL在红色节点中已经被处理成了LL和RL问题）
     *
     * @param node
     * @return
     */
    private RedBlackNode<V> adjustBlackNodeAfterInsert(RedBlackNode<V> node) {
        boolean isLL = isRedNode(node.getLeft()) && isRedNode(node.getLeft().getLeft());
        boolean isRR = isRedNode(node.getRight()) && isRedNode(node.getRight().getRight());
        if (isLL) {
            node = RotateHelper.rightRotate(node);
            node.getRight().setRed(true);
            node.setRed(false);
        } else if (isRR) {
            node = RotateHelper.leftRotate(node);
            node.getLeft().setRed(true);
            node.setRed(false);
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
    protected RedBlackNode<V> deleteNode(RedBlackNode<V> node, V value) {
        // 调用父类的删除节点方法
        node = super.deleteNode(node, value);


        return node;
    }

    /**
     * 新建一个节点
     *
     * @param value
     * @return
     */
    @Override
    protected RedBlackNode<V> newNode(RedBlackNode<V> parent, V value) {
        RedBlackNode<V> node = new RedBlackNode<>(value);
        node.setParent(parent);
        return node;
    }

    /**
     * 一个节点是否是红色节点
     *
     * @param node
     * @return
     */
    private boolean isRedNode(RedBlackNode<V> node) {
        return node != null && node.isRed();
    }

    /**
     * 是否含有红色节点
     *
     * @param node
     * @return
     */
    private boolean hasRedChild(RedBlackNode<V> node) {
        if (node == null) {
            return false;
        }
        return isRedNode(node.getLeft()) || isRedNode(node.getRight());
    }
}
