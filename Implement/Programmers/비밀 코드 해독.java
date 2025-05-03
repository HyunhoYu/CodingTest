import java.util.*;
class Solution {

    int answer;
    List<Integer> list = new ArrayList<>();
    int[][] Q;
    int[] ANS;
    int N;

    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        Q = q;
        ANS = ans;
        N = n;

        backtrack(1);

        return answer;

    }

    private void backtrack(int idx) {

        if (list.size() == 5) {
            check(list);
            return;
        }

        for (int i = idx; i <= N; i++) {
            list.add(i);
            backtrack(i + 1);
            list.remove(list.size() - 1);
        }
    }

    private void check(List<Integer> list) {

        for (int i = 0; i < ANS.length; i++) {

            int cnt = 0;
            int[] target = Q[i];

            for (int j = 0; j < list.size(); j++) {
                int cur = list.get(j);

                for (int k = 0; k < 5; k++) {
                    if (cur == target[k]) {
                        cnt++;
                        break;
                    }
                }
            }

            if (cnt != ANS[i]) return;

        }

        answer++;
    }





}