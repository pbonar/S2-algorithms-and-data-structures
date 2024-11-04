package implementacions;

import core.IDictionary;

import java.util.Comparator;

public class BSTDictionary<K extends Comparable<K>, V> implements IDictionary<K, V> {
    public BSTNode<K, V> root;
    public static Comparator comparator;

    public BSTDictionary(Comparator comparator){
        this.comparator = comparator;
    }

    @Override
    public V insert(K key, V value) {
        BSTNode<K, V> prev = null;
        BSTNode<K, V> current = root;
        boolean leftC = false;

        while (current != null) {
            Pairr p = new Pairr();
            p.key = key;
            p.value = null;
            if (p.compareTo(current.getData(), comparator) < 0) {
                prev = current;
                current = current.getLeft();
                leftC = true;
            } else if (p.compareTo(current.getData(), comparator) > 0){
                prev = current;
                current = current.getRight();
                leftC = false;
            } else
                return null;
        }

        if (prev != null) {
            if (leftC){
                prev.setLeft(new BSTNode<>(key, value));
                return value;
            }
            else {
                prev.setRight(new BSTNode<>(key, value));
                return value;
            }
        } else {
            root = new BSTNode<>(key, value);
            return value;
        }
    }

    @Override
    public V get(K key) {
        return root.searchValue(key);
    }

    @Override
    public V remove(K key) {
        BSTNode<K, V> n = deleteRecursive(root, key);
        if (n != null)
            return n.getValue();
        else
            return null;
    }

    private BSTNode<K, V> deleteRecursive(BSTNode<K, V> current, K key) {
        if (current == null)
            return null;
        Pairr p = new Pairr();
        p.key = key;
        p.value = null;
        int cmp = p.compareTo(current.pairr.key, comparator);
        if (cmp < 0) {
            current.left = deleteRecursive(current.left, key);
        } else if (cmp > 0) {
            current.right = deleteRecursive(current.right, key);
        } else {
            // Znaleziono węzeł do usunięcia
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Węzeł ma dwoje dzieci
            BSTNode<K, V> successor = findMinRecursive(current.right);
            current.setData(successor.getData());
            current.right = deleteRecursive(current.right, successor.getData());
        }
        return current;
    }

    private BSTNode<K, V> findMinRecursive(BSTNode<K, V> current) {
        if (current.getLeft() == null)
            return current;
        else
            return findMinRecursive(current.getLeft());
    }

    public void cout(){
        Executor e = new Executor<K>();
        inOrderTraversalRecursive(root, e);
        System.out.println(e.getResult());
    }

    public void inOrderTraversalRecursive(BSTNode<K, V> current, Executor<K> exec) {
        if (current != null) {
            inOrderTraversalRecursive(current.getLeft(), exec);
            exec.execute(current.getData());
            inOrderTraversalRecursive(current.getRight(), exec);
        }
    }
    public static class Executor<T>{
        String toReturn = "";

        public void execute(Object elem) {
            toReturn += (elem);
            toReturn += " ";
        }

        public String getResult() {
            return toReturn;
        }
    }



    //==============================================================================

    public static class BSTNode<T extends Comparable<T>, V> {
        public Pairr<T, V> pairr = new Pairr<>();

        public BSTNode<T, V> left;
        public BSTNode<T, V> right;

        public BSTNode(T data, V value) {
            this.pairr.value = value;
            this.pairr.key = data;
            this.left = null;
            this.right = null;
        }

        public V getValue() {
            return pairr.value;
        }

        public T getData() {
            return pairr.key;
        }

        public void setData(T data) {
            this.pairr.key = data;
        }

        public BSTNode<T, V> getLeft() {
            return left;
        }

        public void setLeft(BSTNode<T, V> left) {
            this.left = left;
        }

        public BSTNode<T, V> getRight() {
            return right;
        }

        public void setRight(BSTNode<T, V> right) {
            this.right = right;
        }

        public boolean search(T key) {
            Pairr p = new Pairr();
            p.key = key;
            p.value = null;
            if (p.compareTo(this.pairr.key, comparator) < 0) {
                if (left != null)
                    return left.search(key);
                else
                    return false;
            } else if (p.compareTo(this.pairr.key, comparator) > 0){
                if (right != null)
                    return right.search(key);
                else
                    return false;
            } else
                return true;
        }

        public V searchValue(T key) {
            Pairr p = new Pairr();
            p.key = key;
            p.value = null;
            if (p.compareTo(this.pairr.key, comparator) < 0) {
                if (left != null)
                    return left.searchValue(key);
                else
                    return null;
            } else if (p.compareTo(this.pairr.key, comparator) > 0){
                if (right != null)
                    return right.searchValue(key);
                else
                    return null;
            } else
                return pairr.value;
        }
    }
}
