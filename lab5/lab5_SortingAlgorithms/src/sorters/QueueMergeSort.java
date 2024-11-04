package sorters;
import core.SortingAlgorithm;
import testing.MarkedValue;

import java.util.*;

public class QueueMergeSort<T> extends SortingAlgorithm<T> {

    public QueueMergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        if (size <= 1) {
            return list;
        }

        Queue<List<T>> queue = new LinkedList<>();
        for (T element : list) {
            List<T> sublist = new ArrayList<>();
            sublist.add(element);
            queue.add(sublist);
        }

        while (queue.size() > 1) {
            List<T> left = queue.poll();
            List<T> right = queue.poll();
            List<T> merged = merge(left, right);
            queue.add(merged);
        }
        return queue.poll();
    }

    private List<T> merge(List<T> left, List<T> right) {
        List<T> merged = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            if (compare(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i));
                i++;
                swap(merged, 0, 0);
            } else {
                merged.add(right.get(j));
                j++;
                swap(merged, 0, 0);
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
}