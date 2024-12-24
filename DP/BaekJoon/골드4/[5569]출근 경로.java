package DP;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//
public class CommutingPath {

    static final int MOD = 100000;



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());


        int[][][][] dp = new int[x + 1][y + 1][2][2];

        for (int i = 1; i <= x; i++) {
            dp[i][1][0][0] = 1;
        }

        for (int i = 0; i <= y; i++) {
            dp[1][i][1][0] = 1;
        }

        for (int i = 2; i <= x; i++) {
            for (int j = 2; j <= y; j++) {
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % MOD;
                dp[i][j][0][1] = dp[i - 1][j][1][0] % MOD;

                dp[i][j][1][0] = (dp[i][j - 1][1][0] + dp[i][j - 1][1][1]) % MOD;
                dp[i][j][1][1] = dp[i][j - 1][0][0] % MOD;
            }
        }

        int result = (((dp[x][y][0][0] + dp[x][y][0][1]) % MOD)
                + ((dp[x][y][1][0] + dp[x][y][1][1]) % MOD)) % MOD;

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();


    }


}
