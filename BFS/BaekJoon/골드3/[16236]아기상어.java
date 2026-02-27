import java.io.*;
import java.util.*;

public class Main {

    static int[][] matrix;


    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static int timer;

    static int eatingCount;


    static class Point implements Comparable<Point>{
        int row;
        int col;


        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Point(int row, int col, int dist) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Point o) {
            if (this.row != o.row) {
                return Integer.compare(this.row, o.row);
            }
            return Integer.compare(this.col, o.col);
        }
    }


    static class Fish implements Comparable<Fish>{
        Point point;
        int size;

        int dist;

        public Fish(Point point, int size) {
            this.point = point;
            this.size = size;
        }

        public Fish(Point point, int size, int dist) {
            this.point = point;
            this.size = size;
            this.dist = dist;
        }




        @Override
        public int compareTo(Fish o) {
            if (this.dist != o.dist) {
                return Integer.compare(this.dist, o.dist);
            }
            return this.point.compareTo(o.point);
        }
    }




    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        matrix = new int[n][n];


        Fish shark = null;

        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 9) {
                    Point tmp = new Point(i, j);
                    shark = new Fish(tmp, 2);
                    shark.dist = 0;
                    matrix[i][j] = 0;
                }

            }
        }

        timer = 0;
        eatingCount = 0;


        eat(shark, n);
        System.out.println(timer);


    }

    static void eat(Fish shark, int n) {
        ArrayList<Fish> eatableFishes = setDist(shark, n);

        if (eatableFishes.isEmpty()) return;

        Collections.sort(eatableFishes);
        Fish targetFish = eatableFishes.get(0);

        timer += targetFish.dist;
        eatingCount++;
        shark.point.row = targetFish.point.row;
        shark.point.col = targetFish.point.col;
        matrix[shark.point.row][shark.point.col] = 0;

        if (eatingCount == shark.size) {
            shark.size++;
            eatingCount = 0;
        }

        eat(shark, n);



    }

    static ArrayList<Fish> setDist(Fish shark, int n) {
        Queue<Fish> queue = new ArrayDeque<>();
        boolean visited[][] = new boolean[n][n];
        ArrayList<Fish> eatableFishes = new ArrayList<>();

        queue.offer(shark);
        visited[shark.point.row][shark.point.col] = true;

        while (!queue.isEmpty()) {
            Fish current = queue.poll();

            if (matrix[current.point.row][current.point.col] != 0 && current.size < shark.size) {
                eatableFishes.add(current);
            }
            int r = current.point.row;
            int c = current.point.col;
            int d = current.dist;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nd = d + 1;

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && matrix[nr][nc] <= shark.size) {
                    queue.offer(new Fish(new Point(nr, nc), matrix[nr][nc], nd));
                    visited[nr][nc] = true;
                }
            }
        }

        return eatableFishes;

    }


}

