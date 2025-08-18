import java.io.*;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static int count;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        count = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        list = new ArrayList<>();


        int N = Integer.parseInt(br.readLine());

        hanoiTower(N, 1, 2, 3);


        bw.write(count + "\n");

        for (int[] ints : list) {
            bw.write(ints[0] + " " + ints[1] + "\n");
        }

        bw.flush();
        bw.close();


    }


    static void hanoiTower(int N, int start, int mid, int to) {
        if (N == 1) {
            countUp(start, to);
            return;
        }

        hanoiTower(N - 1, start, to, mid);
        countUp(start, to);
        hanoiTower(N - 1, mid, start, to);
    }



    static void countUp(int a, int b) {
        list.add(new int[]{a, b});
        count++;
    }









}