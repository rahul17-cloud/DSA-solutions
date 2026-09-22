class Solution {
    static class Node {
        int prod;
        int[] remain;

        public Node(int k) {
            this.remain = new int[k];
            this.prod = 1;
        }
    }

    private int n;
    private int k;
    private Node[] tree;
    private int[] currentNums;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;
        this.currentNums = new int[n];
        for (int i = 0; i < n; i++) {
            this.currentNums[i] = nums[i] % k;
        }
        
        this.tree = new Node[4 * n];
        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node(k);
        }
        
        build(0, 0, n - 1);
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1] % k;
            int start = queries[i][2];
            int x = queries[i][3];
            
            update(0, 0, n - 1, index, value);
            
            if (start > n - 1) {
                result[i] = 0;
            } else {
                Node res = query(0, 0, n - 1, start, n - 1);
                result[i] = res.remain[x];
            }
        }
        
        return result;
    }

    private Node merge(Node left, Node right) {
        Node parent = new Node(k);
        parent.prod = (left.prod * right.prod) % k;
        
        // Suffix matches entirely within the left child segment
        for (int i = 0; i < k; ++i) {
            parent.remain[i] = left.remain[i];
        }
        
        // Suffix branches out into the right child segment
        for (int i = 0; i < k; ++i) {
            parent.remain[(i * left.prod) % k] += right.remain[i];
        }
        
        return parent;
    }

    private void build(int cur, int left, int right) {
        if (left == right) {
            tree[cur].remain[currentNums[left]] = 1;
            tree[cur].prod = currentNums[left];
            return;
        }
        int mid = left + (right - left) / 2;
        build(2 * cur + 1, left, mid);
        build(2 * cur + 2, mid + 1, right);
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private void update(int cur, int lo, int hi, int idx, int val) {
        if (lo == hi) {
            for (int j = 0; j < k; ++j) {
                tree[cur].remain[j] = 0;
            }
            tree[cur].remain[val] = 1;
            tree[cur].prod = val;
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (idx <= mid) {
            update(2 * cur + 1, lo, mid, idx, val);
        } else {
            update(2 * cur + 2, mid + 1, hi, idx, val);
        }
        tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
    }

    private Node query(int cur, int lo, int hi, int l, int r) {
        if (l <= lo && hi <= r) {
            return tree[cur];
        }
        int mid = lo + (hi - lo) / 2;
        if (r <= mid) {
            return query(2 * cur + 1, lo, mid, l, r);
        } else if (l > mid) {
            return query(2 * cur + 2, mid + 1, hi, l, r);
        } else {
            return merge(query(2 * cur + 1, lo, mid, l, mid), query(2 * cur + 2, mid + 1, hi, mid + 1, r));
        }
    }
}