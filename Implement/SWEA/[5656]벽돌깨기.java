import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] matrix;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static int min;


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
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            matrix = new int[h][w];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backtrack(n, 0, w, h, matrix);
            sb.append("#" + tc + " " + min + "\n");

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void backtrack(int n, int depth, int w, int h, int[][] matrix) {

        if (depth == n) {
            int cnt = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (matrix[i][j] != 0) cnt++;
                }
            }

            min = Math.min(min, cnt);
            return;
        }

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {

                if (matrix[j][i] > 0) {

                    int[][] copyMatrix = new int[h][w];
                    for (int k = 0; k < h; k++) {
                        for (int l = 0; l < w; l++) {
                            copyMatrix[k][l] = matrix[k][l];
                        }
                    }

                    rock(j, i, w, h, copyMatrix);
                    fillEmptySpace(w, h, copyMatrix);
                    backtrack(n, depth + 1, w, h, copyMatrix);
                    break;
                }
            }
        }

        int count = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] != 0) count++;
            }
        }

        min = Math.min(min, count);
    }

    static void rock(int r, int c, int w, int h, int[][] matrix) {

        int value = matrix[r][c];
        matrix[r][c] = 0;


        int it = value - 1;

        for (int i = 0; i < 4; i++) {
            int row = r;
            int col = c;

            for (int j = 0; j < it; j++) {
                row = row + dr[i];
                col = col + dc[i];

                if (!canMove(row, col , w, h)) break;

                int nextValue = matrix[row][col];

                if (nextValue > 1) rock(row, col, w, h, matrix);

                matrix[row][col] = 0;

            }
        }


    }

    static void fillEmptySpace(int w, int h, int[][] matrix) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < w; i++) {
            for (int j = h - 1; j >= 0; j--) {
                if (matrix[j][i] > 0) queue.offer(matrix[j][i]);
            }

            for (int j = h - 1; j >= 0; j--) {
                if (!queue.isEmpty()) {
                    matrix[j][i] = queue.poll();
                } else {
                    matrix[j][i] = 0;
                }
            }

        }
    }

    static boolean canMove(int r, int c, int w, int h) {
        if (r < 0 || r >= h || c < 0 || c >= w) return false;
        return true;
    }
}