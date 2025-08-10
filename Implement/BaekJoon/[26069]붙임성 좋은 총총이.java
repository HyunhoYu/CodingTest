import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Main {
    static Map<String, String> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new HashMap<>();
        map.put("ChongChong", "ChongChong");

        int ans = 1;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String A = st.nextToken();
            String B = st.nextToken();

            if (method(A, B) == 1) {
                map.put(A, A);
                map.put(B, B);
                ans++;
            }
        }

        System.out.println(ans);
    }


    static int method(String A, String B) {
        int result = 0;

        if (map.containsKey(A)) result++;
        if (map.containsKey(B)) result++;

        return result;
    }

}

