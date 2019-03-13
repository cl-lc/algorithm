package net.cllc.structure.tree;

import net.cllc.structure.tree.base.Node;

/**
 * @author chenlei
 * @date 2019-03-13
 */
public class BinarySortTreeUtil {

    /**
     * 插入节点
     *
     * 因为Node数据结构中有个parent，所以这里用while循环不用递归
     * 用递归的话需要函数多一个parent参数
     *
     * @param node
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> Node<T> insertNode(Node<T> node, T value) {
        Node<T> newNode = new Node<>();
        newNode.setValue(value);

        if (node == null) {
            return newNode;
        }

        Node<T> parent;
        while (node != null) {
            int compare = node.getValue().compareTo(value);
            parent = node;
            if (compare > 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node == null) {
                newNode.setParent(parent);
                if (compare > 0) {
                    parent.setLeft(newNode);
                } else {
                    parent.setRight(newNode);
                }
            }
        }

        return newNode;
    }

    /**
     * 查找节点
     *
     * @param node
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> Node<T> searchNode(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

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
     * @param node
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> Node<T> deleteNode(Node<T> node, T value) {
        Node<T> delete = searchNode(node, value);
        if (delete == null) {
            return null;
        }
        if (node == delete) {
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
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> Node<T> deleteNodeWithoutFullChild(Node<T> delete) {
        Node<T> parent = delete.getParent();
        if (parent == null) {
            // root节点
            return delete;
        }

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
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> Node<T> deleteNodeWithFullChild(Node<T> delete) {
        Node<T> parent = delete.getParent();
        if (parent == null) {
            // root节点
            return delete;
        }

        // 找到后继节点
        Node<T> newChild = findMinNodeInTree(delete.getRight());
        // 因为后继节点肯定没有左子节点，所以直接调用删除函数删除后继节点
        deleteNodeWithoutFullChild(newChild);

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
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> Node<T> findMinNodeInTree(Node<T> node) {
        if (node.getLeft() != null) {
            return findMinNodeInTree(node.getLeft());
        }

        return node;
    }
}
