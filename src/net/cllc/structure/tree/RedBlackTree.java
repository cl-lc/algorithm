package net.cllc.structure.tree;

import net.cllc.structure.tree.node.RedBlackNode;
import net.cllc.structure.tree.util.RotateHelper;
import net.cllc.structure.tree.util.BinaryTreeHelper;

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
        RedBlackNode<V> node = insert(value);
        adjustAfterInsert(node);
    }

    /**
     * 插入节点
     *
     * @param value
     * @return 插入的新节点
     */
    private RedBlackNode<V> insert(V value) {
        if (root == null) {
            root = newNodeWithLeaf(null, value);
            return root;
        }

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
        RedBlackNode<V> uncle = BinaryTreeHelper.getBrother(parent);
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
        boolean isLL = BinaryTreeHelper.isLeftChild(parent, node) && BinaryTreeHelper.isLeftChild(grandpa, parent);
        boolean isRR = BinaryTreeHelper.isRightChild(parent, node) && BinaryTreeHelper.isRightChild(grandpa, parent);
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
            updateRoot(grandpa, node);
        }

        // e: LR/RL
        boolean isLR = BinaryTreeHelper.isRightChild(parent, node) && BinaryTreeHelper.isLeftChild(grandpa, parent);
        boolean isRL = BinaryTreeHelper.isLeftChild(parent, node) && BinaryTreeHelper.isRightChild(grandpa, parent);
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
     * @param value
     * @return
     */
    @Override
    public void deleteNode(V value) {
        RedBlackNode<V> delete = searchNode(value);
        if (delete == null) {
            return;
        }
        // 前驱节点
        RedBlackNode<V> predecessor = findPredecessor(delete.getLeft());
        RedBlackNode<V> node = delete(value);

        // 删除一个黑色节点，但是补上来的是红色节点
        if (!isRedNode(predecessor) && isRedNode(node)) {
            node.setRed(false);
            return;
        }

        // 删除一个红色节点，不影响
        if (isRedNode(predecessor)) {
            return;
        }

        // 调整逻辑
        adjustAfterDelete(node);
    }

    /**
     * 删除节点
     *
     * @param value
     * @return 被删除后，补上来的节点
     */
    private RedBlackNode<V> delete(V value) {
        RedBlackNode<V> delete = searchNode(value);
        if (delete == null) {
            return null;
        }

        if (delete.getLeft() == null || delete.getRight() == null) {
            return deleteNodeWithoutTwoChild(delete);
        } else {
            // 有两个孩子
            // 找到前驱 节点
            RedBlackNode<V> predecessor = findPredecessor(delete.getLeft());
            // 删除后继节点
            RedBlackNode<V> next = deleteNodeWithoutTwoChild(predecessor);
            // 设置当前节点的值为后继节点的值
            delete.setValue(predecessor.getValue());
            return next;
        }
    }

    /**
     * 删除之后调整
     *
     * @param node
     */
    private void adjustAfterDelete(RedBlackNode<V> node) {
        if (node == null) {
            return;
        }

        // a: node是根节点
        RedBlackNode<V> parent = node.getParent();
        if (parent == null) {
            return;
        }

        RedBlackNode<V> brother = BinaryTreeHelper.getBrother(node);
        // b: 父节点是黑色，兄弟节点是红色
        if (!isRedNode(parent) && isRedNode(brother)) {
            if (BinaryTreeHelper.isLeftChild(parent, node)) {
                RotateHelper.leftRotate(parent);
            } else {
                RotateHelper.rightRotate(parent);
            }
            updateRoot(parent, brother);
            brother.setRed(false);
            parent.setRed(true);
            adjustAfterDelete(node);
            return;
        }

        RedBlackNode<V> lOfBrother = brother.getLeft();
        RedBlackNode<V> rOfBrother = brother.getRight();
        // c: 父节点，兄弟节点，兄弟的孩子节点都为黑色
        if (!isRedNode(parent) && !isRedNode(brother) && !isRedNode(lOfBrother) && !isRedNode(rOfBrother)) {
            brother.setRed(true);
            adjustAfterDelete(parent);
            return;
        }

        // d: 父节点是红色，其他节点是黑色
        if (isRedNode(parent) && !isRedNode(brother) && !isRedNode(lOfBrother) && !isRedNode(rOfBrother)) {
            parent.setRed(false);
            brother.setRed(true);
            return;
        }

        // e: 父节点颜色随意，兄弟节点为黑色，兄弟节点的内侧孩子为红色，外侧孩子为黑色（RL，LR）
        if (!isRedNode(brother)) {
            if (BinaryTreeHelper.isLeftChild(parent, node) && isRedNode(lOfBrother)) {
                RotateHelper.rightRotate(brother);
                lOfBrother.setRed(false);
                brother.setRed(true);
                adjustAfterDelete(node);
                return;
            } else if (BinaryTreeHelper.isRightChild(parent, node) && isRedNode(rOfBrother)) {
                RotateHelper.leftRotate(brother);
                rOfBrother.setRed(false);
                brother.setRed(true);
                adjustAfterDelete(node);
                return;
            }
        }

        // f: 父节点颜色随意，兄弟节点为黑色，兄弟节点的内侧孩子颜色随意，外侧孩子为红色
        if (!isRedNode(brother)) {
            if (BinaryTreeHelper.isLeftChild(parent, node) && isRedNode(rOfBrother)) {
                RotateHelper.leftRotate(parent);
                brother.setRed(parent.isRed());
                parent.setRed(false);
                rOfBrother.setRed(false);
                updateRoot(parent, brother);
            } else if (BinaryTreeHelper.isRightChild(parent, node) && isRedNode(lOfBrother)) {
                RotateHelper.rightRotate(parent);
                brother.setRed(parent.isRed());
                parent.setRed(false);
                lOfBrother.setRed(false);
                updateRoot(parent, brother);
            }
        }
    }

    /**
     * 旋转之后更新根节点
     *
     * @param oldNode
     * @param newNode
     */
    private void updateRoot(RedBlackNode<V> oldNode, RedBlackNode<V> newNode) {
        if (root == null) {
            return;
        }

        if (root.equals(oldNode)) {
            root = newNode;
        }
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
