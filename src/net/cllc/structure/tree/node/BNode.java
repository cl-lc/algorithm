package net.cllc.structure.tree.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlei
 * @date 2019-03-25
 */
public class BNode<V extends Comparable<V>> {
    private int keyCount;
    private List<V> keys;
    private List<BNode<V>> nodes;
    private BNode<V> parent;
    private boolean leaf;

    public BNode() {
        this.keyCount = 0;
        this.keys = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.leaf = true;
    }

    public BNode(BNode<V> parent) {
        this();
        this.parent = parent;
    }

    public BNode(BNode<V> parent, V key) {
        this(parent);
        this.keys.add(key);
        this.keyCount += 1;
    }

    /**
     * 找到某个值对应的子树
     *
     * @param key
     * @return
     */
    public BNode<V> findChildNode(V key) {
        if (isLeaf()) {
            return null;
        }

        for (int i = 0; i < keyCount; i++) {
            if (keys.get(i).compareTo(key) >= 0) {
                return nodes.get(i);
            }
        }
        return nodes.get(keyCount);
    }

    /**
     * 某个关键字是否在节点中
     *
     * @param key
     * @return
     */
    public boolean keyInNode(V key) {
        for (int i = 0; i < keyCount; i++) {
            if (keys.get(i).equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 叶子节点增加一个key
     *
     * @param key
     */
    public void addKeyToLeaf(V key) {
        if (!isLeaf()) {
            return;
        }

        for (int i = 0; i < keyCount; i++) {
            if (keys.get(i).compareTo(key) >= 0) {
                keys.add(i, key);
                keyCount += 1;
                return;
            }
        }

        keys.add(key);
        keyCount += 1;
    }

    /**
     * 普通节点增加一个key，以及对应的子节点
     * 增加一个key，即是要删除key那个位置原来对应的子节点
     *
     * @param key
     * @param leftChildNode
     * @param rightChildNode
     */
    public void addKeyToNode(V key, BNode<V> leftChildNode, BNode<V> rightChildNode) {
        if (isLeaf()) {
            return;
        }

        for (int i = 0; i < keyCount; i++) {
            if (keys.get(i).compareTo(key) >= 0) {
                keys.add(i, key);
                nodes.remove(i);
                nodes.add(i, rightChildNode);
                nodes.add(i, leftChildNode);
                keyCount += 1;
                return;
            }
        }

        keys.add(key);
        if (keyCount > 0) {
            nodes.remove(keyCount);
            nodes.add(keyCount, rightChildNode);
            nodes.add(keyCount, leftChildNode);
        } else {
            nodes.add(leftChildNode);
            nodes.add(rightChildNode);
        }
        keyCount += 1;

        leftChildNode.setParent(this);
        rightChildNode.setParent(this);
    }

    /**
     * copy一个子节点
     *
     * @param start
     * @param end
     * @return
     */
    public BNode<V> subNode(int start, int end) {
        BNode<V> node = new BNode<>();
        node.setKeys(new ArrayList<>(keys.subList(start, end)));
        if (!isLeaf()) {
            node.setNodes(new ArrayList<>(nodes.subList(start, end + 1)));
        }
        node.setLeaf(isLeaf());
        return node;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }

    public V getKey(int index) {
        if (index < 0 || index >= keyCount) {
            return null;
        }

        return this.keys.get(index);
    }

    public List<V> getKeys() {
        return keys;
    }

    public void setKeys(List<V> keys) {
        this.keys = keys;
        this.keyCount = keys.size();
    }

    public List<BNode<V>> getNodes() {
        return nodes;
    }

    public void setNodes(List<BNode<V>> nodes) {
        this.nodes = nodes;
    }

    public BNode<V> getParent() {
        return parent;
    }

    public void setParent(BNode<V> parent) {
        this.parent = parent;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
