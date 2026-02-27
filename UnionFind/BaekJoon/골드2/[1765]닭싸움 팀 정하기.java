import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] enemyGraph;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        enemyGraph = new ArrayList[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n + 1; i++) {
            enemyGraph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        int it = Integer.parseInt(st.nextToken());

        for (int i = 0; i < it; i++) {

            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if (cmd.equals("E")) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                enemyGraph[u].add(v);
                enemyGraph[v].add(u);
            } else {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                union(x, y);
            }
        }

        for (int i = 1; i < enemyGraph.length; i++) {
            ArrayList<Integer> friends = enemyGraph[i];

            if (friends.size() > 1) {
                int head = friends.get(0);

                for (int j = 1; j < friends.size(); j++) {
                    union(head, friends.get(j));
                }
            }
        }


        int cnt = 0;


        for (int i = 1; i < parent.length; i++) {
            if (i == parent[i]) {
                cnt++;
            }
        }


//        for (int i = 1; i < parent.length; i++) {
//            System.out.printf("%d ", parent[i]);
//        }

        System.out.println(cnt);


    }

    static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    static int find(int x) {

        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

}
