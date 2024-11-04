package BST;

class Nodee<T extends Comparable<T>> {
    private T data;
    private Nodee<T> left;
    private Nodee<T> right;

    public Nodee(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Nodee<T> getLeft() {
        return left;
    }

    public void setLeft(Nodee<T> left) {
        this.left = left;
    }

    public Nodee<T> getRight() {
        return right;
    }

    public void setRight(Nodee<T> right) {
        this.right = right;
    }

    public boolean search(T key) {
        if (key.compareTo(this.data) < 0) {
            if (left != null)
                return left.search(key);
            else
                return false;
        } else if (key.compareTo(this.data) > 0){
            if (right != null)
                return right.search(key);
            else
                return false;
        } else
            return true;
    }
}