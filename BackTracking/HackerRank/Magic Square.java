import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'formingMagicSquare' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY s as parameter.
     */

    static int min = Integer.MAX_VALUE;

    public static int formingMagicSquare(List<List<Integer>> s) {

        backtrack(new ArrayList<>(), s);

        return min;
    }

    static void backtrack(List<Integer> cur, List<List<Integer>> s) {

        if (cur.size() == 9) {

            List<List<Integer>> result = convertTo2d(cur);


            if (check(result)) {
                int temp = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp += Math.abs(result.get(i).get(j) - s.get(i).get(j));
                    }
                }

                if (temp < min) min = temp;
            }


            return;
        }


        for (int i = 1; i < 10; i++) {
            if (cur.contains(i)) continue;
            cur.add(i);
            backtrack(cur, s);
            cur.remove(cur.size() - 1);
        }

    }

    static List<List<Integer>> convertTo2d(List<Integer> cur) {

        int idx = -1;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                idx++;
                result.add(new ArrayList<>());
            }

            result.get(idx).add(cur.get(i));
        }

        return result;
    }

    static boolean check(List<List<Integer>> result) {

        List<Integer> sums = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            List<Integer> current = result.get(i);

            int sum = 0;
            for (int j = 0; j < current.size(); j++) {
                sum += current.get(j);
            }

            sums.add(sum);
        }

        int target = sums.get(0);

        for (int i = 1; i < sums.size(); i++) {
            if (target != sums.get(i)) return false;
        }

        for (int c = 0; c < 3; c++) {
            int sum = 0;

            for (int r = 0; r < 3; r++) {
                sum += result.get(r).get(c);
            }

            if (target != sum) return false;
        }

        int dig1 = result.get(0).get(0) + result.get(1).get(1) + result.get(2).get(2);

        if (dig1 != target) return false;

        int dig2 = result.get(0).get(2) + result.get(1).get(1) + result.get(2).get(0);

        if (dig2 != target) return false;


        return true;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> s = new ArrayList<>();

        IntStream.range(0, 3).forEach(i -> {
            try {
                s.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
