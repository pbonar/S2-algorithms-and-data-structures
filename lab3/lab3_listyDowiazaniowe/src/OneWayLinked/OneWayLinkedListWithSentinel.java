package OneWayLinked;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class OneWayLinkedListWithSentinel<E> implements IList<E>{
    Elementum sentinel;
    Elementum head = new Elementum<>(null);

    int size;

    private void checkIndex(int index, boolean normal){
        if (normal && (index >= size || index < 0))
            throw new IllegalArgumentException("index has to be greater or even zero and cant be bigger or even size");
        else if (index > size || index < 0)
            throw new IllegalArgumentException("index has to be greater or even zero and cant be bigger than size");
    }

    public OneWayLinkedListWithSentinel() {
        sentinel = new Elementum(null);
        head.setNext(sentinel);
        size = 0;
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, false);
        Elementum<E> el = sentinel;
        boolean ifAll = true;
        for(int i = 0; i < index; i++){
            if (el.getNext() != null)
                el = el.getNext();
            else ifAll = false;
        }
        size++;
        Elementum<E> temp = el.getNext();
        el.setNext(new Elementum<E>(element, temp));
    }

    @Override
    public void clear() {
        sentinel.setNext(null);
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        if (indexOf(element) != -1)
            return true;
        else return false;
    }

    public Elementum<E> getElementum(int index) {
        checkIndex(index, true);
        Elementum<E> el = sentinel.getNext();
        boolean ifAll = true;
        for(int i = 0; i < index; i++){
            if (el.getNext() != null)
                el = el.getNext();
            else ifAll = false;
        }
        return el;
    }

    @Override
    public E get(int index) {
        return getElementum(index).getValue();
    }

    @Override
    public E set(int index, E element) {
        Elementum<E> el = sentinel;
        checkIndex(index, true);
        boolean ifAll = true;
        for(int i = 0; i < index; i++){
            if (el.getNext() != null)
                el = el.getNext();
            else ifAll = false;
        }
        el.setValue(element);
        return element;
    }

    @Override
    public int indexOf(E element) {
        int i = -1;
        Elementum<E> el = sentinel;
        while (el.getNext() != null){
            i++;
            el = el.getNext();
            if (element != null) {
                if (el.getValue() != null)
                    if (el.getValue().equals(element))
                        return i;
            }
            else {
                if (el.getValue() == null)
                    return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.getNext() != null)
            return false;
        else
            return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, true);
        if (index+2 < size){
            size--;
            getElementum(index-1).setNext(getElementum(index+1));
            return getElementum(index-1).getValue();
        }
        else if (index+1 == size){
            size--;
            getElementum(index-1).setNext(null);
            return getElementum(index-1).getValue();
        }
        else {
            size--;
            return null;
        }
    }

    @Override
    public boolean remove(E element) {
        int i = indexOf(element);
        if (remove(i) != null)
            return true;
        else
            return false;
    }

    public boolean ifEvenSize(){
        if (size%2 == 0) return true;
        else return false;
    }

    public boolean ifOddSize(){
        return !ifEvenSize();
    }

    public boolean ifOneElementList(){
        if (size == 1) return true;
        else return false;
    }

    public void cout(){
        for (int i = 0; i < size; i++)
            System.out.print(get(i)  + " ");
        System.out.println();
    }

    @Override
    public int size() {
        return size;
    }

    public OneWayLinkedListWithSentinel<E> getInverted(){
        Elementum<E> el = sentinel;
        OneWayLinkedListWithSentinel<E> lista = new OneWayLinkedListWithSentinel<>();
        while (el.getNext() != null){
            el = el.getNext();
            lista.add(0, el.getValue());
        }
        return lista;
    }

    public E getFromEnd(int n){
        checkIndex(n, true);
        OneWayLinkedListWithSentinel<E> lista = getInverted();
        return lista.get(n);
    }

    //nic potrzebnego to co skipnac ale potrzebne zeby implementowac
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        IList.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return IList.super.spliterator();
    }

}
