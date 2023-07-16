package com.company.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OrderedTree {
    private Node root;

    public void add(String value) {
        root = addRecursive(root, value);
    }

    public List<String> traverseInOrder() {
        List<String> nodes = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            Node top = stack.pop();
            nodes.add(top.value);
            current = top.right;
        }
        return nodes;
    }

    private Node addRecursive(Node current, String value) {
        if (current == null) {
            return new Node(value);
        }
        if (value.compareTo(current.value) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }
        return current;
    }
}
