package Lista;

public class Iteratorr<E> implements java.util.Iterator<E> {
    Element<E> current;
    int curr = -1;
    int size = 0;

    public Iteratorr(Element<E> tail, int size) {
        current = tail;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        if (curr < size-1 && size != 0) return true;
        else return false;
    }

    @Override
    public E next() {
        if (hasNext()){
            curr++;
            current = current.getNext();
            return current.getValue();
        }
        else return null;
    }
}
