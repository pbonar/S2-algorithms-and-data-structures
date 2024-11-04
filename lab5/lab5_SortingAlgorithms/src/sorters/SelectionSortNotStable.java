package sorters;

import core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SelectionSortNotStable<T> extends SortingAlgorithm<T> {

    public SelectionSortNotStable(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int n = list.size();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            // find the minimum and maximum elements in the unsorted portion of the list
            int min = i, max = j;
            for (int k = i; k <= j; k++) {
                if (compare(list.get(k), list.get(min)) < 0) {
                    min = k;
                }
                if (compare(list.get(k), list.get(max)) >= 0) {
                    max = k;
                }
            }
            // swap the minimum element with the beginning of the unsorted portion
            if (min != i) {
                swap(list, i, min);
            }
            // if the maximum element was swapped with the beginning, update max index
            if (max == i) {
                max = min;
            }
            // swap the maximum element with the end of the unsorted portion
            if (max != j) {
                swap(list, j, max);
            }
        }
        return list;
    }
}


