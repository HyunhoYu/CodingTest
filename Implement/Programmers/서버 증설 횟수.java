class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int server = 0;
        int[] arr = new int[24];

        for (int i = 0; i < 24; i++) {

            if (i >= k) server -= arr[i - k];

            int user = players[i];
            int d = user - (server * m);
            if (d >= m) {
                answer += d / m;
                server += d / m;
                arr[i] = d / m;
            }
        }

        return answer;
    }
}

