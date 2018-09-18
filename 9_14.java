/* Reverse a linked list from position m to n. Do it in one-pass. */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m - n == 0){
            return head;
        }
        ListNode node = head;
        ListNode pre_next = null;
        ListNode pre_rever = null;
        ListNode change_head = null;
        ListNode change_end = null;
        int pos = 1;
        while(node != null){
            if(pos == m-1){
                pre_rever = node;
            }
            if(pos == n+1){
                change_end.next = node;
            }
            pre_next = node.next;
            if(pos >= m && pos <= n){
                if(pos == m){
                    change_end = node;
                }
                if(pre_rever != null){
                    pre_rever.next = node;
                }
                node.next = change_head;
                change_head = node;
            }
            pos++;
            node = pre_next;
        }
        
        if(m == 1){
            return change_head;
        }
        return head;
    }
}

