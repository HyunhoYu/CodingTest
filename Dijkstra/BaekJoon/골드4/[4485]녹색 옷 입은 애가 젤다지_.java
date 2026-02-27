import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{

    int row;
    int col;
    int cost;

    public Node(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {

    static int[][] matrix;

    static int[][] cost;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{-1, 1, 0, 0};
    static int p = 1;




    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());

            if (n == 0) break;

            matrix = new int[n][n];

            cost = new int[n][n];

            for (int i = 0; i < n; i++) {

                matrix[i] = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                for (int j = 0; j < n; j++) {
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }

            bw.write("Problem " + p + ": " + dijkstra(n) + "\n");
            p++;


        }
        bw.flush();
        bw.close();
        br.close();
    }

    static int dijkstra(int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, matrix[0][0]));
        cost[0][0] = matrix[0][0];


        while (!pq.isEmpty()) {

            Node current = pq.poll();

            int r = current.row;
            int c = current.col;
            int currentCost = current.cost;



            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;


                if (cost[nr][nc] > currentCost + matrix[nr][nc]) {
                    cost[nr][nc] = currentCost + matrix[nr][nc];
                    pq.offer(new Node(nr, nc, cost[nr][nc]));
                }




            }
        }

        return cost[n-1][n-1];
    }

}

