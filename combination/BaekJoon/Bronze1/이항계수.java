import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (K == 0) {
            System.out.println(1);
            return;
        }


        int diff = N - K;

        int ans = 1;
        for (int i = N; i > diff; i--) {
            ans *= i;
        }

        int kFac = K;

        for (int i = K - 1; i >= 1; i--) {
            kFac *= i;
        }

        System.out.println(ans / kFac);
    }



}

