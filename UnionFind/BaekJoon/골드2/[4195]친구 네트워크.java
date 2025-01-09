import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {



    static HashMap<String, String> parentTable;
    static HashMap<String, Integer> nodeCountTable;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());

            parentTable = new HashMap<>();
            nodeCountTable = new HashMap<>();

            for (int j = 0; j < r; j++) {

                st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                if (!parentTable.containsKey(a)) {
                    parentTable.put(a, a);
                    nodeCountTable.put(a, 1);
                }

                if (!parentTable.containsKey(b)) {
                    parentTable.put(b, b);
                    nodeCountTable.put(b, 1);
                }

                String root = find(a);
                String leaf = find(b);

                if (!root.equals(leaf)) {
                    nodeCountTable.put(root, nodeCountTable.get(root) + nodeCountTable.get(leaf));
                    union(a, b);
                }


                bw.write(nodeCountTable.get(root) + "\n");


            }


        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void union(String x, String y) {

        x = find(x);
        y = find(y);


        parentTable.put(y, x);

    }

    static String find(String x) {

        if (parentTable.get(x).equals(x)) {
            return x;
        }

        parentTable.put(x, find(parentTable.get(x)));

        return parentTable.get(x);
    }


}
