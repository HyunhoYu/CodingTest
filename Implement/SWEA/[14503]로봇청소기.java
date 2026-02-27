import java.io.*;
import java.util.StringTokenizer;

class Point {
    int row;
    int col;


    public Point(int row, int col) {
        this.row = row;
        this.col = col;

    }
}
class Robot {

    Point point;
    int direction;

    public Robot(Point point, int direction) {
        this.point = point;
        this.direction = direction;
    }
}

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dr = new int[]{0, 0, - 1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};

    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        st = new StringTokenizer(br.readLine());

        Robot robot;

        int row;
        int col;
        int direction;

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());

        robot = new Robot(new Point(row, col), direction);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        solution(robot, N, M);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void solution(Robot robot, int N, int M) {

        while (true) {

            cleanCurrentPoint(robot);

            if (shouldGoBack(robot, N, M)) {

                revolution180(robot);
                move(robot);
                int row = robot.point.row;
                int col = robot.point.col;
                if (!validateMapIndexBoundary(row, col, N, M) || !validateWall(row, col)) break;
                revolution180(robot);
                continue;


            }

            while (true) {

                revolutionLeft(robot);
                move(robot);

                int r = robot.point.row;
                int c = robot.point.col;

                if (validateMapIndexBoundary(r, c, N, M) && validateWall(r, c) && validateVisited(r, c)) {
                    break;
                }

                revolution180(robot);
                move(robot);
                revolution180(robot);
            }


        }
    }

    static void cleanCurrentPoint(Robot robot) {

        int r = robot.point.row;
        int c = robot.point.col;

        if (!visited[r][c]) {
            visited[r][c] = true;
            ans++;
        }

    }

    static boolean shouldGoBack(Robot robot, int N, int M) {

        boolean goBackFlag = true;

        int r = robot.point.row;
        int c = robot.point.col;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (validateMapIndexBoundary(nr, nc, N, M) && validateVisited(nr, nc) && validateWall(nr, nc)) {
                goBackFlag = false;
                break;
            }
        }

        return goBackFlag;
    }



    static void revolutionLeft(Robot robot) {

        int currentDirection = robot.direction;
        robot.direction = (currentDirection + 3) % 4;
    }

    static void revolution180(Robot robot) {

        int currentDirection = robot.direction;
        robot.direction = (currentDirection + 2) % 4;
    }

    static void move(Robot robot) {

        int currentRow = robot.point.row;
        int currentCol = robot.point.col;


        switch (robot.direction) {
            case 0:
                robot.point = new Point(currentRow - 1, currentCol);
                break;

            case 1:
                robot.point = new Point(currentRow, currentCol + 1);
                break;

            case 2:
                robot.point = new Point(currentRow + 1, currentCol);
                break;

            case 3:
                robot.point = new Point(currentRow, currentCol - 1);
                break;
        }

    }

    static boolean validateMapIndexBoundary(int r, int c, int N, int M) {



        if (r < 0 || r >= N || c < 0 || c >= M) return false;
        return true;
    }

    static boolean validateVisited(int r, int c) {


        if (visited[r][c]) return false;
        return true;
    }

    static boolean validateWall(int r, int c) {

        if (map[r][c] == 1) return false;
        return true;
    }






}