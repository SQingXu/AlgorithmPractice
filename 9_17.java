/* Given a non-empty array of integers, every element appears twice except for one. Find that single one. */
class Solution {
    public int singleNumber(int[] nums) {
        //bit manipulation
        int res = 0;
        for(int i = 0 ; i < nums.length; i++){
            res = res ^ nums[i];
        }
        return res; 
    }
}

/* Given a linked list, return the node where the cycle begins. If there is no cycle, return null. */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        //detect for if there is a cycle
        ListNode node1 = head;
        ListNode node2 = head;
        while(node1 != null){
            node1 = node1.next;
            if(node1 == null){
                return null;
            }
            node1 = node1.next;
            node2 = node2.next;
            if(node1 == node2){
                break;
            }
        }
        if(node1 == null){
            return null; // no cycle
        }
        node2 = head;
        while(node1 != node2){
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }
}

/* There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int[] running_gas = new int[gas.length];
        int sum = 0;
        for(int i = 0; i < gas.length; i++){
            running_gas[i] = gas[i] - cost[i];
            sum += running_gas[i];
        }
        if(sum < 0){
            return -1;
        }
        
        int steps = gas.length - 1;
        int start = 0;
        int end = 0;
        int rsum = running_gas[start];
        while(steps > 0){
            if(rsum >= 0){
                end = (end+1)%gas.length;
                rsum += running_gas[end];
            }else{
                start = (start-1+gas.length)%gas.length;
                rsum += running_gas[start];
            }
            steps--;
        }
        return start;
    }
}