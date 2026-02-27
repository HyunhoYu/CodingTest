import java.io.*;
import java.util.*;

public class Main {

    static char[][] matrix;

    static Queue<int[]> waterQueue = new ArrayDeque<>();
    static Queue<int[]> hedgehogQueue = new ArrayDeque<>();
    static int[] cavePoint = new int[2];

    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        matrix = new char[r][c];


        for (int i = 0; i < r; i++) {
            String line = br.readLine();

            for (int j = 0; j < c; j++) {
                char character = line.charAt(j);
                matrix[i][j] = character;

                if (character == 'S') {
                    hedgehogQueue.offer(new int[]{i, j, 0});
                } else if (character == '*') {
                    waterQueue.offer(new int[]{i, j});
                } else if (character == 'D') {
                    cavePoint = new int[]{i, j};
                }
            }
        }

        bfs(r, c);
        System.out.println("KAKTUS");



    }

    static void bfs(int row, int col) {

        while (!hedgehogQueue.isEmpty()) {

            int iter = waterQueue.size();

            for (int i = 0; i < iter; i++) {
                int[] water = waterQueue.poll();
                int r = water[0];
                int c = water[1];
                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];

                    if (nr >= 0 && nr < row && nc >= 0 && nc < col && matrix[nr][nc] == '.') {
                        matrix[nr][nc] = '*';
                        waterQueue.offer(new int[]{nr, nc});
                    }
                }
            }



            int it = hedgehogQueue.size();

            for (int i = 0; i < it; i++) {

                int[] hedgehog = hedgehogQueue.poll();

                int r = hedgehog[0];
                int c = hedgehog[1];
                int time = hedgehog[2];



                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];

                    if (nr >= 0 && nr < row && nc >= 0 && nc < col) {

                        if (matrix[nr][nc] == '.') {
                            matrix[nr][nc] = 'S';
                            hedgehogQueue.offer(new int[]{nr, nc, time + 1});
                        } else if (matrix[nr][nc] == 'D') {
                            System.out.println(time + 1);
                            System.exit(0);
                        }
                    }
                }
            }

        }
    }


}

