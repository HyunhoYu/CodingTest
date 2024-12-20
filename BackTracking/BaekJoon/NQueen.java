package BackTracking;

import java.io.*;
import java.util.ArrayList;

public class NQueen {


    static ArrayList<ArrayList<Integer>> results;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        results = new ArrayList<>();


        backtrack(n, new ArrayList<Integer>());

        bw.write(results.size() + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void backtrack(int n, ArrayList<Integer> current) {

        if (current.size() == n) {
            results.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(current, i)) {

                current.add(i);
                backtrack(n, current);
                current.remove(current.size() - 1);
            }

        }
    }



    static boolean isValid(ArrayList<Integer> current, int currentCol) {
        for (int i = 0; i < current.size(); i++) {
            if (currentCol == current.get(i)) return false;
            int currentIndex = current.size();

            int rowDiff = currentIndex - i;

            if (Math.abs(currentCol - current.get(i)) == rowDiff) return false;
        }
        return true;
    }


}
