import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static int[][] grid;
    static int[] dice;
    static int N;
    static int M;
    static int x;
    static int y;
    static int K;
    static int leftK;
    static int[] dx = new int[]{2100000000, 1, -1, 0, 0};
    static int[] dy = new int[]{2100000000, 0, 0, -1, 1};


    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        leftK = K;

        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        dice = new int[6];


        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken());


            int[] diceCopy = new int[6];
            for (int j = 0; j < 6; j++) {
                diceCopy[j] = dice[j];
            }

            act(command, diceCopy);


        }

        System.out.print(sb);


    }


    static void act(int command, int[] diceCopy) {

        if (!canIAct(command)) return;

        switch (command) {


            case 1:
                dice[5] = diceCopy[2];
                dice[2] = diceCopy[0];
                dice[0] = diceCopy[3];
                dice[3] = diceCopy[5];
                break;

            case 2:
                dice[3] = diceCopy[0];
                dice[0] = diceCopy[2];
                dice[5] = diceCopy[3];
                dice[2] = diceCopy[5];
                break;

            case 3:
                dice[4] = diceCopy[0];
                dice[5] = diceCopy[4];
                dice[1] = diceCopy[5];
                dice[0] = diceCopy[1];
                break;

            case 4:
                dice[1] = diceCopy[0];
                dice[5] = diceCopy[1];
                dice[4] = diceCopy[5];
                dice[0] = diceCopy[4];
                break;


            default:
                break;

        }

        leftK--;
        if (leftK == 0) {
            sb.append(dice[0]);
        } else {
            sb.append(dice[0] + "\n");
        }


        setPoint(command);
        setGridAndDice();

    }

    static boolean canIAct(int command) {

        int[] nextPoint = generateNextPoint(command);

        int nextX = nextPoint[0];
        int nextY = nextPoint[1];

        if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N) return false;

        return true;

    }

    static int[] generateNextPoint(int command) {
        int[] result = new int[2];

        int nextX = x + dx[command];
        int nextY = y + dy[command];

        result[0] = nextX;
        result[1] = nextY;

        return result;
    }


    static void setPoint(int command) {

        int[] nextPoint = generateNextPoint(command);

        x = nextPoint[0];
        y = nextPoint[1];

    }

    static void setGridAndDice() {
        if (grid[y][x] == 0) {
            grid[y][x] = dice[5];
        } else {
            dice[5] = grid[y][x];
            grid[y][x] = 0;
        }
    }




}