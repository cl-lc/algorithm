package net.cllc.algorithm.sort;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chenlei
 * @date 2019-03-27
 */
public class MergeSortTest {
    private static final int INPUT_SIZE = 1000;

    public static void main(String[] args) {
        randomTest();
        randomTest();
        randomTest();
        randomTest();
    }

    private static void randomTest() {
        List<Integer> input = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < INPUT_SIZE; i++) {
            input.add(random.nextInt());
        }

        List<Integer> output = MergeSort.sort(input);
        for (int i = 0; i < INPUT_SIZE - 1; i++) {
            assert output.get(i) <= output.get(i + 1);
        }
    }
}
