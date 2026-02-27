package BackTracking;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MakePassword {

    static String[] line;
    static ArrayList<ArrayList<String>> results;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        String[] nr = br.readLine().split(" ");
        int n = Integer.parseInt(nr[0]);
        int r = Integer.parseInt(nr[1]);

        line = br.readLine().split(" ");
        results = new ArrayList<>();

        Arrays.sort(line);

        backtrack(n, r, 0, new ArrayList<String>());


        for (ArrayList<String> result : results) {
            for (int i = 0; i < result.size(); i++) {
                System.out.printf("%s", result.get(i));
            }
            System.out.println();
        }

        br.close();

    }

    static void backtrack(int n, int r, int index, ArrayList<String>tmp) {

        if (tmp.size() == n) {
            int vowelCount = 0;
            int consonantCount = 0;
            for (String s : tmp) {
                if (s.equals("a") || s.equals("i") || s.equals("e") || s.equals("o") || s.equals("u")) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
            if (vowelCount >= 1 && consonantCount >= 2) {
                results.add(new ArrayList<>(tmp));
            }
            return;
        }

        for (int i = index; i < r; i++) {
            tmp.add(line[i]);
            backtrack(n, r, i + 1, tmp);
            tmp.remove(line[i]);
        }
    }


}
