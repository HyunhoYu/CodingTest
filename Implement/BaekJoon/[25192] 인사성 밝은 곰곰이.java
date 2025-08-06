import java.io.*;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (true) {
            T--;
            String str = br.readLine();
            if (str.equals("ENTER")) break;
        }

        HashSet<String> set = new HashSet<>();

        int ans = 0;

        for (int i = 0; i < T; i++) {
            String str = br.readLine();

            if (str.equals("ENTER")) {
                ans += set.size();
                set = new HashSet<>();
                continue;
            }

            set.add(str);
        }

        ans += set.size();
        System.out.println(ans);
    }

}

