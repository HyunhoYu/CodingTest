class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int max = 0;
        while (right > left) {

            int w = right - left;
            int h = height[left] < height[right] ? height[left] : height[right];

            int result = w * h;

            if (result > max) max = result;

            if(height[left] > height[right]) right--;
            else left++;
        }

        return max;

    }
}