import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        // Base element to handle division cleanly
        prefixProducts.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            // Reset the list because any product including 0 will be 0
            prefixProducts.clear();
            prefixProducts.add(1);
        } else {
            int lastProduct = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        }
    }

    public int getProduct(int k) {
        int n = prefixProducts.size();
        // If k is >= current valid elements (excluding the initial 1),
        // it means a 0 occurred within the last k elements.
        if (k >= n) {
            return 0;
        }
        return prefixProducts.get(n - 1) / prefixProducts.get(n - 1 - k);
    }
}
