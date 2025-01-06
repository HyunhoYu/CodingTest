import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        parent = new int[g + 1];
        int cnt = 0;

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < p; i++) {
            int airPlane = Integer.parseInt(br.readLine());

            int targetGate = find(airPlane);

            if (targetGate == 0) {
                break;
            }

            cnt++;
            union(targetGate, targetGate - 1);

        }

        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
        br.close();

    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);

    }

    static void union(int x, int y) {

        x = find(x);
        y = find(y);

        if (x != y) parent[x] = y;
    }
}
