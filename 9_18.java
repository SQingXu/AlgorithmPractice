/* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordDictD = new HashSet<String>();
        int max_len = 0;
        for(String i: wordDict){
            wordDictD.add(i);
            max_len = Math.max(i.length(),max_len);
        }
        //DP solution
        boolean[] d = new boolean[s.length()];
        for(int i = 0; i < s.length(); i++){
            for(String str: wordDict){
                if((i-str.length()+1 >=0) && str.equals(s.substring(i-str.length()+1, i+1)) && (i-str.length() < 0 || d[i-str.length()])){
                    d[i] = true;
                }
            }
        }
        return d[d.length-1];
        //return wordBreakRec("", s, 0, wordDictD, max_len); 
        
    }
    
    private boolean wordBreakRec(String cur_word, String s, int pos, HashSet<String> wordDictD, int max_len){
        if(wordDictD.contains(cur_word)){
            if(pos >= s.length()){
                return true;
            }
            boolean res1 = wordBreakRec(cur_word+s.charAt(pos), s, pos+1, wordDictD, max_len);
            if(res1){
                return res1;
            }
            boolean res2 = wordBreakRec("", s, pos, wordDictD, max_len);
            return (res1||res2);
        }else{
            //add char until find the macthed word
            String cur_wordc = cur_word;
            for(int p = pos; p < Math.min(s.length(), pos+(max_len-cur_word.length())); p++){
                cur_wordc += s.charAt(p);
                if(wordDictD.contains(cur_wordc)){
                    return wordBreakRec(cur_wordc,s, p+1,wordDictD, max_len);
                }
            }
            return false;
        }
    }
}

/* Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

*/

class Solution {
    public int maxDepth(TreeNode root) {
        // int max_depth = 0;
        // if(root == null){
        //     return max_depth;
        // }
        return maxDepthRec(root, 0);
        
    }
    
    private int maxDepthRec(TreeNode node, int depth){
        if(node == null){
            return depth;
        }
        depth += 1;
        int ret1 = maxDepthRec(node.left, depth);
        int ret2 = maxDepthRec(node.right, depth);
        return Math.max(ret1, ret2);
    }
}

/* Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.*/

class Solution {
    public int sumNumbers(TreeNode root) {
        return sumTreeRec(0, root);
    }
    
    private int sumTreeRec(int cur_num, TreeNode node){
        if(node == null){
            return 0;
        }
        int cur_sum = cur_num * 10 + node.val;
        //leaf
        if(node.left == null && node.right == null){
            return cur_sum;
        }
        int cur_sum_orig = cur_sum;
        cur_sum = 0;
        cur_sum += sumTreeRec(cur_sum_orig, node.left);
        cur_sum += sumTreeRec(cur_sum_orig, node.right);
        return cur_sum;
    }
}