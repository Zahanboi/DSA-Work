import java.util.*;

class Heap{
    private static ArrayList<Integer> minHeap = new ArrayList<>(); // can make private so only this class can access it
    private static ArrayList<Integer> maxHeap = new ArrayList<>();

    int peek(String type) {
        if (type.equals("min")) {
            return minHeap.isEmpty() ? null : minHeap.get(0);
        } else {
            return maxHeap.isEmpty() ? null : maxHeap.get(0);
        }
    }
    
    public List<Integer> insertIntoHeap(int value, String type) {
        // Implementation for inserting into heap for both minHeap and maxHeap
        if (type.equals("min")) {
            minHeap.add(value);
            heapifyUp(minHeap.size() - 1, "min");
            // Perform heapify up for minHeap
        } else {
            maxHeap.add(value);
            heapifyUp(maxHeap.size() - 1, "max");
            // Perform heapify up for maxHeap
        }
        return null;
    }

public List<Integer> deleteFromHeap(String type) { // would need heapify down after deletion becuase we need to look down of the tree compare the parent to its children and normal heapify up function for insertion because then we are adding a element below comparing childern to its parent and swapping if needed
        if (type.equals("min")) {
            if (minHeap.isEmpty()) return null;
            
            // Remove the last element
            int lastElement = minHeap.remove(minHeap.size() - 1);
            
            // If the heap isn't empty after removal, move the last element to the root and sink it down
            if (!minHeap.isEmpty()) {
                minHeap.set(0, lastElement);
                heapifyDown(0, "min");
            }
            
        } else {
            if (maxHeap.isEmpty()) return null;
            
            int lastElement = maxHeap.remove(maxHeap.size() - 1);
            
            if (!maxHeap.isEmpty()) {
                maxHeap.set(0, lastElement);
                heapifyDown(0, "max");
            }
        }
        return null;
    }

    public void heapifyUp(int index, String type) {
        if (type.equals("min")) {
            int parentIdx = (index - 1) / 2;
            if (index > 0 && minHeap.get(index) < minHeap.get(parentIdx)) {
                Collections.swap(minHeap, index, parentIdx);
                heapifyUp(parentIdx, "min");
            }
            // Heapify logic for minHeap
        } else {
            int parentIdx = (index - 1) / 2;
            if (index > 0 && maxHeap.get(index) > maxHeap.get(parentIdx)) {
                Collections.swap(maxHeap, index, parentIdx);
                heapifyUp(parentIdx, "max");
            }
            // Heapify logic for maxHeap
        }
        
    }

    public void heapifyDown(int index, String type) {
        if (type.equals("min")) {// for min heap we want to compare the parent with its children and swap if the parent is greater than its children
            int leftChildIdx = 2 * index + 1;
            int rightChildIdx = 2 * index + 2;
            int smallestIdx = index;

            if (leftChildIdx < minHeap.size() && minHeap.get(leftChildIdx) < minHeap.get(smallestIdx)) {
                smallestIdx = leftChildIdx;
            }
            if (rightChildIdx < minHeap.size() && minHeap.get(rightChildIdx) < minHeap.get(smallestIdx)) {
                smallestIdx = rightChildIdx;
            }
            if (smallestIdx != index) {
                Collections.swap(minHeap, index, smallestIdx);
                heapifyDown(smallestIdx, "min");
            }
        } else {
            int leftChildIdx = 2 * index + 1;
            int rightChildIdx = 2 * index + 2;
            int largestIdx = index;

            if (leftChildIdx < maxHeap.size() && maxHeap.get(leftChildIdx) > maxHeap.get(largestIdx)) {
                largestIdx = leftChildIdx;
            }
            if (rightChildIdx < maxHeap.size() && maxHeap.get(rightChildIdx) > maxHeap.get(largestIdx)) {
                largestIdx = rightChildIdx;
            }
            if (largestIdx != index) {
                Collections.swap(maxHeap, index, largestIdx);
                heapifyDown(largestIdx, "max");
            }
        }
    }



    public int[] heapSort(int[] arr, String type) {
        // Implementation for heap sort
        if (type.equals("min")) {
            for (int num : arr) {
                insertIntoHeap(num, "min");
            }
        } else {
            for (int num : arr) {
                insertIntoHeap(num, "max");
            }
        }
        return null;
    }

    public String[] nearbyCars(int[][] points, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0]*a[0] + a[1]*a[1]) - (b[0]*b[0] + b[1]*b[1]));
        for (int[] point : points) {
            minHeap.offer(point);
        }
        String[] result = new String[k];
        for(int i = 0; i < k; i++) {
            int[] car = minHeap.poll();
            result[i] = "(" + car[0] + "," + car[1] + ")";
        }
        return result;
        //T.C = O(n log n) for inserting all points into the heap and O(k log n) for extracting k nearest cars, so overall O(n log n + k log n) worst case will be 2nlong n which is O(n log n)
    }

    public int connectNRopes(int[] ropes){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int rope : ropes) {
            minHeap.offer(rope);
        }
        int totalCost = 0;
        while (minHeap.size() > 1) {
            int firstRope = minHeap.poll();
            int secondRope = minHeap.poll();
            int cost = firstRope + secondRope;
            totalCost += cost;
            minHeap.offer(cost);
        }
        return totalCost;
        //tc - nlogn
    }

    public int[] weakestSoldier(int[][] mat, int k){
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int i = 0;
        for(int[] row : mat){
            int soldiers = 0;
            for(int val : row){
                if(val == 1) {
                    soldiers++;
                } else {
                    break;// bcoz soldiers are in front
                }
            }
            minHeap.offer(new int[]{soldiers, i});
            i++;
        }
        int[] result = new int[k];
        for(int j = 0; j < k; j++) {
            result[j] = minHeap.poll()[1];
        }
        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int i = 0;
        int j = i + k-1;
        ArrayList<Integer> al = new ArrayList();
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
         int z  = i;
        while(z<=j){
                maxHeap.offer(new int[]{nums[z], z});
                z++;
            }
            al.add(maxHeap.peek()[0]);
            i++;
            j++;

        while(j<=nums.length-1){
            maxHeap.offer(new int[]{nums[j], j});
            while(maxHeap.peek()[1] < i) maxHeap.poll();
            
            al.add(maxHeap.peek()[0]);
            i++;
            j++;
            
        }

        int arr[] = new int[al.size()];
        for(int y = 0; y<al.size(); y++){
            arr[y] = al.get(y);
        }

        return arr;
        //T.C = O(n log k) where n is the number of elements in the input array and k is the size of the sliding window. Each element is added to and removed from the heap at most once, leading to log k operations for each of the n elements.
    }

    public int[] Kthlargestelementinastream(int[] stream, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();// tranverse until k size and if k+1 wala element is greater than the minHeap.peek() then we remove the peek and add the new element to the heap because ussnew stream me ek sbse chhota mil gya menaing kth largest element jo k number pe aakr largst hai 
        for(int num : stream){
            if(minHeap.size() < k){
                minHeap.offer(num);
            } else if(num > minHeap.peek()){
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        int[] result = new int[minHeap.size()];
        int i = 0;
        while(!minHeap.isEmpty()){
            result[i++] = minHeap.poll();
        }
        return result;
    }

    public int pathWithMinimumEffort(int[][] matrix) {
        int ans = 0;
        //initialize a 2d array with all dist to infinity
        int twoD[][] = new int[matrix.length][matrix[0].length];
        for(int row[] : twoD) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        //use a pq to store the cells with their effort and coordinates 
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{matrix[0][0], 0, 0}); 
        //dijkstra's algorithm to find the minimum effort path
        twoD[0][0] = 0;
        
        //look in all 4 directions and update the dist if the new effort is less than the current dist
        return ans;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(); // instantiate the Heap class
        // heap.insertIntoHeap(5, "min");
        // heap.insertIntoHeap(3, "min");
        // heap.insertIntoHeap(8, "min");
        // heap.insertIntoHeap(1, "min");
        // System.out.println(heap.peek("min")); 
        int arr[] = {5, 3, 8, 1};
        heap.heapSort(arr, "min");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        //after using heap sort
        System.out.println();
        for(int i = 0; i < arr.length; i++) {
            System.out.print(heap.peek("min")); 
            heap.deleteFromHeap("min");
            // System.out.print(minHeap.get(i) + " ");
        }
        System.out.println();
        int[][] points = {{1, 2}, {3, 4}, {1, -1}};
        String[] nearby = heap.nearbyCars(points, 2);
        for (String car : nearby) {
            System.out.println(car);
        }

        int[] ropes = {4, 3, 2, 6};
        int cost = heap.connectNRopes(ropes);
        System.out.println("Total cost to connect ropes: " + cost);

        int[][] soldiers = {{1,0,0,0},{1,1,1,1},{1,0,0,0},{1,0,0,0}};
        int[] weakest = heap.weakestSoldier(soldiers, 2);
        System.out.println("Indices of weakest soldiers:");
        for (int idx : weakest) {
            System.out.print(idx + " ");
        }

        int[] slidingWindow = heap.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println("\nMaximum in each sliding window:");
        for (int val : slidingWindow) {
            System.out.print(val + " ");
        }

        int[] kthLargest = heap.Kthlargestelementinastream(new int[]{3, 1, 5, 12, 2, 11}, 3);
        System.out.println("\nKth largest elements in the stream:");
        for (int val : kthLargest) {
            System.out.print(val + " ");
        }

        int[][] matrix = {{31, 100, 65, 12, 18 },{ 10, 13, 47, 157, 6 },{ 100, 113, 174, 11, 33 },{ 88, 124, 41, 20, 140 },{ 99, 32, 111, 41, 20}};
        int minEffort = heap.pathWithMinimumEffort(matrix);
        System.out.println("\nMinimum effort to traverse the matrix: " + minEffort);
    }
}