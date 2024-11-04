package sorters;

import core.SortingAlgorithm;

import java.util.*;

public class QuickSort<T> extends SortingAlgorithm<T> {
    private boolean useRandomPivot;

    public QuickSort(Comparator<? super T> comparator, boolean useRandomPivot) {
        super(comparator);
        this.useRandomPivot = useRandomPivot;
    }

    @Override
    public List<T> sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
        return list;
    }

    private void quickSort(List<T> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private int partition(List<T> list, int low, int high) {
        int pivotIndex;
        if (useRandomPivot) {
            pivotIndex = new Random().nextInt(high - low + 1) + low;
//
//            ArrayList<Integer> lista = new ArrayList<Integer>();
//            for (int i = 0; i < 3; i++){
//                lista.add(new Random().nextInt(high - low + 1) + low);
//            }
//            Collections.sort(lista);
//            pivotIndex = lista.get(lista.size()/2);
        } else {
            pivotIndex = low;
        }
        T pivotValue = list.get(pivotIndex);
        swap(list, pivotIndex, high);
        int storeIndex = low;
        ListIterator<T> curr = list.listIterator(low);
        ListIterator<T> toswap = list.listIterator(low);
        for (int i = low; i < high; i++) {
            T p;
            if (curr.hasNext()) {
                p = curr.next();
                p = curr.previous();
            }
            else {
                p = curr.previous();
                p = curr.next();
            }
            if (compare(p, pivotValue) < 0) {
//                swap(list, i, storeIndex);
                swap(list, p, curr, toswap);
                storeIndex++;
            }
            if (curr.hasNext()) curr.next();
        }
        swap(list, storeIndex, high);
        return storeIndex;
    }

    void swap(List<T> list, T p, ListIterator<T> curr, ListIterator<T> toswap){
        T n = toswap.next();
        curr.set(n);
        toswap.set(p);
    }
}