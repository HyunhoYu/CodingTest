import java.io.*;
import java.util.*;


class Node implements Comparable<Node>{

    int vertex;
    int dependency;

    public Node(int vertex, int dependency) {
        this.vertex = vertex;
        this.dependency = dependency;
    }

    @Override
    public int compareTo(Node o) {
        return this.vertex - o.vertex;
    }
}


public class Main {

    static ArrayList<Integer>[] graph;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        inDegree = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            inDegree[v]++;
        }

        ArrayList<Node> ans = bfs();

        Collections.sort(ans);

        for (Node node : ans) {

            bw.write(node.dependency + " ");
        }



        bw.flush();
        bw.close();
        br.close();

    }

    static ArrayList<Node> bfs() {
        ArrayList<Node> ans = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.offer(new Node(i, 1));
        }

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            ans.add(current);

            ArrayList<Integer> nexts = graph[current.vertex];

            for (Integer next : nexts) {
                inDegree[next]--;
                if (inDegree[next] == 0) queue.offer(new Node(next, current.dependency + 1));
            }

        }

        return ans;
    }


}
