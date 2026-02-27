import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();



        int[] dp = new int[N + 3];



        dp[2] = 1;
        dp[3] = 1;



        for (int i = 4; i < N + 1; i++) {

            int min = Integer.MAX_VALUE;

            if (i % 3 == 0) {
                min = Math.min(dp[i / 3] + 1, min);
            }

            if (i % 2 == 0) {
                min = Math.min(dp[i / 2] + 1, min);
            }

            min = Math.min(dp[i - 1] + 1, min);

            dp[i] = min;
        }

        System.out.println(dp[N]);




    }


}