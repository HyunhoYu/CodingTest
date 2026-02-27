import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {

    static int[][] matrix;

    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 1; i <= t; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());


            matrix = new int[r][c];

            for (int j = 0; j < r; j++) {
                matrix[j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            min = Integer.MAX_VALUE;

            dfs(0, r, k, 0);
            sb.append("#" + i + " " + min + "\n");

        }
        System.out.println(sb);


    }

    static void dfs(int depth, int r, int k, int cnt) {

        if (cnt >= min) return;

        if (depth == r) {
            if (check(k)) {
                min = Math.min(min, cnt);
            }
            return;
        }

        dfs(depth + 1, r, k, cnt);

        int[] tmp = matrix[depth].clone();

        Arrays.fill(matrix[depth], 0);
        dfs(depth + 1, r, k, cnt + 1);


        Arrays.fill(matrix[depth], 1);
        dfs(depth + 1, r, k, cnt + 1);

        matrix[depth] = tmp;


    }

    static boolean check(int k) {

        for (int i = 0; i < matrix[0].length; i++) {
            int maxSame = 1;
            int same = 1;
            for (int j = 0; j < matrix.length - 1; j++) {

                if (matrix[j][i] == matrix[j + 1][i]) {
                    same++;
                } else {
                    same = 1;
                }
                maxSame = Math.max(maxSame, same);
            }

            if (maxSame < k) return false;
        }

        return true;
    }
}

