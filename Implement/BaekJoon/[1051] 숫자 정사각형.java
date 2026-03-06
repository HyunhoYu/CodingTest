import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        max = 1;
        int N;
        int M;

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                grid[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }


        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){

                if (j == M - 1) continue;

                int currentVal = grid[i][j];
                List<Integer> indexList = new ArrayList<>();

                indexList.add(j);

                for (int k = j + 1; k < M ; k++) {
                    if (grid[i][k] == currentVal) {
                        indexList.add(k);
                    }
                }


                List<List<Integer>> combs = makeCombination(indexList);
                makeSquare(combs, i, N, grid);
            }
        }

        System.out.print(max);

    }

    static List<List<Integer>> makeCombination(List<Integer> indexList) {

        List<List<Integer>> result = new ArrayList<>();

        for (int currentIndex = 0; currentIndex < indexList.size() - 1; currentIndex++) {
            for (int i = currentIndex + 1; i < indexList.size(); i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(indexList.get(currentIndex));
                tmp.add(indexList.get(i));
                result.add(tmp);
            }
        }

        return result;

    }

    static void makeSquare(List<List<Integer>> combs, int topRow, int N, int[][] grid) {

        for (List<Integer> comb : combs) {
            int col1 = comb.get(0);
            int col2 = comb.get(1);

            int width = col2 - col1;
            int bottomRow = topRow + width;

            if (bottomRow >= N) continue;

            if (grid[bottomRow][col1] == grid[topRow][col1] && grid[bottomRow][col2] == grid[topRow][col2]) {
                int size = (width + 1) * (width + 1);
                if (size > max) {
                    max = size;
                }
            }
        }

    }




}
