import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = br.readLine()) != null) {


            if (line.isEmpty()) break;
            int N = Integer.parseInt(line);

            int iter = 1;

            if (N == 0) {
                iter = 1;
            } else {
                for (int i = 0; i < N; i++) {
                    iter *= 3;
                }
            }


            char[] a = new char[iter];
            Arrays.fill(a, '-');

            solution(a);
            bw.write("\n");

        }

        bw.flush();
        bw.close();
    }

    static void solution(char[] a) throws IOException {
        int length = a.length;

        if ((length == 1 && a[0] == '-') || (a[0] == ' ')) {
            bw.write(a);
            return;
        }

        int d = length / 3;



        char[] la = new char[d];
        char[] ma = new char[d];
        char[] ra = new char[d];

        Arrays.fill(la, '-');
        Arrays.fill(ma, ' ');
        Arrays.fill(ra, '-');

        solution(la);
        solution(ma);
        solution(ra);

    }


}