package fun.cllc.algorithm.sort;


/**
 * @author chenlei
 * @date 2019-03-27
 */
public class QuickSort {
    /**
     * @param values
     * @param <T>
     */
    public static <T extends Comparable<T>> void sort(T[] values) {
        sort(values, 0, values.length - 1);
    }

    /**
     * sort
     *
     * @param values
     * @return
     */
    private static <T extends Comparable<T>> void sort(T[] values, int start, int end) {
        if (start >= end) {
            return;
        }

        int index = partition(values, start, end);
        sort(values, start, index - 1);
        sort(values, index + 1, end);
    }

    /**
     * @param values
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> int partition(T[] values, int start, int end) {
        T tmp = values[start];
        while (start < end) {
            while (start < end && values[end].compareTo(tmp) >= 0) {
                end--;
            }
            values[start] = values[end];

            while (start < end && values[start].compareTo(tmp) <= 0) {
                start++;
            }
            values[end] = values[start];
        }

        values[start] = tmp;
        return start;
    }
}
