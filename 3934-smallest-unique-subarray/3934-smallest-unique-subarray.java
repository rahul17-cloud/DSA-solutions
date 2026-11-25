import java.util.Arrays;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] sa = buildSuffixArray(nums);
        int[] lcp = buildLCP(nums, sa);

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int ans = Integer.MAX_VALUE;
        for (int idx = 0; idx < n; idx++) {
            int r = rank[idx];
            int maxCommon = 0;
            if (r > 0) {
                maxCommon = Math.max(maxCommon, lcp[r - 1]);
            }
            if (r + 1 < n) {
                maxCommon = Math.max(maxCommon, lcp[r]);
            }

            int candidateLen = maxCommon + 1;
            if (idx + candidateLen <= n) {
                ans = Math.min(ans, candidateLen);
            }
        }

        return ans == Integer.MAX_VALUE ? n : ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        Integer[] order = new Integer[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            order[i] = i;
            rank[i] = nums[i];
        }

        for (int k = 1; k < n; k <<= 1) {
            final int step = k;
            final int[] currRank = rank;
            Arrays.sort(order, (a, b) -> {
                if (currRank[a] != currRank[b]) {
                    return Integer.compare(currRank[a], currRank[b]);
                }
                int ra = (a + step < n) ? currRank[a + step] : -1;
                int rb = (b + step < n) ? currRank[b + step] : -1;
                return Integer.compare(ra, rb);
            });

            int[] nextRank = new int[n];
            nextRank[order[0]] = 0;
            for (int i = 1; i < n; i++) {
                int prev = order[i - 1];
                int curr = order[i];
                int prevSecond = (prev + step < n) ? currRank[prev + step] : -1;
                int currSecond = (curr + step < n) ? currRank[curr + step] : -1;

                boolean samePair = (currRank[prev] == currRank[curr]) && (prevSecond == currSecond);
                nextRank[curr] = nextRank[prev] + (samePair ? 0 : 1);
            }
            rank = nextRank;

            if (rank[order[n - 1]] == n - 1) {
                break;
            }
        }

        int[] sa = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
        }
        return sa;
    }

    private int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;
        if (n <= 1) {
            return new int[0];
        }

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n - 1];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] == n - 1) {
                h = 0;
                continue;
            }
            int j = sa[rank[i] + 1];
            while (i + h < n && j + h < n && nums[i + h] == nums[j + h]) {
                h++;
            }
            lcp[rank[i]] = h;
            if (h > 0) {
                h--;
            }
        }
        return lcp;
    }
}
