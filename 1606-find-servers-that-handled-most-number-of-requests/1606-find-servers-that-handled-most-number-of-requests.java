import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // PriorityQueue stores [endTime, serverId] sorted by endTime ascending
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // TreeSet tracks available server IDs sorted from 0 to k-1
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        int[] requestsHandled = new int[k];
        int maxLoaded = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            
            // Free up all servers whose load finishes at or before the current arrival time
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                int[] freed = busy.poll();
                available.add(freed[1]);
            }
            
            // If all servers are busy, drop the request
            if (available.isEmpty()) {
                continue;
            }
            
            // Look for the first available server >= (i % k)
            Integer target = available.ceiling(i % k);
            if (target == null) {
                // If none found >= preferred index, wrap around to the smallest available server ID
                target = available.first();
            }
            
            // Assign the request to the found server
            available.remove(target);
            busy.offer(new int[]{currTime + load[i], target});
            requestsHandled[target]++;
            maxLoaded = Math.max(maxLoaded, requestsHandled[target]);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> busiest = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestsHandled[i] == maxLoaded) {
                busiest.add(i);
            }
        }
        
        return busiest;
    }
}
