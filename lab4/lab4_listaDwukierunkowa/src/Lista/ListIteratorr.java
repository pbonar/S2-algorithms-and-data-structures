package Lista;

import java.util.ListIterator;

public class ListIteratorr<E> implements ListIterator<E> {
    Element<E> tail;
    Element<E> current;
    int curr = 0;
    Size size;

    public ListIteratorr(Element<E> tail, Size size){
        this.tail = tail;
        this.current = tail.getNext();
        this.curr = 0;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        if (curr < size.getSize()-1 && size.getSize() != 0) return true;
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

    @Override
    public boolean hasPrevious() {
        if (curr > 0 && size.getSize() != 0) return true;
        else return false;
    }

    @Override
    public E previous() {
        if (hasPrevious()){
            current = current.getPrev();
            curr--;
            return current.getValue();
        }
        return null;
    }

    @Override
    public int nextIndex() {
        if (hasNext())
            return curr+1;
        else return -1;
    }

    @Override
    public int previousIndex() {
        if (hasPrevious())
            return curr-1;
        else return -1;
    }

    @Override
    public void remove() {
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
        size.setSize(size.getSize()-1);
        System.out.println(curr);
        if (curr == size.getSize()){
            tail.setValue((E) current.getPrev().getValue());
            tail.setPrev(current.getPrev().getPrev());
            tail.setNext(current.getNext());
        }
        current = current.getPrev();
        curr--;
    }

    @Override
    public void set(E e) {
        current.setValue(e);
    }

    @Override
    public void add(E e) {
        Element<E> el = new Element<>(e);
        current.getNext().setPrev(el);
        el.setNext(current.getNext());
        current.setNext(el);
        el.setPrev(current);
        size.setSize(size.getSize()+1);

    }

    public E now(){
        return current.getValue();
    }
}