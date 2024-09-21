//Time complexity: O(n)
//Space complexity: O(n)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length==0 || k==0)
        {
            return new int[]{};
        }
        int max=0;
        HashMap<Integer, Integer> map= new HashMap<>();
        for(int i=0; i<nums.length; i++)
        {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
            max= Math.max(max, map.get(nums[i]));
        }
        List[] l= new List[max+1];
        for(int key: map.keySet())
        {
            int index=map.get(key);
            if(l[index]==null)
            {
                l[index]= new ArrayList<>();
            }
            l[index].add(key);
        }
        int[] res= new int[k];
        for(int i=max; i>=0 && k>0; i--)
        {
            if(l[i]==null)
            {
            continue;
            }
            List<Integer>mylist = l[i];
            for(int j=0; j<l[i].size() && k>0; j++)
            {
                res[k-1]= mylist.get(j);
                k--;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        // Test case
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        Solution s= new Solution();
        int[] result = s.topKFrequent(nums, k);

        // Printing the result
        System.out.println(Arrays.toString(result));  // Expected output: [1, 2]
    }
}
