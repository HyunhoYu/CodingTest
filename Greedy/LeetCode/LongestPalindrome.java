class Solution {
    public int longestPalindrome(String s) {

        Map<Character, Integer> map = new HashMap<>();

        int oddFreqCount = 0;

        int sLen = s.length();

        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(i);

            map.put(target, map.getOrDefault(target, 0) + 1);
            if ((map.get(target) % 2) == 0) oddFreqCount--;
            else oddFreqCount++;
        }

        if (oddFreqCount > 0) return sLen - oddFreqCount + 1;

        return sLen;



    }
}


