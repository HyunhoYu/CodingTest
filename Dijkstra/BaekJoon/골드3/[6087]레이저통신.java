import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int row;
    int col;
    int direction; //0 은 좌우 1은 상하
    int cost;

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }

    public Node(int row, int col, int direction, int cost) {
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.cost = cost;
    }
}

public class Main {

    static char[][] matrix;
    static int[][] cost;
    static int[] startPoint = new int[2];
    static int[] endPoint = new int[2];
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        matrix = new char[h][w];
        cost = new int[h][w];
        visited = new boolean[h][w][2];

        boolean startPointFlag = false;

        for (int i = 0; i < h; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < w; j++) {
                matrix[i][j] = line[j].charAt(0);
                cost[i][j] = Integer.MAX_VALUE;

                if (matrix[i][j] == 'C' && !startPointFlag) {
                    startPoint = new int[]{i, j};
                    startPointFlag = true;
                } else if (matrix[i][j] == 'C' && startPointFlag) {
                    endPoint = new int[]{i, j};
                }
            }
        }



        dijkstra(startPoint, w, h);
        System.out.println(cost[endPoint[0]][endPoint[1]]);

    }

    static void dijkstra(int[] startPoint, int w, int h) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int r = startPoint[0];
        int c = startPoint[1];
        cost[r][c] = 0;
        int direction = -1;

        for (int i = 0; i < 4; i++) {

            if (i <= 1) {
                direction = 0;
            } else {
                direction = 1;
            }
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < h && nc >= 0 && nc < w && matrix[nr][nc] != '*') {
                pq.offer(new Node(nr, nc, direction, 0));
                cost[nr][nc] = 0;
            }
        }

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            if (visited[current.row][current.col][current.direction]) continue;

            visited[current.row][current.col][current.direction] = true;

            for (int i = 0; i < 4; i++) {
                int nextCost;
                int nextDirection;


                if (i <= 1) {
                    nextDirection = 0;
                } else {
                    nextDirection = 1;
                }

                if (nextDirection == current.direction) {
                    nextCost = 0;
                } else {
                    nextCost = 1;
                }



                int nr = current.row + dr[i];
                int nc = current.col + dc[i];


                if (nr < 0 || nr >= h || nc < 0 || nc >= w || matrix[nr][nc] == '*') continue;



                if (cost[nr][nc] >= current.cost + nextCost) {
                    cost[nr][nc] = current.cost + nextCost;
                    pq.offer(new Node(nr, nc, nextDirection, current.cost + nextCost));
                }





            }
        }




    }
}

