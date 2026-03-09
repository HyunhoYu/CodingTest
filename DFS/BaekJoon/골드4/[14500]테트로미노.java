import java.io.*;
import java.util.*;

public class Main {

    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};

    static int N;
    static int M;

    static int[][] grid;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                solution(i, j);
                tetromino4(i, j);
            }
        }

        System.out.println(ans);
    }

    static void solution(int row, int col) {

        char[][] allCommands = {
                {'G', 'G', 'G'},
                {'G', 'R', 'G', 'R', 'G'},
                {'G', 'G', 'R', 'G'}
        };

        for (int dir = 0; dir < 4; dir++) {
            for (char[] cmds : allCommands) {
                simulate(row, col, 0, 0, dir, cmds);
            }
        }
    }

    static void simulate(int row, int col, int index, int sum,
                         int direction, char[] commands) {

        if (!check(row, col)) return;
        sum += grid[row][col];

        if (index == commands.length) {
            if (sum > ans) ans = sum;
            return;
        }

        if (commands[index] == 'G') {
            int[] result = go(row, col, index, sum, direction);
            simulate(result[0], result[1], result[2], result[4], result[3], commands);
        } else {
            for (int i = 0; i < 2; i++) {
                int[] result = (i == 0)
                        ? revolutionLeft(row, col, index, sum, direction)
                        : revolutionRight(row, col, index, sum, direction);
                simulate(result[0], result[1], result[2], result[4], result[3], commands);
            }
        }
    }

    static void tetromino4(int row, int col) {

        for (int i = 0; i < 4; i++) {
            int except = i;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{row, col});

            for (int j = 0; j < 4; j++) {

                if (j == except) continue;

                int nextRow = row + dr[j];
                int nextCol = col + dc[j];

                if (!check(nextRow, nextCol)) break;

                q.offer(new int[]{nextRow, nextCol});
            }

            int sum = 0;

            while (!q.isEmpty()) {
                int[] point = q.poll();
                sum += grid[point[0]][point[1]];
            }

            if (sum > ans) ans = sum;
        }
    }

    static int[] go(int row, int col, int index, int sum, int direction) {
        int[] result = new int[5];
        result[0] = row + dr[direction];
        result[1] = col + dc[direction];
        result[2] = index + 1;
        result[3] = direction;
        result[4] = sum;
        return result;
    }

    static int[] revolutionLeft(int row, int col, int index, int sum, int direction) {
        int[] result = new int[5];
        result[0] = row;
        result[1] = col;
        result[2] = index + 1;
        result[3] = (direction + 3) % 4;
        result[4] = sum - grid[row][col];
        return result;
    }

    static int[] revolutionRight(int row, int col, int index, int sum, int direction) {
        int[] result = new int[5];
        result[0] = row;
        result[1] = col;
        result[2] = index + 1;
        result[3] = (direction + 1) % 4;
        result[4] = sum - grid[row][col];
        return result;
    }

    static boolean check(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= M) return false;
        return true;
    }
}