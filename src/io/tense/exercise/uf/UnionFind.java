package io.tense.exercise.uf;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class UnionFind<T> {

    private final class UFNode<T> {
        public final T item;
        public UFNode<T> parent;
        public int rank;

        UFNode(T item) {
            this.item = item;
            this.rank = 0;
            this.parent = this;
        }
    }

    private final Map<T, UFNode<T>> nodeContainerMap = new HashMap<>();

    private UnionFind() {}

    //make singleton sets
    public UnionFind(Iterable<T> items) {
        for(T t : items) {
            nodeContainerMap.put(t, new UFNode<T>(t));
        }
    }

    //locate the representative for an element's set
    private Optional<T> find(T item) {
        if(nodeContainerMap.containsKey(item) == false) {
            return Optional.empty();
        }
        UFNode<T> node = findNode(nodeContainerMap.get(item));
        return Optional.of(node.item);
    }

    private UFNode<T> findNode(UFNode<T> node) {
        if(node.parent != node) {
            node.parent = findNode(node.parent);
        }
        return node.parent;
    }

    // union the sets that x and y are members of
    public void union(T x, T y) {
        UFNode<T> xRoot = findNode(nodeContainerMap.get(x));
        UFNode<T> yRoot = findNode(nodeContainerMap.get(y));

        if (xRoot == yRoot) {
            return;
        }

        if(xRoot.rank < yRoot.rank) {
            xRoot.parent = yRoot;
        } else if (xRoot.rank > yRoot.rank) {
            yRoot.parent = xRoot;
        } else {
            yRoot.parent = xRoot;
            xRoot.rank += 1;
        }
    }

    public boolean connected(T x, T y) {
        return find(x).get() == find(y).get();
    }
}
