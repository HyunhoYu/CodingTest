import java.io.*;
import java.util.*;

public class Main {

    static int[][] matrix;
    static ArrayList<ArrayList<Integer>> zeroPoint;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        matrix = new int[9][9];
        zeroPoint = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < 9; j++) {
                if (matrix[i][j] == 0) {
                    zeroPoint.add(new ArrayList<>(List.of(i, j)));
                }
            }
        }

        backtrack(0);


    }

    static void backtrack(int index) {

        if (index == zeroPoint.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            long endTime = System.currentTimeMillis();

            System.exit(0);
        }

        ArrayList<Integer> current = zeroPoint.get(index);
        int r = current.get(0);
        int c = current.get(1);

        for (int i = 1; i < 10; i++) {
            if (check(r, c, i)) {
                matrix[r][c] = i;
                backtrack(index + 1);
                matrix[r][c] = 0;
            }
        }
    }

    static boolean check(int r, int c, int value) {

        for (int i = 0; i < 9; i++) {
            if (matrix[i][c] == value) return false;
            if (matrix[r][i] == value) return false;
        }

        int startRow = r / 3 * 3;
        int startCol = c / 3 * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (matrix[i][j] == value) return false;
            }
        }

        return true;
    }


}

