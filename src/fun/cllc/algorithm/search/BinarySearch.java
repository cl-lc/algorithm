package fun.cllc.algorithm.search;

import java.util.List;

/**
 * @author chenlei
 * @date 2019-08-09
 */
public class BinarySearch {
    /**
     * 二分查找
     *
     * @param values
     * @param target
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int find(List<T> values, T target) {
        return find(values, target, 0, values.size() - 1);
    }

    /**
     * 递归查找
     *
     * @param values
     * @param target
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> int find(List<T> values, T target, int left, int right) {
        if (left == right - 1) {
            return -1;
        }

        int index = (left + right) / 2;
        T value = values.get(index);
        int compare = value.compareTo(target);
        if (compare < 0) {
            return find(values, target, index, right);
        } else if (compare > 0) {
            return find(values, target, left, index);
        } else {
            return index;
        }
    }
}
