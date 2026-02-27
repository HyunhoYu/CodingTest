import java.io.*;
import java.time.Year;
import java.util.*;

public class Main {

    static int[] parent;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] parties;
    static int[] problemPeople;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        graph = new ArrayList[n + 1];
        parties = new ArrayList[m];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }


        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        problemPeople = new int[Integer.parseInt(st.nextToken())];

        for (int i = 0; i < problemPeople.length; i++) {
            problemPeople[i] = Integer.parseInt(st.nextToken());
        }

        for (int problemPerson : problemPeople) {
            union(problemPerson, 0);
        }

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            int it = Integer.parseInt(st.nextToken());

            ArrayList<Integer> tmpList = new ArrayList<>();
            parties[i] = new ArrayList<>();
            for (int j = 0; j < it; j++) {
                tmpList.add(Integer.parseInt(st.nextToken()));
                parties[i].add(tmpList.get(tmpList.size() - 1));
            }

            for (int j = 0; j < tmpList.size(); j++) {
                Integer from = tmpList.get(j);
                ArrayList<Integer> toList = graph[from];
                for (int k = 0; k < tmpList.size(); k++) {
                    if (j == k) continue;
                    Integer to = tmpList.get(k);

                    if (!toList.contains(to)) {
                        toList.add(to);
                    }
                }
            }
        }


        bfs(problemPeople, n);

        int ans = m;

        for (int i = 0; i < parties.length; i++) {
            for (Integer partyMember : parties[i]) {
                if (find(partyMember) == 0) {
                    ans--;
                    break;
                }
            }
        }

        System.out.println(ans);


    }

    static void bfs(int[] problemPeople, int n) {

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        for (int problemPerson : problemPeople) {
            queue.offer(problemPerson);
            visited[problemPerson] = true;
        }

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            ArrayList<Integer> nextList = graph[current];
            for (Integer next : nextList) {
                union(next, current);

                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        parent[x] = y;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
