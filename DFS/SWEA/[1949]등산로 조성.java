import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static int[][] matrix;
    static int maxLength;
    static boolean[][] visited; //등산로가 높은곳에서 낮은곳으로 조성되어서 visited 체킹이 필요없을거 같지만, 등산로를 깎는 과정에서 이미 방문한곳을 깎는 것을 방지해야되므로 visted체킹이 필요






    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= t; i++) {
            st = new StringTokenizer(br.readLine());

            maxLength = 0;
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            matrix = new int[n][n];
            visited = new boolean[n][n];

            int startValue = 0;
            for (int j = 0; j < n; j++) {
                matrix[j] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                for (int l = 0; l < matrix[j].length; l++) {
                    startValue = Math.max(startValue, matrix[j][l]);
                }
            }

            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    if (matrix[j][l] == startValue) {
                        dfs(j, l, 1, false, n, k);

                    }
                }
            }

            sb.append("#" + i + " " + maxLength + "\n");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int currentRow, int currentCol, int depth, boolean isUsedChance, int n, int k) {

        int currentValue = matrix[currentRow][currentCol];
        visited[currentRow][currentCol] = true;


        for (int i = 0; i < 4; i++) {
            int nextRow = currentRow + dr[i];
            int nextCol = currentCol + dc[i];


            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) continue;

            int nextValue = matrix[nextRow][nextCol];

            if (currentValue > nextValue) {
                dfs(nextRow, nextCol, depth + 1, isUsedChance, n, k);


            } else if (nextValue - currentValue < k && !isUsedChance) {
                //문제에서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다고 했는데 이는 무조건 K값을 깎는것을 의미하진 않는다. 다른 제약조건을 만족한다면 K값 보다 작은 값으로 깎을 수 있다.
//                matrix[nextRow][nextCol] = nextValue - k;
//                isUsedChance = true;
//                dfs(nextRow, nextCol, depth + 1, isUsedChance, n, k);
//
//                matrix[nextRow][nextCol] = nextValue;
//                isUsedChance = false;

                for (int j = k; j > nextValue - currentValue; j--) {
                    matrix[nextRow][nextCol] = nextValue - j;
                    isUsedChance = true;
                    dfs(nextRow, nextCol, depth + 1, isUsedChance, n, k);

                    matrix[nextRow][nextCol] = nextValue;
                    isUsedChance = false;
                }
            }
        }

        visited[currentRow][currentCol] = false;
        maxLength = Math.max(maxLength, depth);




    }
}
