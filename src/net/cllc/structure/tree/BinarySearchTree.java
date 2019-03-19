package net.cllc.structure.tree;

import net.cllc.structure.tree.node.BaseBinaryNode;
import net.cllc.structure.tree.node.BinarySearchNode;
import net.cllc.structure.tree.util.TreeHelper;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySearchTree<V extends Comparable<V>, N extends BaseBinaryNode<V, N>> extends BaseBinaryTree<V, N> {
    /**
     * 插入节点
     *
     * @param value
     * @return
     */
    public void insertNode(V value) {
        root = insertNode(root, value);
    }

    /**
     * 插入节点
     *
     * @param node
     * @param value
     * @return
     */
    protected N insertNode(N node, V value) {
        if (node == null) {
            return newNode(value);
        }

        int compare = node.getValue().compareTo(value);
        if (compare > 0) {
            node.setLeft(insertNode(node.getLeft(), value));
            node.getLeft().setParent(node);
        } else {
            node.setRight(insertNode(node.getRight(), value));
            node.getRight().setParent(node);
        }

        TreeHelper.updateHeight(node);

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
        if (node == null) {
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
        if (node == null) {
            return null;
        }

        int compare = node.getValue().compareTo(value);
        if (compare > 0) {
            // 当前值比待删除的值要大，则从左子树查找
            node.setLeft(deleteNode(node.getLeft(), value));
            if (node.getLeft() != null) {
                node.getLeft().setParent(node);
            }
        } else if (compare < 0) {
            // 当前值比待删除的值要小，则从右子树查找
            node.setRight(deleteNode(node.getRight(), value));
            if (node.getRight() != null) {
                node.getRight().setParent(node);
            }
        } else {
            // 找到要删除的值了
            if (node.getLeft() == null || node.getRight() == null) {
                // 有0个或者1个孩子
                node = node.getLeft() == null ? node.getRight() : node.getLeft();
            } else {
                // 有两个孩子
                // 找到后继节点（右树中最小的那个）
                N minNode = findMinNodeInTree(node.getRight());
                // 删除后继节点
                deleteNode(node, minNode.getValue());
                // 设置当前节点的值为后继节点的值
                node.setValue(minNode.getValue());
            }
        }

        TreeHelper.updateHeight(node);

        return node;
    }

    /**
     * 找到一棵树中，value最小的那个节点
     *
     * @param node
     * @return
     */
    private N findMinNodeInTree(N node) {
        if (node.getLeft() != null) {
            return findMinNodeInTree(node.getLeft());
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
    protected N newNode(V value) {
        return (N) new BinarySearchNode<>(value);
    }
}
