import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int length = Integer.parseInt(br.readLine());

        int min = Integer.MAX_VALUE;
        int max = -1;


        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < length; i++) {
            int num = Integer.parseInt(st.nextToken());

            min = num < min ? num : min;
            max = num > max ? num : max;
        }

        System.out.println(min * max);


    }

}

