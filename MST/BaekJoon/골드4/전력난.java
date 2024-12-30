import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{

    int start;
    int end;
    int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

public class Main {


    static ArrayList<Edge> edges;

    static int[] arr;
    static int originalCost;
    static int cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (v == 0 && e == 0) {
                break;
            }

            arr = new int[v];
            originalCost = 0;
            edges = new ArrayList<>();
            cost = 0;


            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                originalCost += weight;
                edges.add(new Edge(start, end, weight));
            }

            Collections.sort(edges);

            for (int i = 0; i < arr.length; i++) {
                arr[i] = i;
            }



            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                int start = edge.start;
                int end = edge.end;
                int weight = edge.weight;

                if (find(start) != find(end)) {
                    union(start, end);
                    cost += weight;
                }
            }
            System.out.println(originalCost - cost);

        }

    }

    static void union(int v1, int v2) {
        int rep = find(v1);
        int sub = find(v2);

        arr[sub] = rep;
    }

    static int find(int index) {

        int value = arr[index];

        if (value == index) {
            return value;
        }

        return arr[index] = find(arr[value]);
    }


}

