import java.io.*;
import java.util.*;

public class Main {

    static int[][] points;
    static Double tax;
    static Double taxSum;

    static ArrayList<int[]> notVisitedPoints;
    static ArrayList<int[]> visitedPoints;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {

            int n = Integer.parseInt(br.readLine());
            points = new int[n][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                points[j][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                points[j][1] = Integer.parseInt(st.nextToken());
            }
            tax = Double.parseDouble(br.readLine());
            taxSum = 0.0;

            visitedPoints = new ArrayList<>();
            notVisitedPoints = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                notVisitedPoints.add(points[j]);
            }
            prim(notVisitedPoints.get(0), n, tax);
            bw.write("#" + (i+1) + " " + Math.round(taxSum) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();


    }

    static void prim(int[] initPoint, int n, Double tax) {


        visitedPoints.add(new int[]{initPoint[0], initPoint[1]});
        notVisitedPoints.remove(0);
        while (visitedPoints.size() != n) {
            Double minLength = Double.MAX_VALUE;

            int minPointIdx = -1;
            for (int i = 0; i < visitedPoints.size(); i++) {
                int[] current = visitedPoints.get(i);
                for (int j = 0; j < notVisitedPoints.size(); j++) {
                    int[] target = notVisitedPoints.get(j);


                    double length = Math.pow(current[0] - target[0], 2) + Math.pow(current[1] - target[1], 2);

                    if (length < minLength) {
                        for (int k = 0; k < notVisitedPoints.size(); k++) {
                            int[] point = notVisitedPoints.get(k);
                            if (target[0] == point[0] && target[1] == point[1]) {
                                minPointIdx = k;
                                break;
                            }
                        }
                        minLength = length;
                    }
                }
            }
            taxSum += tax * minLength;
            visitedPoints.add(new int[]{notVisitedPoints.get(minPointIdx)[0], notVisitedPoints.get(minPointIdx)[1]});
            notVisitedPoints.remove(minPointIdx);
        }
    }


}

