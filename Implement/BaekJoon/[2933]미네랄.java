import java.io.*;
import java.util.*;

class Point implements Comparable<Point>{

    int r;
    int c;


    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Point o) {
        return o.r - this.r;
    }
}

public class Main {
    static char[][] matrix;
    static ArrayList<ArrayList<Point>> clusters;
    static int[] cmd;
    static int R;
    static int C;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        matrix = new char[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < C; j++) {
                matrix[i][j] = str.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        cmd = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cmd[i] = Integer.parseInt(st.nextToken());
        }

        simulate();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(matrix[i][j]);
            }
            if (i == R - 1) break;
            System.out.println();
        }


    }

    static void simulate() {


        for (int i = 0; i < cmd.length; i++) {

            eraseMineral(i);


            clusters = new ArrayList<>();
            clustering();


            ArrayList<Point> unstableCluster = findUnstableCluster();

            if (unstableCluster.isEmpty()) continue;


            int distance = findDistance(unstableCluster);


            fallDown(unstableCluster, distance);

        }

    }

    static void fallDown(ArrayList<Point> unstableCluster, int distance) {

        for (Point point : unstableCluster) {
            matrix[point.r][point.c] = '.';
            matrix[point.r + distance][point.c] = 'x';
        }

    }

    static ArrayList<Point> findUnstableCluster() {
        for (ArrayList<Point> cluster : clusters) {
            Collections.sort(cluster);

            if (cluster.get(0).r != R - 1) return cluster;

        }

        return new ArrayList<>();
    }

    static int findDistance(ArrayList<Point> unstableCluster) {


        int minDistance = Integer.MAX_VALUE;

        ArrayList<Point> candidatePoints = new ArrayList<>();
        ArrayList<ArrayList<Point>> tmp = new ArrayList<>();

        for (int i = 0; i < C; i++) {

            ArrayList<Point> sameCols = new ArrayList<>();

            for (Point point : unstableCluster) {
                if (point.c == i) {
                    sameCols.add(point);
                }
            }

            if (!sameCols.isEmpty()) {
                tmp.add(sameCols);
            }
        }

        for (ArrayList<Point> sameCols : tmp) {
            Collections.sort(sameCols);
            candidatePoints.add(sameCols.get(0));
        }



        for (Point candidatePoint : candidatePoints) {

            int candidateRow = candidatePoint.r;
            int candidateCol = candidatePoint.c;

            for (int i = candidateRow + 1; i < R; i++) {

                if (matrix[i][candidateCol] == 'x' && (i - candidateRow) < minDistance) {
                    minDistance = (i - candidateRow);
                }
            }

        }


        if (minDistance == Integer.MAX_VALUE) {
            Collections.sort(candidatePoints);



            return R - 1 - candidatePoints.get(0).r;

        }

        return minDistance - 1;



    }

    static void clustering() {

        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};
        boolean[][] visited = new boolean[R][C];

        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {

                if (!visited[i][j] && matrix[i][j] == 'x') {
                    ArrayList<Point> cluster = new ArrayList<>();
                    clusters.add(cluster);



                    Queue<Point> queue = new ArrayDeque<>();
                    cluster.add(new Point(i, j));
                    visited[i][j] = true;

                    queue.offer(new Point(i, j));

                    while (!queue.isEmpty()) {

                        Point current = queue.poll();

                        int row = current.r;
                        int col = current.c;



                        for (int k = 0; k < 4; k++) {

                            int nextRow = row + dr[k];
                            int nextCol = col + dc[k];

                            if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || visited[nextRow][nextCol] || matrix[nextRow][nextCol] == '.') continue;

                            queue.offer(new Point(nextRow, nextCol));
                            cluster.add(new Point(nextRow, nextCol));
                            visited[nextRow][nextCol] = true;
                        }

                    }

                }
            }
        }


    }

    static void eraseMineral(int index) {

        int targetRow = R - cmd[index];

        if ((index % 2) == 0) {

            for (int i = 0; i < C; i++) {

                if(matrix[targetRow][i] == '.') continue;

                matrix[targetRow][i] = '.';
                break;
            }
        }  else {

            for (int i = C - 1; i >= 0; i--) {

                if(matrix[targetRow][i] == '.')continue;

                matrix[targetRow][i] = '.';
                break;
            }
        }
    }



}