package array2;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Array2<T> implements Iterable<T> {
    private T[][] array;

    public Array2(int[] dimensions) {
        if (dimensions.length < 2) {
            throw new IllegalArgumentException("Array2 has to have at least two dimensions");
        }
        for (int i : dimensions) {
            if (i < 0) {
                throw new IllegalArgumentException("Array2 dimensions cant be negative");
            }
        }
        array = (T[][]) new Object[dimensions.length][];
        for (int i = 0; i < dimensions.length; i++) {
            array[i] = (T[]) new Object[dimensions[i]];
            for (int j = 0; j < dimensions[i]; j++) {
                array[i][j] = null;
            }
        }
    }

    public Array2(T[][] array) {
        this.array = array;
    }

    public T get(int i, int j) {
        if (i < 0 || i >= array.length || j < 0 || j >= array[i].length) {
            throw new IndexOutOfBoundsException();
        }
        return array[i][j];
    }

    public void set(T newElem, int i, int j) {
        if (i < 0 || i >= array.length || j < 0 || j >= array[i].length) {
            throw new IndexOutOfBoundsException();
        }
        array[i][j] = newElem;
    }

    public boolean ifExists(int i, int j){
        if (i < array.length && j < array[i].length)
            return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Array2Iterator<>(new Array2<T>(array));
    }

    public int[] getSizes() {
        int[] sizes = new int[array.length];
        sizes[0] = array[0].length;
        for (int i = 1; i < array.length; i++) {
            sizes[i] = array[i].length;
        }
        return sizes;
    }

    public int getLength(){
        return array.length;
    }
}