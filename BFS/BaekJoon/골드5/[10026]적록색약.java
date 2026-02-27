import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static String[][] matrix;
    static boolean[][] visited;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        matrix = new String[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = line[j];
            }
        }




        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, n);
                    cnt++;
                }
            }
        }

        System.out.printf("%d ", cnt);

        visited = new boolean[n][n];
        cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    bfs2(i, j, n);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);

    }

    static void bfs(int r, int c, int n) {
        Queue<int[]> queue = new ArrayDeque<>();

        int[] point = new int[]{r, c};

        queue.offer(point);
        visited[r][c] = true;

        while (!queue.isEmpty()) {

            int[] rc = queue.poll();

            int row = rc[0];
            int col = rc[1];

            for (int i = 0; i < 4; i++) {

                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol] || !matrix[row][col].equals(matrix[nextRow][nextCol])) continue;


                int[] nextRc = new int[]{nextRow, nextCol};

                queue.offer(nextRc);
                visited[nextRow][nextCol] = true;

            }
        }
    }

    static void bfs2(int r, int c, int n) {
        Queue<int[]> queue = new ArrayDeque<>();

        boolean flag = false;

        if (matrix[r][c].equals("R") || matrix[r][c].equals("G")) flag = true;

        int[] point = new int[]{r, c};

        queue.offer(point);
        visited[r][c] = true;

        while (!queue.isEmpty()) {

            int[] rc = queue.poll();

            int row = rc[0];
            int col = rc[1];

            for (int i = 0; i < 4; i++) {

                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) continue;

                if (flag && matrix[nextRow][nextCol].equals("B")) {
                    continue;
                } else if (!flag && !matrix[row][col].equals(matrix[nextRow][nextCol])) {
                    continue;
                }

                queue.offer(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;


            }
        }
    }
}
