package array2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2SkipIterator<T> implements Iterator<T> {
    private final Array2<T> array;
    private int i;
    private int iNext;
    private int j;
    private int jNext;
    private int maxj;
    int n;

    public Array2SkipIterator(Array2<T> array, int n) {
        this.array = array;
        this.i = -1;
        this.j = 0;
        for (int i : array.getSizes())
            if (i > maxj) maxj = i;
        this.n = n;
    }


    @Override
    public boolean hasNext() {
        iNext = i;
        jNext = j;
        int count = 0;
        while (iNext < array.getLength() && jNext < maxj) {
            iNext++;
            if (iNext >= array.getLength()){
                iNext = 0;
                jNext++;
            }
            if (array.ifExists(iNext, jNext)) count++;
            if (count >= n) return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (i == -1)
            i = 0;
        else {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            i = iNext;
            j = jNext;
        }
        //System.out.println("  i = " + i + ", j = " + j + " element = " + array.get(i, j));
        T element = array.get(i, j);
        return element;
    }
}