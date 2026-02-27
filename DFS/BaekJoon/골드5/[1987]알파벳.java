import java.io.*;
import java.util.*;


public class Main {

    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int ans;
    static Map<Character, Boolean> visitMap;
    static char[][] grid;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        ans = 1;
        visitMap = new HashMap<>();
        visitMap.put(grid[0][0], true);

        solution(0, 0, 1);
        System.out.print(ans);

    }

    static void solution(int currentRow, int currentCol, int currentVal) {

        if (currentVal > ans) ans = currentVal;


        for (int i = 0; i < 4; i++) {
            int nextRow = currentRow + dr[i];
            int nextCol = currentCol + dc[i];

            if (check(nextRow, nextCol)) {
                currentVal++;
                visitMap.put(grid[nextRow][nextCol], true);

                solution(nextRow, nextCol, currentVal);

                currentVal--;
                visitMap.remove(grid[nextRow][nextCol]);
            }

        }
    }

    static boolean check(int nextRow, int nextCol) {
        if (nextRow < 0 || nextRow >= R || nextCol < 0 || nextCol >= C || visitMap.get(grid[nextRow][nextCol]) != null) return false;
        else return true;
    }
}
