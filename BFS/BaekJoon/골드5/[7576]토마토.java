import java.io.*;
import java.util.*;

public class Main {

    static int[][] matrix;
    static boolean[][] visited;
    static ArrayList<Point> pointOfOne;
    static int ans;



    static class Point {

        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws  IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        matrix = new int[r][c];
        visited = new boolean[r][c];
        pointOfOne = new ArrayList<>();

        boolean zeroFlag = true;

        for (int i = 0; i < r; i++) {
            String[] line = br.readLine().split(" ");
            matrix[i] = Arrays.stream(line)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == 0) zeroFlag = false;
                if (matrix[i][j] == 1) pointOfOne.add(new Point(i, j));

            }
        }

        if (zeroFlag) {
            System.out.println(0);
            return;
        }

        ans = 0;
        bfs(r, c);

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(ans);







    }


    static void bfs(int r, int c) {

        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};

        Queue<Point> queue = new LinkedList<>();

        for (Point point : pointOfOne) {
            queue.offer(point);
            visited[point.r][point.c] = true;
        }

        int cnt = pointOfOne.size();
        int nextCnt = 0;

        while (!queue.isEmpty()) {

            if (cnt == 0) {
                ans++;
                cnt = nextCnt;
                nextCnt = 0;
            }

            Point current = queue.poll();
            cnt--;

            int currentRow = current.r;
            int currentCol = current.c;

            for (int i = 0; i < 4; i++) {

                int nr = currentRow + dr[i];
                int nc = currentCol + dc[i];

                if (nr < 0 || nr >= r || nc < 0 || nc >= c || matrix[nr][nc] == -1) continue;

                if (!visited[nr][nc]) {
                    queue.offer(new Point(nr, nc));
                    visited[nr][nc] = true;
                    matrix[nr][nc] = 1;
                    nextCnt++;
                }
            }
        }
    }
}

