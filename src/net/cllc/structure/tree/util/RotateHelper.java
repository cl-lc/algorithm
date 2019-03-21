package net.cllc.structure.tree.util;

import net.cllc.structure.tree.node.BaseBinaryNode;

/**
 * @author chenlei
 * @date 2019-03-19
 */
public class RotateHelper {

    /**
     * 右旋
     *
     * @param node
     * @return 返回新的根节点
     */
    public static <V extends Comparable<V>, N extends BaseBinaryNode<V, N>> N rightRotate(N node) {
        N parent = node.getParent();
        N a = node.getLeft();
        N b = a.getRight();

        TreeHelper.updateChild(parent, a);
        a.setRight(node);
        a.setParent(parent);
        b.setParent(node);

        node.setLeft(b);
        node.setParent(a);

        return a;
    }

    /**
     * 左旋
     *
     * @param node
     * @return 返回新的根节点
     */
    public static <V extends Comparable<V>, N extends BaseBinaryNode<V, N>> N leftRotate(N node) {
        N parent = node.getParent();
        N a = node.getRight();
        N b = a.getLeft();

        TreeHelper.updateChild(parent, a);
        a.setLeft(node);
        a.setParent(parent);
        b.setParent(node);

        node.setRight(b);
        node.setParent(a);

        return a;
    }
}
