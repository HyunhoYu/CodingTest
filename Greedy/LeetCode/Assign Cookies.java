class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int ans = 0;

        for (int i = g.length - 1; i >= 0; i--) {
            for (int j = s.length - 1; j >= 0; j--) {
                if (s[j] >= g[i]) {
                    ans += 1;
                    s[j] = 0;
                    break;
                }
            }
        }

        return ans;
    }
}