import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    static int maxHouse;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= tc; t++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());


            maxHouse = 1;

            int[][] matrix = new int[n][n];

            for (int i = 0; i < n; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());


                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    int[] initPoint = new int[]{i, j};

                    bfs(n, m, matrix, initPoint);


                }
            }

            sb.append("#" + t + " " + maxHouse + "\n");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }


    static void bfs(int n, int m, int[][] matrix, int[] initPoint) {
        Queue<int[]> queue = new ArrayDeque();
        boolean[][] visited = new boolean[n][n];

        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};

        int houseCount = 0;

        int initRow = initPoint[0];
        int initCol = initPoint[1];

        queue.offer(new int[]{initRow, initCol});
        visited[initRow][initCol] = true;

        if (matrix[initRow][initCol] == 1) houseCount++;


        int queueSize = 1;
        int now = 0;
        int k = 1;
        while (!queue.isEmpty()) {

            int[] point = queue.poll();

            int row = point[0];
            int col = point[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) continue;

                if (matrix[nextRow][nextCol] == 1) houseCount++;

                queue.offer(new int[]{nextRow, nextCol});
                visited[nextRow][nextCol] = true;


            }

            now++;

            if (now == queueSize) {
                k++;
                now = 0;
                queueSize = queue.size();

                if (check(k, m, houseCount)) {

                    if (houseCount > maxHouse) maxHouse = houseCount;
                }

            }
        }

    }

    static boolean check(int k, int m, int houseCount) {

        int cost = k * k + (k - 1) * (k - 1);
        int profit = m * houseCount;

        if (profit >= cost) return true;
        return false;
    }

}