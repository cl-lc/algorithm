package net.cllc.structure.tree;

import net.cllc.structure.tree.base.BinaryTree;
import net.cllc.structure.tree.base.Node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySortTree<T extends Comparable<T>> extends BinaryTree<T> {
    /**
     * 插入节点
     *
     * @param value
     * @return
     */
    public void insertNode(T value) {
        Node<T> node = new Node<>(value);

        if (root == null) {
            root = node;
            return;
        }

        Node<T> parent;
        Node<T> index = root;
        while (index != null) {
            parent = index;
            int compare = index.getValue().compareTo(value);
            if (compare > 0) {
                index = index.getLeft();
            } else {
                index = index.getRight();
            }

            if (index == null) {
                node.setParent(parent);
                if (compare > 0) {
                    parent.setLeft(node);
                } else {
                    parent.setRight(node);
                }
            }
        }
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node<T> searchNode(T value) {
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
    private Node<T> searchNode(Node<T> node, T value) {
        int compare = node.getValue().compareTo(value);
        if (compare == 0) {
            return node;
        } else if (compare > 0) {
            // 节点大于要找的值，则从左树中继续查找
            return searchNode(node.getLeft(), value);
        } else {
            // 节点小于等于要查找的值，则从左树中继续查找
            return searchNode(node.getRight(), value);
        }
    }

    /**
     * 删除节点
     *
     * @param value
     * @return
     */
    public Node<T> deleteNode(T value) {
        Node<T> delete = searchNode(value);
        if (delete == null) {
            return null;
        }
        if (root == delete) {
            root = null;
            return delete;
        }

        if (delete.getLeft() != null && delete.getRight() != null) {
            return deleteNodeWithFullChild(delete);
        } else {
            return deleteNodeWithoutFullChild(delete);
        }
    }

    /**
     * 删除只有一个子节点，或者没有子节点的节点
     *
     * @param delete
     * @return
     */
    private Node<T> deleteNodeWithoutFullChild(Node<T> delete) {
        Node<T> parent = delete.getParent();
        Node<T> newChild = delete.getLeft() == null ? delete.getRight() : delete.getLeft();
        if (parent.getLeft() == delete) {
            parent.setLeft(newChild);
        } else {
            parent.setRight(newChild);
        }

        return delete;
    }

    /**
     * 删除有两个子节点的节点
     *
     * @param delete
     * @return
     */
    private Node<T> deleteNodeWithFullChild(Node<T> delete) {
        // 找到后继节点
        Node<T> newChild = findMinNodeInTree(delete.getRight());
        // 因为后继节点肯定没有左子节点，所以直接调用删除函数删除后继节点
        deleteNodeWithoutFullChild(newChild);

        Node<T> parent = delete.getParent();
        // 更新新节点的属性
        newChild.setLeft(delete.getLeft());
        newChild.setRight(delete.getRight());
        newChild.setParent(parent);
        // 更新父节点的子节点为后继节点
        if (parent.getLeft() == delete) {
            parent.setLeft(newChild);
        } else {
            parent.setRight(newChild);
        }

        return null;
    }

    /**
     * 找到一棵树中，value最小的那个节点
     *
     * @param node
     * @return
     */
    private Node<T> findMinNodeInTree(Node<T> node) {
        if (node.getLeft() != null) {
            return findMinNodeInTree(node.getLeft());
        }

        return node;
    }
}
