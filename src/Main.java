import java.io.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static int ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);


        ans = 0;


        for (int i = 0; i < N; i++) {

            int l = 0;
            int r = N-1;

            while (l < r) {
                if (l == i) {
                    l++;
                    continue;
                }

                if (r == i) {
                    r--;
                    continue;
                }

                int sum = arr[l] + arr[r];

                if (sum == arr[i]) {
                    ans++;
                    break;
                }

                if (sum < arr[i]) {
                    l++;
                } else if (sum > arr[i]) {
                    r--;
                }

            }

        }

        System.out.print(ans);

    }


}