class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();

        dp.add(new ArrayList<Integer>());
        dp.get(0).add(1);

        if (numRows == 1) return dp;

        dp.add(new ArrayList<Integer>());
        dp.get(1).add(1);
        dp.get(1).add(1);

        if (numRows == 2) return dp;

        for (int i = 2; i < numRows; i++) {
            List<Integer> currentRow = dp.get(i - 1);
            List nextRow = new ArrayList<Integer>();
            nextRow.add(1);
            for (int j = 1; j < currentRow.size(); j++) {
                nextRow.add(currentRow.get(j - 1) + currentRow.get(j));
            }
            nextRow.add(1);
            dp.add(nextRow);
        }

        return dp;

    }
}