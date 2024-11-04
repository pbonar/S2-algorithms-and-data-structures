package sorters;

import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class InsertionSort<T> extends SortingAlgorithm<T> {

    public InsertionSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int insertionIndex = binarySearch(list, 0, i - 1, key);
            for (int j = i - 1; j >= insertionIndex; j--) {
                swap(list, j, j+1);
            }
        }
        return list;
    }

    private int binarySearch(List<T> list, int low, int high, T key) {
        int index = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (compare(list.get(mid), key) <= 0) {
                index = mid + 1;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return index;
    }
}