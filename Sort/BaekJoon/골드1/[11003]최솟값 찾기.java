import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();


        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());


        int[] dequeIdx = new int[N];
        int[] dequeVal = new int[N];

        int l = 0;
        int r = -1;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int val = Integer.parseInt(st.nextToken());

            while (l <= r && dequeVal[r] >= val) {
                r--;
            }

            r++;

            dequeIdx[r] = i;
            dequeVal[r] = val;

            if (dequeIdx[l] < i - L + 1) l++;

            sb.append(dequeVal[l] + " ");

        }

        System.out.println(sb);




    }


}