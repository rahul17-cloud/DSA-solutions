class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Convert inputs to long to prevent integer overflow during multiplication
        long c1 = cost1;
        long c2 = cost2;
        long cBoth = costBoth;
        long n1 = need1;
        long n2 = need2;

        // Option 1: Buy all requirements individually
        long allIndividual = n1 * c1 + n2 * c2;

        // Option 2: Fulfill the overlapping needs using type 3 items, 
        // and buy the remaining difference individually
        long overlap = Math.min(n1, n2);
        long rem1 = n1 - overlap;
        long rem2 = n2 - overlap;
        long mixed = overlap * cBoth + rem1 * c1 + rem2 * c2;

        // Option 3: Fulfill the maximum need entirely with type 3 items
        // (Useful when costBoth is cheaper than even a single individual item)
        long allBoth = Math.max(n1, n2) * cBoth;

        // Return the minimum of the three strategies
        return Math.min(allIndividual, Math.min(mixed, allBoth));
    }
}
