package com.hong.test.structure.chapter4;

import java.nio.BufferUnderflowException;

/**
 * Created by Hongwei on 2015/9/25.
 * 二叉树查询树
 */
public class BinarySearchTree {
    /**
     * 内部类
     * 二叉树节点
     */
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public BinaryNode<T> getLeft() {
            return left;
        }

        public void setLeft(BinaryNode<T> left) {
            this.left = left;
        }

        public BinaryNode<T> getRight() {
            return right;
        }

        public void setRight(BinaryNode<T> right) {
            this.right = right;
        }
    }

    private BinaryNode<Integer> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(Integer x) {
        return contains(x, root);
    }

    public Integer findMin() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMin(root).element;
    }

    public Integer findMax() {
        if (isEmpty()) throw new BufferUnderflowException();
        return findMax(root).element;
    }

    public void insert(Integer x) {
        root = insert(x, root);
    }

    public void remove(Integer x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    private boolean contains(Integer x, BinaryNode<Integer> root) {
        if (root == null) return false;

        int compareResult = x.compareTo(root.element);
        if (compareResult < 0) {
            return contains(x, root.left);
        } else if (compareResult > 0) {
            return contains(x, root.right);
        } else {
            return true;
        }
    }

    private BinaryNode<Integer> findMin(BinaryNode<Integer> root) {
        if (root != null)
            while (root.left != null)
                root = root.left;

        return root;
    }

    private BinaryNode<Integer> findMax(BinaryNode<Integer> root) {
        if (root == null) return null;
        else if (root.right == null) return root;
        return findMax(root.right);
    }

    private BinaryNode<Integer> insert(Integer x, BinaryNode<Integer> root) {
        if (root == null) {
            root = new BinaryNode<>(x, null, null);
        }
        int compareResult = x.compareTo(root.element);
        if (compareResult < 0) {
            root.left = insert(x, root.left);
        } else if (compareResult > 0) {
            root.right = insert(x, root.right);
        }

        return root;
    }

    private BinaryNode<Integer> remove(Integer x, BinaryNode<Integer> root) {
        if (root == null) return null;

        int compareResult = x.compareTo(root.element);
        if (compareResult < 0) {
            root.left = remove(x, root.left);
        } else if (compareResult > 0) {
            root.right = remove(x, root.right);
        } else if (root.left != null && root.right != null) {
            root.element = findMin(root.right).element;
            root.right = remove(root.element, root.right);
        } else {
            root = (root.left != null) ? root.left : root.right;
        }

        return root;
    }
}
