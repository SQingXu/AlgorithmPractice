/*Q1 Given an array, rotate the array to the right by k 
steps, where k is non-negative. */

class Solution {
    public void rotate(int[] nums, int k) {
        //simple way O(n) space
        // int[] rotated = new int[nums.length];
        // for(int i = 0; i < nums.length; i++){
        //     rotated[(i+k)%nums.length] = nums[i];
        // }
        // for(int i = 0; i < nums.length; i++){
        //     nums[i] = rotated[i];
        // }
        
        //2nd way O(1) space O(n*k) complexity
        // for(int i = 0; i < k; i++){
        //     int temp = nums[nums.length -1];
        //     int temp1 = 0;
        //     for(int j = 0; j < nums.length; j++){
        //         temp1 = nums[j];
        //         nums[j] = temp;
        //         temp = temp1;
        //     }
        // }
        
        //3rd way O(1) space O(n) complexity
        k = k%nums.length;
        int count = 0;
        
        for(int i = 0; count < nums.length; i++){
            int start = i;
            int current_pos = i;
            int temp = nums[current_pos];
            int temp_sub = 0;
            do{
                int next_pos = (current_pos+k)%nums.length;
                temp_sub = nums[next_pos];
                nums[next_pos] = temp;
                temp = temp_sub;
                current_pos = next_pos;
                count++;
            }while(current_pos != start);
        }
        
    }
}

/* Given an array of n positive integers and a positive integer s, 
find the minimal length of a contiguous subarray of which the sum 
≥ s. If there isn't one, return 0 instead. */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum < s){
            return 0;
        }

        int least_len = Integer.MAX_VALUE;
        int run_sum = 0;
        int current_len = 0;
        for(int i = 0; i < nums.length; i++){
            run_sum += nums[i];
            current_len++;
            
            if(run_sum >= s){
                //slide_win = true;
                least_len = Math.min(least_len, current_len);
                while(run_sum - nums[i-current_len+1] >= s){
                    run_sum -= nums[i-current_len+1];
                    current_len--;
                    least_len = Math.min(least_len, current_len);
                }
            }
        }
        return least_len;
        
    }
}

/* Q3 Given a sorted integer array without duplicates, return the summary of its ranges. */
class Solution {
    public List<String> summaryRanges(int[] nums) {
        
        List<String> ret = new ArrayList<>();
        if(nums.length == 0){
            return ret;
        }
        
        int current_len = 1;
        int prev = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] - prev == 1){
                //consecutive
                current_len++;
            }else{
                if(current_len == 1){
                    ret.add(Integer.toString(prev));
                }else{
                    String range = Integer.toString(prev-current_len+1) + "->" + Integer.toString(prev);
                    ret.add(range);
                    current_len = 1;
                }
            }
            prev = nums[i];
        }
        
        if(current_len == 1){
            ret.add(Integer.toString(prev));
        }else{
            String range = Integer.toString(prev-current_len+1) + "->" + Integer.toString(prev);
            ret.add(range);
            current_len = 1;
        }
        
        
        return ret;
    }
    
    
}