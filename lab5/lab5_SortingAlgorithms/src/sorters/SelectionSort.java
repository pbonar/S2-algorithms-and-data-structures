package sorters;

import core.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectionSort<T> extends SortingAlgorithm<T> {

    public SelectionSort(Comparator<? super T> comparator) {
        super(comparator);
    }
    @Override
    public List<T> sort(List<T> list) {
        int n = list.size();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int min = i, max = j;
            for (int k = i; k <= j; k++) {
                if (compare(list.get(k), list.get(min)) < 0) {
                    min = k;
                }
                if (compare(list.get(k), list.get(max)) >= 0) {
                    max = k;
                }
            }
            if (min != i) {
                for (int k = min; k > i; k--)
                    swap(list, k, k-1);
            }
            if (max < min) {
                max++;
            }
            if (max != j) {
                for (int k = max; k < j; k++)
                    swap(list, k, k+1);
            }
        }
        return list;
    }


}
