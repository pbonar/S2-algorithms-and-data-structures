package array2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2Iterator<T> implements Iterator<T> {
    private final Array2<T> array;
    private int i;
    private int iNext;
    private int j;
    private int jNext;
    private int maxj;

    public Array2Iterator(Array2<T> array) {
        this.array = array;
        this.i = -1;
        this.j = 0;
        for (int i : array.getSizes())
            if (i > maxj) maxj = i;
    }

    @Override
    public boolean hasNext() {
        iNext = i;
        jNext = j;
        while (iNext < array.getLength() && jNext < maxj) {
            iNext++;
            if (iNext >= array.getLength()){
                iNext = 0;
                jNext++;
            }
            if (array.ifExists(iNext, jNext)) return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            //return null;
            throw new NoSuchElementException();
        }
        i = iNext;
        j = jNext;
        T element = array.get(i, j);
        return element;
    }
}