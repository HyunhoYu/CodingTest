import java.io.*;
import java.util.*;


enum Status {
    INACTIVE,
    ACTIVE,
    DEAD
}

class Cell implements Comparable<Cell>{

    Status status;
    int lifePower;
    int birthTime;
    int activeTime;
    int row;
    int col;

    public Cell(int lifePower, int row, int col) {

        this.lifePower = lifePower;
        this.status = Status.INACTIVE;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Cell o) {
        return o.lifePower - this.lifePower;
    }
}

public class Main {


    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());


            int gridHeight = n + 2 * k
            int gridWidth = m + 2 * k

            int[][] grid = new int[gridHeight][gridWidth];
            ArrayList<Cell> cells = new ArrayList<>();

            int row = gridHeight / 2;
            int col = gridWidth / 2;

            boolean[][] visited = new boolean[gridHeight][gridWidth];


            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < m; j++) {
                    grid[row][col] = Integer.parseInt(st.nextToken());

                    if (grid[row][col] != 0) {
                        cells.add(new Cell(grid[row][col], row, col));
                        visited[row][col] = true;
                    }
                    col++;
                }
                col = gridWidth / 2;
                row++;
            }

            int result = simulate(grid, k, cells, visited);



            sb.append("#" + tc + " " + result + "\n");


        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int simulate(int[][] grid, int k, ArrayList<Cell> cells, boolean[][] visited) {

        int time = 0;
        int aliveCellCount = 0;



        for (Cell cell : cells) {

            cell.birthTime = time;
        }




        while (true) {



            PriorityQueue<Cell> pq = statusChange(cells, time);

            if (time == k) break;

            if (!pq.isEmpty()) {

                breed(grid, pq, visited, time, cells);

            }


            time++;
        }



        for (Cell cell : cells) {
            if (cell.status != Status.DEAD) aliveCellCount++;
        }

        return aliveCellCount;

    }

    static PriorityQueue<Cell> statusChange(ArrayList<Cell> cells, int time) {

        PriorityQueue<Cell> pq = new PriorityQueue<>();

        for (Cell cell : cells) {

            switch (cell.status) {

                case INACTIVE:
                    if (time == cell.lifePower + cell.birthTime) {
                        cell.status = Status.ACTIVE;
                        cell.activeTime = time;
                        pq.offer(cell);
                    }
                    break;

                case ACTIVE:
                    if (time == cell.activeTime + cell.lifePower) cell.status = Status.DEAD;
                    break;

                case DEAD:
                    break;

            }
        }


        return pq;
    }


    static void breed(int[][] grid, PriorityQueue<Cell> pq, boolean[][] visited, int time, ArrayList<Cell> cells) {

        int[] dr = new int[]{0, 0, -1, 1};
        int[] dc = new int[]{-1, 1, 0, 0};




        while (!pq.isEmpty()) {

            Cell cell = pq.poll();

            int row = cell.row;
            int col = cell.col;

            for (int i = 0; i < 4; i++) {

                int nextRow = row + dr[i];
                int nextCol = col + dc[i];

                if (canBreed(visited, nextRow, nextCol)) {
                    grid[nextRow][nextCol] = cell.lifePower;
                    Cell newCell = new Cell(cell.lifePower, nextRow, nextCol);
                    newCell.birthTime = time + 1;
                    cells.add(newCell);
                    visited[nextRow][nextCol] = true;

                }


            }


        }


    }

    static boolean canBreed(boolean[][] visited, int row, int col) {

        if (visited[row][col]) return false;
        return true;
    }


}