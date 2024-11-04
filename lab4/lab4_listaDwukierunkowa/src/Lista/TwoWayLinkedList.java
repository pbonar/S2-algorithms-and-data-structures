package Lista;

import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedList<E> implements IList<E>{
    Element<E> tail = null;
    Size size = new Size(0);

    void checkIndex(int index){
        if(index < 0 || index >= size.getSize())
            throw new IllegalArgumentException("wrong index");
    }

    public void addBetween(Element<E> prev,E element, Element<E> next){
        Element<E> n = new Element<E>(element);
        prev.setNext(n);
        n.setPrev(prev);
        n.setNext(next);
        next.setPrev(n);
        size.setSize(size.getSize()+1);
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()){
            tail = new Element<E>(e);
            tail.setPrev(tail);
            tail.setNext(tail);
            size.setSize(size.getSize()+1);
        }
        else{
            addBetween(tail, e, tail.getNext());
            tail = tail.getNext();
        }
        return true;
    }

    @Override
    public void add(int index, E element) {
        if(index < 0 || index > size.getSize()){
            System.out.println(index + " " + size.getSize());
            throw new IllegalArgumentException("wrong index");
        }
        if (isEmpty() || index >= size.getSize()){
            add(element);
        }
        else {
            Element<E> prev;
            if (index > 0){
                prev = getElement(index-1);
            }
            else {
                prev = tail;
            }
            Element<E> next = prev.getNext();
            addBetween(prev, element, next);
        }
    }

    @Override
    public void clear() {
        tail = null;
        size.setSize(0);
    }

    @Override
    public boolean contains(E element) {
        int o = indexOf(element);
        if (o == -1) return false;
        else return true;
    }

    public Element<E> getElement(int index){
        checkIndex(index);
        Element<E> e = tail;
        if ((size.getSize()-1)/2 > index){
            e = tail.getNext();
            for (int i = 0; i < index; i++)
                e = e.getNext();
        }
        else {
            for (int i = size.getSize()-1; i > index; i--)
                e = e.getPrev();
        }
        return e;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getElement(index).getValue();
    }

    @Override
    public int indexOf(E element) {
        Element<E> e = tail;
        for(int i = size.getSize() - 1; i >= 0; i--){
            if (e.getValue().equals(element))
                return i;
            e = e.getPrev();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (tail != null)
            return false;
        else
            return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Element<E> e = getElement(index);
        e.getPrev().setNext(e.getNext());
        e.getNext().setPrev(e.getPrev());
        size.setSize(size.getSize()-1);
        if (index == size.getSize()){
            if (size.getSize() == 0)
                clear();
            else
                tail = e.getPrev();
        }
        return e.getValue();
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        getElement(index).setValue(element);
        return element;
    }

    @Override
    public int size() {
        return size.getSize();
    }

    @Override
    public boolean remove(E element) {
        int i = indexOf(element);
        if (i == -1){
            return false;
        }
        else if (size.getSize() == 1 && i == 0){
            clear();
            return true;
        }
        else {
            remove(i);
            return true;
        }
    }

    //====================TO DO=====================

    @Override
    public Iterator<E> iterator() {

        return new Iteratorr<E>(tail, size.getSize());
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorr<E>(tail, size);
    }

    public void cout(){
        System.out.println("LISTA: ");
        if (size.getSize() == 0) return;
        for (int i = 0; i < size.getSize(); i++){
            System.out.println("  "+ i +": " + get(i));
        }
    }
}