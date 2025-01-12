import java.io.*;
import java.util.*;

public class Main {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(x, y);
            } else {
                if (isSameSet(x, y)) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();


    }


    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (rank[x] == rank[y]) rank[x]++;

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    static int find(int x) {

        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    static boolean isSameSet(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return true;
        } else {
            return false;
        }
    }

}
