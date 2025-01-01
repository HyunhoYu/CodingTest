import java.io.*;
import java.util.*;

class Edge {
    int to;
    int length;

    public Edge(int to, int length) {
        this.to = to;
        this.length = length;
    }
}

public class Main {

    static int[] items;
    static ArrayList<Edge>[] graph;
    static int maxItem = 0;

    static boolean[] visited;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int range = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < items.length; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (length > range) continue;

            graph[v1].add(new Edge(v2, length));
            graph[v2].add(new Edge(v1, length));
        }

        for (int i = 1; i < n + 1; i++) {
            bfs(i, n, range);
        }

        bw.write(maxItem + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    static void bfs(int startVertex, int n, int range) {

        Queue<Edge> queue = new ArrayDeque<>();
        visited = new boolean[n + 1];
        visited[startVertex] = true;

        for (Edge edge : graph[startVertex]) {

            queue.offer(new Edge(edge.to, edge.length));
            visited[edge.to] = true;
        }

        while (!queue.isEmpty()) {

            Edge edge = queue.poll();
            int to = edge.to;
            int length = edge.length;

            for (Edge edge1 : graph[to]) {
                if (length + edge1.length <= range) { //이 조건문에서 visited체크하면 안됨 123 233 134 << 이 경우에서 1에서 3으로 가면 cost가 4인데 1 -> 2 -> 3으로가면 cost가6 이때 range가 5라고 하고 1 -> 2 -> 3 으로 방문하는게 1 -> 3으로 방문하는거보다 선행된다면 사실 3으로 방문이 가능한데 방문하지 않음
                    queue.offer(new Edge(edge1.to, length + edge1.length));
                    visited[edge1.to] = true;
                }
            }
        }

        int tmp = 0;
        for (int i = 1; i < n + 1; i++) {
            if (visited[i]) {
                tmp += items[i];
            }
        }

        if (tmp > maxItem) maxItem = tmp;

    }

}

