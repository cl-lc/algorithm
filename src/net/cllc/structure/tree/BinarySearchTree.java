package net.cllc.structure.tree;

import net.cllc.structure.tree.node.BaseBinaryNode;
import net.cllc.structure.tree.node.BinarySearchNode;
import net.cllc.structure.tree.util.BinaryTreeHelper;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySearchTree<V extends Comparable<V>, N extends BaseBinaryNode<V, N>> extends BaseBinaryTree<V, N> {
    /**
     * 插入节点
     *
     * @param value
     */
    public void insertNode(V value) {
        root = insertNode(null, root, value);
    }

    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    protected N insertNode(N parent, N node, V value) {
        if (!isANode(node)) {
            node = newNodeWithLeaf(parent, value);
            BinaryTreeHelper.updateChild(parent, node);
            return node;
        }

        if (node.getValue().compareTo(value) > 0) {
            insertNode(node, node.getLeft(), value);
        } else {
            insertNode(node, node.getRight(), value);
        }

        return node;
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public N searchNode(V value) {
        if (root == null) {
            return null;
        }

        return searchNode(root, value);
    }

    /**
     * 查找节点
     *
     * @param node
     * @param value
     * @return
     */
    private N searchNode(N node, V value) {
        if (!isANode(node)) {
            return null;
        }

        int compare = node.getValue().compareTo(value);
        if (compare > 0) {
            return searchNode(node.getLeft(), value);
        } else if (compare < 0) {
            return searchNode(node.getRight(), value);
        } else {
            return node;
        }
    }

    /**
     * 删除节点
     *
     * @param value
     * @return
     */
    public void deleteNode(V value) {
        root = deleteNode(root, value);
    }

    /**
     * 删除节点
     *
     * @param node
     * @param value
     * @return
     */
    protected N deleteNode(N node, V value) {
        if (!isANode(node)) {
            return null;
        }

        int compare = node.getValue().compareTo(value);
        if (compare > 0) {
            // 当前值比待删除的值要大，则从左子树查找
            deleteNode(node.getLeft(), value);
        } else if (compare < 0) {
            // 当前值比待删除的值要小，则从右子树查找
            deleteNode(node.getRight(), value);
        } else {
            // 找到要删除的值了
            if (!isANode(node.getLeft()) || !isANode(node.getRight())) {
                deleteNodeWithoutTwoChild(node);
            } else {
                // 有两个孩子
                // 找到后继节点
                N successor = findSuccessor(node.getRight());
                // 删除后继节点
                deleteNode(node, successor.getValue());
                // 设置当前节点的值为后继节点的值
                node.setValue(successor.getValue());
            }
        }

        return node;
    }

    /**
     * 删除只有一个子节点或者没有子节点的节点
     *
     * @param node
     * @return
     */
    protected N deleteNodeWithoutTwoChild(N node) {
        // 有0个或者1个孩子
        N parent = node.getParent();
        N child = isANode(node.getLeft()) ? node.getLeft() : node.getRight();
        child.setParent(parent);

        // 更新父节点的子节点
        if (BinaryTreeHelper.isLeftChild(parent, node)) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }

        return child;
    }

    /**
     * 找到一个节点的后继节点
     *
     * @param node
     * @return
     */
    protected N findSuccessor(N node) {
        if (isANode(node.getLeft())) {
            return findSuccessor(node.getLeft());
        }

        return node;
    }

    /**
     * 找到一个节点的前驱节点
     *
     * @param node
     * @return
     */
    protected N findPredecessor(N node) {
        if (isANode(node.getRight())) {
            return findPredecessor(node.getRight());
        }

        return node;
    }

    /**
     * 新建一个节点
     *
     * @param value
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    protected N newNode(N parent, V value) {
        return (N) new BinarySearchNode<>((BinarySearchNode<V>) parent, value);
    }
}
