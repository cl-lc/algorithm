package net.cllc.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlei
 * @date 2019-03-27
 */
public class MergeSort {
    /**
     * sort
     *
     * @param values
     * @return
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> values) {
        int size = values.size();
        if (size <= 1) {
            return values;
        }

        List<T> left = values.subList(0, size / 2);
        left = sort(left);

        List<T> right = values.subList(size / 2, size);
        right = sort(right);

        return mergeToSortedArray(left, right);
    }

    /**
     * 合并两个已经排好序的数组
     *
     * @param left
     * @param right
     * @return
     */
    private static <T extends Comparable<T>> List<T> mergeToSortedArray(List<T> left, List<T> right) {
        int leftSize = left.size();
        int rightSize = right.size();
        int leftIndex = 0, rightIndex = 0;

        List<T> ret = new ArrayList<>();
        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0) {
                ret.add(left.get(leftIndex));
                leftIndex++;
            } else {
                ret.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        if (leftIndex < leftSize) {
            ret.addAll(left.subList(leftIndex, leftSize));
        } else if (rightIndex < rightSize) {
            ret.addAll(right.subList(rightIndex, rightSize));
        }

        return ret;
    }
}
