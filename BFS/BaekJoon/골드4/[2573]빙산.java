import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]> pointsOfIce;
    static int[][] matrix;

    static int[] dr = new int[]{0, 0, -1, 1};

    static int[] dc = new int[]{-1, 1, 0, 0};

    static int yearCount;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        matrix = new int[n][m];
        pointsOfIce = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            matrix[i] = Arrays.stream(line)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 0) {
                    pointsOfIce.add(new int[]{i, j});
                }
            }
        }

        yearCount = 0;
        melt(n, m);
        bw.write(yearCount + "\n");
        bw.flush();
        bw.close();
        br.close();



    }

    static void melt(int n, int m) {


        if (pointsOfIce.isEmpty()) {
            yearCount = 0;  // 문제 조건에 따라 0으로 설정
            return;
        }

        ArrayList<Integer> copyValList = new ArrayList<>();


        for (int[] point : pointsOfIce) {
            int r = point[0];
            int c = point[1];

            int copyVal = matrix[r][c];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= n || nr < 0 || nc >= m || nc < 0 || matrix[nr][nc] != 0) continue;

                copyVal--;
            }
            copyValList.add(copyVal);
        }



        Iterator<int[]> iterator = pointsOfIce.iterator();
        int idx = 0;
        while (iterator.hasNext()) {
            int[] point = iterator.next();
            int row = point[0];
            int col = point[1];

            if (copyValList.get(idx) <= 0) {
                matrix[row][col] = 0;
                iterator.remove();
            } else {
                matrix[row][col] = copyValList.get(idx);
            }
            idx++;
        }


        yearCount++;

        if (countLand(n, m)) return;
        melt(n, m);

    }

    static boolean countLand(int n, int m) {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];


        for (int[] point : pointsOfIce) {

            int currentRow = point[0];
            int currentCol = point[1];

            if (!visited[currentRow][currentCol]) {
                queue.offer(new int[]{currentRow, currentCol});
                visited[currentRow][currentCol] = true;
                cnt++;
            }

            while (!queue.isEmpty()) {

                int[] current = queue.poll();

                int r = current[0];
                int c = current[1];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || matrix[nr][nc] == 0 || visited[nr][nc]) continue;
                    queue.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }
            }



        }

        if (cnt >= 2) return true;
        return false;
    }
}

