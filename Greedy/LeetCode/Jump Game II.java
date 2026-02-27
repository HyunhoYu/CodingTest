class Solution {
    public int jump(int[] nums) {

        int near = 0;
        int far = 0;
        int jumps = 0;

        while (far < nums.length - 1) {

            int mostFar = 0;
            for (int i = near; i <= far; i++) {
                mostFar = Math.max(mostFar, nums[i] + i);
            }

            near = far + 1;
            far = mostFar;

            jumps++;
        }
        return jumps;
    }
}