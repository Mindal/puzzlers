package ru.bogdanov.puzzlers.seventh;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;

    private List<Node> children;

    private int weight;

    public Node(int weight) {
        this.weight = weight;
    }


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public int getWeight() {
        return weight;
    }
}
