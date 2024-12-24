
import java.io.*;
import java.util.*;

public class Main {

    static int n = 12;
    static int m = 6;

    static String[][] field = new String[n][m];

    static boolean keepGoing;

    static int cnt;

    static class Puyo {
        int r;
        int c;

        String color;

        public Puyo(int r, int c, String color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        for (int i = 0; i < n; i++) {
            field[i] = br.readLine().split("");
        }

        cnt = 0;

        while (true) {

            keepGoing = false;

            bfs();

            if (keepGoing == false) {
                break;
            }

            cnt++;
        }

        System.out.println(cnt);


    }

    static void bfs() {

        Queue<Puyo> queue = new LinkedList<>();
        boolean[][] visited = new boolean[12][6];

        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!(field[i][j].equals(".")) && !visited[i][j]) {
                    ArrayList<int[]> list = new ArrayList<>();
                    queue.offer(new Puyo(i, j, field[i][j]));
                    list.add(new int[]{i, j});
                    visited[i][j] = true;


                    while (!queue.isEmpty()) {

                        Puyo current = queue.poll();

                        int r = current.r;
                        int c = current.c;
                        String color = current.color;

                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];

                            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                            if (!visited[nr][nc] && field[nr][nc].equals(color)) {
                                list.add(new int[]{nr, nc});
                                visited[nr][nc] = true;
                                queue.offer(new Puyo(nr, nc, color));
                            }
                        }

                    }

                    if (list.size() >= 4) {
                        for (int[] ints : list) {
                            field[ints[0]][ints[1]] = ".";
                        }

                        keepGoing = true;
                    }

                }
            }
        }

        for (int i = 0; i < m; i++) {
            goDown(i);
        }

    }

    static void goDown(int col) {

        Queue<String> queue = new LinkedList<>();

        for (int row = n - 1; row >= 0; row--) {

            if (!field[row][col].equals(".")) {
                queue.offer(field[row][col]);
                field[row][col] = ".";
            }
        }

        int index = 11;

        while (!queue.isEmpty()) {

            String current = queue.poll();
            field[index][col] = current;
            index--;
        }

    }
}

