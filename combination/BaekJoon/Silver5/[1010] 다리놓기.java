import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            if (K > N) {
                int tmp = N;
                N = K;
                K = tmp;
            }


            int diff = N - K;

            BigInteger ans = new BigInteger("1");

            for (int j = N; j > diff; j--) {
                ans = ans.multiply(new BigInteger(Integer.toString(j)));
            }

            BigInteger KFac = new BigInteger(Integer.toString(K));

            for (int j = K - 1; j >= 1; j--) {
                KFac = KFac.multiply(new BigInteger(Integer.toString(j)));
            }
            bw.write(ans.divide(KFac) + "\n");



        }
        bw.flush();
        bw.close();
    }



}

