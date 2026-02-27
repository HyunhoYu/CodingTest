import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int ans, N, M;
    static int[][] map;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtrack(0);

        System.out.println(ans);
    }

    static void backtrack(int depth) {



        if (depth == 3) {

            int[][] tmp = copyArr(map);

            simulate(tmp);
            int result = countEmptySpace(tmp);

            if (result > ans) ans = result;
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    backtrack(depth + 1);
                    map[i][j] = 0;
                }
            }
        }




    }

    static void simulate(int[][] arr) {

        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};

        Queue<int[]> virusPointQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (arr[i][j] == 2) {
                    virusPointQueue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!virusPointQueue.isEmpty()) {

            int[] current = virusPointQueue.poll();

            int r = current[0];
            int c = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || arr[nr][nc] != 0) continue;

                virusPointQueue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
                arr[nr][nc] = 2;
            }

        }



    }

    static int countEmptySpace(int[][] arr) {
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (arr[i][j] == 0) result++;
            }
        }
        return result;
    }

    static int[][] copyArr(int[][] arr) {
        int[][] tmp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = arr[i][j];
            }
        }

        return tmp;
    }
}
