package BST;

public class BSTree<T extends Comparable<T>> {
    private Nodee<T> root;


    // Metoda rekurencyjna: Wyszukiwanie elementu w drzewie BST
    public boolean search(T key) {
        return root.search(key);
        //return searchRecursive(root, key);
    }

    private boolean searchRecursive(Nodee<T> current, T key) {
        if (current == null)
            return false;

        int cmp = key.compareTo(current.getData());
        if (cmp < 0)
            return searchRecursive(current.getLeft(), key);
        else if (cmp > 0)
            return searchRecursive(current.getRight(), key);
        else
            return true;
    }

    // Metoda rekurencyjna: Znajdowanie minimum w drzewie BST
    public T findMin() {
        if (root == null)
            return null;

        Nodee<T> minNode = findMinRecursive(root);
        return minNode.getData();
    }

    private Nodee<T> findMinRecursive(Nodee<T> current) {
        if (current.getLeft() == null)
            return current;
        else
            return findMinRecursive(current.getLeft());
    }

    // Metoda rekurencyjna: Znajdowanie maksimum w drzewie BST
    public T findMax() {
        if (root == null)
            return null;

        Nodee<T> maxNode = findMaxRecursive(root);
        return maxNode.getData();
    }
    private Nodee<T> findMaxRecursive(Nodee<T> current) {
        if (current.getRight() == null)
            return current;
        else
            return findMaxRecursive(current.getRight());
    }

    // Metoda rekurencyjna: Przejście po drzewie w porządku post-order
    public void postOrderTraversal() {
        Executor<T> exec = new Executor<>();
        postOrderTraversalRecursive(root, exec);
        System.out.println(exec.getResult());
    }
    private void postOrderTraversalRecursive(Nodee<T> current, Executor<T> exec) {
        if (current != null) {
            postOrderTraversalRecursive(current.getLeft(), exec);
            postOrderTraversalRecursive(current.getRight(), exec);
            exec.execute(current.getData());
        }
    }

    // Metoda iteracyjna: Znalezienie następnika (in-order successor)
    public T findSuccessor(T key) {
        Nodee<T> current = root;
        Nodee<T> successor = null;

        while (current != null) {
            if (key.compareTo(current.getData()) < 0) {
                successor = current;
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (successor != null)
            return successor.getData();
        else
            return null;
    }

    // Metoda iteracyjna: Wstawianie elementu do drzewa BST
    public void insert(T key) {
        Nodee<T> prev = null;
        Nodee<T> current = root;
        boolean leftC = false;

        while (current != null) {
            if (key.compareTo(current.getData()) < 0) {
                prev = current;
                current = current.getLeft();
                leftC = true;
            } else {
                prev = current;
                current = current.getRight();
                leftC = false;
            }
        }

        if (prev != null) {
            if (leftC)
                prev.setLeft(new Nodee<>(key));
            else
                prev.setRight(new Nodee<>(key));
        } else {
            root = new Nodee<>(key);
        }
    }

    // Metoda iteracyjna: Usunięcie elementu z drzewa BST
    public void delete(T key) {
        Nodee<T> current = root;
        Nodee<T> prev = null;
        boolean leftC = false;
        if (root.getData().compareTo(key) == 0){
            Nodee<T> toPaste = current.getLeft();
            root = current.getRight();
            current = root;
            while (current.getLeft() != null)
                current = current.getLeft();
            current.setLeft(toPaste);
        } else {
            while (current != null) {
                if (key.compareTo(current.getData()) < 0) {
                    prev = current;
                    current = current.getLeft();
                    leftC = true;
                } else if (key.compareTo(current.getData()) > 0){
                    prev = current;
                    current = current.getRight();
                    leftC = false;
                } else {
                    break;
                }
            }
            if (current != null){
                if (current.getLeft() == null) {
                    prev.setRight(current.getRight());
                } else if (current.getRight() == null) {
                    prev.setLeft(current.getLeft());
                } else {
                    if (leftC) {
                        prev.setLeft(current.getRight());
                    } else {
                        prev.setRight(current.getRight());
                    }
                    Nodee<T> toPaste = current.getLeft();
                    current = current.getRight();
                    while (current.getLeft() != null)
                        current = current.getLeft();
                    current.setLeft(toPaste);
                }
            }
        }
    }
}