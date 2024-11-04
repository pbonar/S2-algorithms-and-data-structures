import java.util.NoSuchElementException;

public class Array3Heap<T extends Comparable<T>> {

    public T[] heap;
    private int size;

    public Array3Heap(int capacity) {
        heap = (T[]) new Comparable[capacity];
        size = 0;
    }

    public void clear() {
        size = 0;
    }

    public void add(T element) {
        if (size >= heap.length) {
            T[] newHeap = (T[]) new Comparable[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
        heap[size] = element;
        shiftUp(size);
        size++;
    }

    public T minimum() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        int minInd = 0;
        for (int i = size/3; i < size; i++){
            if (heap[minInd].compareTo(heap[i]) >= 0) minInd = i;
        }
        T min = heap[minInd];
        heap[minInd] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        if (minInd != size) shiftUp(minInd);
        return min;
    }

    public T maximum() {
        T max = heap[0];
        swap(0, size-1);
        size--;
        shiftDown(0);

        return max;
    }

    public void cout(){
        System.out.println("kopiec:");
        int y = 1;
        int j = 0;
        for (int i = 0; i < size; i++){
            if (j == y) {
                System.out.println();
                y = 3*y;
                j = 0;
            }
            j++;
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private void shiftUp(int index) {
        int parentIndex = (index - 1) / 3;
        while (index >= 0 && heap[index].compareTo(heap[parentIndex]) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 3;
        }
    }
    private void shiftDown(int index) {
        int maxIndex = index;
        int leftChildIndex = 3 * index + 1;
        int rightChildIndex = 3 * index + 3;
        for (int i = leftChildIndex; i <= rightChildIndex && i < size; i++) {
            if (heap[i].compareTo(heap[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        if (maxIndex != index) {
            swap(index, maxIndex);
            shiftDown(maxIndex);
        }
    }
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void addHeap(Array3Heap<T> kopiec) {
        for (T t : kopiec.heap)
            add(t);
    }
}
