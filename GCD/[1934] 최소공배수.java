import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));




        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int gcd = findGCD(A, B);

            bw.write(A * B / gcd + "\n");


        }

        bw.flush();
        bw.close();
    }

    static int findGCD(int A, int B) {
        if (A < B) {
            int tmp = A;
            A = B;
            B = tmp;
        }

        if (B == 0) return A;
        return findGCD(B, A % B);
    }




}