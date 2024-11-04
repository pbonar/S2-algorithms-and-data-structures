package sorters;

import java.util.Comparator;
import java.util.List;

import core.SortingAlgorithm;

public class MergeSort<T> extends SortingAlgorithm<T> {
    public MergeSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        if (list.size() <= 1) {
            return list;
        }

        int middle = list.size() / 2;

        List<T> leftList = list.subList(0, middle);
        List<T> rightList = list.subList(middle, list.size());

        leftList = sort(leftList);
        rightList = sort(rightList);

        return merge(leftList, rightList, list);
    }

    private List<T> merge(List<T> leftList, List<T> rightList, List<T> mergedList) {
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (compare(leftList.get(leftIndex), rightList.get(rightIndex)) <= 0) {
                mergedList.set(mergedIndex++, leftList.get(leftIndex++));
                swap(leftList, 0, 0);
            } else {
                mergedList.set(mergedIndex++, rightList.get(rightIndex++));
                swap(leftList, 0, 0);
            }
        }

        while (leftIndex < leftList.size()) {
            mergedList.set(mergedIndex++, leftList.get(leftIndex++));
            swap(leftList, 0, 0);
        }

        while (rightIndex < rightList.size()) {
            mergedList.set(mergedIndex++, rightList.get(rightIndex++));
            swap(leftList, 0, 0);
        }

        return mergedList;
    }
}