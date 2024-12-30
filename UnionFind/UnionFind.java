import java.io.*;
import java.util.*;

public class Main {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/Users/hyunho/downloads/q50_02.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int it = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < it; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(v1, v2);
            } else {
                if (find(v1) == find(v2)) {
                    System.out.println("Yes");
                } else{
                    System.out.println("No");
                }
            }
        }


    }

    static void union(int v1, int v2) {
        int rep = find(v1);
        int sub = find(v2);
        arr[sub] = rep;
    }

    static int find(int index) {

        int value = arr[index];

        if (index == value) {
            return value;
        }

        int rep = find(value);

        arr[index] = rep;

        return rep;
    }


}

