import java.io.*;
import java.util.*;

class Node {

    int row;
    int col;
    int time;

    public Node(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class Main {

    static int ans;
    static int[][] matrix;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};


    static ArrayList<int[]> results;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= t; tc++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            matrix = new int[n][m];
            ans = 0;
            results = new ArrayList<>();


            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(R, C, L, n, m);

            sb.append("#" + tc + " " + ans + "\n");


        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int R, int C, int L, int n, int m) {

        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        Node node = new Node(R, C, 1);

        queue.offer(node);
        visited[R][C] = true;

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            ans++;
            results.add(new int[]{current.row, current.col});


            ArrayList<int[]> nexts = findNexts(current, n, m, visited);

            for (int[] next : nexts) {

                if (current.time >= L) continue;

                boolean upFlag = false;
                boolean downFlag = false;
                boolean leftFlag = false;
                boolean rightFlag = false;

                if (next[2] == 0) leftFlag = true;
                if (next[2] == 1) rightFlag = true;
                if (next[2] == 2) upFlag = true;
                if (next[2] == 3) downFlag = true;

                if (leftFlag) {
                    if (matrix[next[0]][next[1]] == 2 || matrix[next[0]][next[1]] == 6 || matrix[next[0]][next[1]] == 7) continue;
                }

                if (rightFlag) {
                    if (matrix[next[0]][next[1]] == 2 || matrix[next[0]][next[1]] == 4 || matrix[next[0]][next[1]] == 5) continue;
                }

                if (upFlag) {
                    if (matrix[next[0]][next[1]] == 3 || matrix[next[0]][next[1]] == 4 || matrix[next[0]][next[1]] == 7) continue;
                }

                if (downFlag) {
                    if (matrix[next[0]][next[1]] == 3 || matrix[next[0]][next[1]] == 5 || matrix[next[0]][next[1]] == 6) continue;
                }


                queue.offer(new Node(next[0], next[1], current.time + 1));
                visited[next[0]][next[1]] = true;
            }

        }

    }

    static ArrayList<int[]> findNexts(Node current, int n, int m, boolean visited[][]) {
        int r = current.row;
        int c = current.col;

        int val = matrix[r][c];

        ArrayList<int[]> tmp = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (val == 2 && (i == 0 || i == 1)) continue;
            if (val == 3 && (i == 2 || i == 3)) continue;
            if (val == 4 && (i == 0 || i == 3)) continue;
            if (val == 5 && (i == 0 || i == 2)) continue;
            if (val == 6 && (i == 1 || i == 2)) continue;
            if (val == 7 && (i == 1 || i == 3)) continue;

            int nr = r + dr[i];
            int nc = c + dc[i];

            if (check(nr, nc, n, m, visited)) {
                tmp.add(new int[]{nr, nc, i});
            }
        }

        return tmp;

    }

    static boolean check(int nr, int nc, int n, int m, boolean[][] visited) {
        if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || matrix[nr][nc] == 0) return false;

        return true;
    }


}