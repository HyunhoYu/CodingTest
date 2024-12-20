package BackTracking;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class chickenDelivery {
    static ArrayList<ArrayList<Integer>> housePoint;
    static ArrayList<ArrayList<Integer>> chickenPoint;

    static ArrayList<Integer> results;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        housePoint = new ArrayList<>();
        chickenPoint = new ArrayList<>();
        results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] strArr = br.readLine().split(" ");
            int[] inputArr = Arrays.stream(strArr)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < inputArr.length; j++) {
                if (inputArr[j] == 1) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    housePoint.add(tmp);
                } else if (inputArr[j] == 2) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    chickenPoint.add(tmp);
                }
            }
        }

        search(m);

        bw.write(Collections.min(results) + "\n");
        bw.close();
        br.close();
    }

    static void search(int m) {
        ArrayList<ArrayList<Integer>> memory = new ArrayList<>();
        backtrack(memory, m, 0);


    }

    static void backtrack(ArrayList<ArrayList<Integer>> memory, int m, int index) {

        if (memory.size() == m) {

            int total = 0;

            for (int i = 0; i < housePoint.size(); i++) {
                int minimum = Integer.MAX_VALUE;
                for (int j = 0; j < memory.size(); j++) {
                    int tmp = Math.abs(housePoint.get(i).get(0) - memory.get(j).get(0)) + Math.abs(housePoint.get(i).get(1) - memory.get(j).get(1));
                    minimum = minimum < tmp ? minimum : tmp;
                }
                total += minimum;
            }
            results.add(total);
            return;
        }

        for (int i = index; i < chickenPoint.size(); i++) {
            ArrayList<Integer> targetPoint = chickenPoint.get(i);
            memory.add(targetPoint);
            backtrack(memory, m, i + 1);
            memory.remove(targetPoint);
        }
    }
}
