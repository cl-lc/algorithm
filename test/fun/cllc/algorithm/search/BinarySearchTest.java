package fun.cllc.algorithm.search;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author chenlei
 * @date 2019-08-09
 */
public class BinarySearchTest {
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
        input.sort(Integer::compareTo);

        int targetIndex = random.nextInt(INPUT_SIZE);
        int searchIndex = BinarySearch.find(input, input.get(targetIndex));
        assert targetIndex == searchIndex;
    }
}
