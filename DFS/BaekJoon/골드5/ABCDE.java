import java.io.*;
import java.util.*;
//
public class Main {

    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();


        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            String[] relation = br.readLine().split(" ");
            int u = Integer.parseInt(relation[0]);
            int v = Integer.parseInt(relation[1]);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, 0);
        }

        System.out.println(0);



    }

    static void dfs(int vertex, int depth) {

        if (depth == 4) {
            System.out.println(1);
            System.exit(0);
        }



        ArrayList<Integer> targetList = graph.get(vertex);

        for (Integer next : targetList) {

            if (visited[next]) continue;
            visited[next] = true;
            dfs(next, depth + 1);
            visited[next] = false;
        }


    }



}

