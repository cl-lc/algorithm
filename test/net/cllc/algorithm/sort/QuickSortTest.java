package net.cllc.algorithm.sort;


import java.util.Random;

/**
 * @author chenlei
 * @date 2019-03-27
 */
public class QuickSortTest {
    private static final int INPUT_SIZE = 1000;

    public static void main(String[] args) {
        randomTest();
        randomTest();
        randomTest();
        randomTest();
    }

    private static void randomTest() {
        Integer[] input = new Integer[INPUT_SIZE];
        Random random = new Random();
        for (int i = 0; i < INPUT_SIZE; i++) {
            input[i] = random.nextInt();
        }

        QuickSort.sort(input);
        for (int i = 0; i < INPUT_SIZE - 1; i++) {
            assert input[i] <= input[i + 1];
        }
    }
}
