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
     * @param value
     */
    @Override
    public void insertNode(V value) {
        if (root == null) {
            root = newNodeWithLeaf(null, value);
            root.setRed(false);
            return;
        }

        // 插入节点
        RedBlackNode<V> node = insert(value);
        // 调整
        adjustAfterInsert(node);
    }

    /**
     * 插入节点
     *
     * @param value
     * @return
     */
    private RedBlackNode<V> insert(V value) {
        RedBlackNode<V> node = root;
        RedBlackNode<V> parent;
        do {
            parent = node;
            node = node.getValue().compareTo(value) > 0 ? node.getLeft() : node.getRight();
        } while (isANode(node));

        node = newNodeWithLeaf(parent, value);
        if (parent.getValue().compareTo(value) > 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }

        return node;
    }

    /**
     * 插入之后的调整
     *
     * @param node
     */
    private void adjustAfterInsert(RedBlackNode<V> node) {
        RedBlackNode<V> parent = node.getParent();
        // a: 第一个根节点
        if (parent == null) {
            node.setRed(false);
            return;
        }

        // b: 父节点是黑色
        if (!parent.isRed()) {
            return;
        }

        RedBlackNode<V> grandpa = parent.getParent();
        RedBlackNode<V> uncle = TreeHelper.getBrother(parent);
        // c: 父节点和叔叔节点是红色
        if (isRedNode(parent) && isRedNode(uncle)) {
            parent.setRed(false);
            uncle.setRed(false);
            grandpa.setRed(true);
            // 递归判断
            adjustAfterInsert(grandpa);
            return;
        }

        // d: LL/RR
        boolean isLL = node.equals(parent.getLeft()) && parent.equals(grandpa.getLeft());
        boolean isRR = node.equals(parent.getRight()) && parent.equals(grandpa.getRight());
        if (isLL || isRR) {
            if (isLL) {
                node = RotateHelper.rightRotate(grandpa);
                node.getRight().setRed(true);
            } else {
                node = RotateHelper.leftRotate(grandpa);
                node.getLeft().setRed(true);
            }

            node.setRed(false);
            // 如果祖父节点就是根节点的话，需要重置根节点变量
            if (grandpa.equals(root)) {
                root = node;
            }
        }

        // e: LR/RL
        boolean isLR = node.equals(parent.getRight()) && parent.equals(grandpa.getLeft());
        boolean isRL = node.equals(parent.getLeft()) && parent.equals(grandpa.getRight());
        if (isLR || isRL) {
            if (isLR) {
                RotateHelper.leftRotate(parent);
            } else {
                RotateHelper.rightRotate(parent);
            }

            // LR/RL被处理成LL/RR，再次调用调整函数
            adjustAfterInsert(parent);
        }
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
        return new RedBlackNode<>(parent, value);
    }

    /**
     * 新建一个叶子节点
     *
     * @param parent
     * @return
     */
    @Override
    protected RedBlackNode<V> newLeaf(RedBlackNode<V> parent) {
        RedBlackNode<V> leaf = super.newLeaf(parent);
        leaf.setRed(false);
        return leaf;
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
}
