import java.io.*;
import java.time.Year;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] graph;
    static int[] path;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {

            st = new StringTokenizer(br.readLine());
            int from = i;
            ArrayList<Integer> toList = graph[from];
            for (int j = 1; j <= n; j++) {

                if (Integer.parseInt(st.nextToken()) == 1) {
                    toList.add(j);
                }
            }
        }

        path = new int[m];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < path.length; i++) {
            path[i] = Integer.parseInt(st.nextToken());
        }

        bfs(n);

        boolean flag = true;

        for (int i = 1; i < path.length; i++) {

            if (path[0] != find(path[i])) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
            return;
        }

        System.out.println("NO");


    }

    static void bfs(int n) {

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(path[0]);
        visited[path[0]] = true;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            ArrayList<Integer> nexts = graph[current];

            for (Integer next : nexts) {

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    union(next, current);
                }
            }
        }


    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }

    static int find(int x) {

        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
