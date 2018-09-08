/* Q1. Given a string containing only digits, restore it by returning 
all possible valid IP address combinations. */
class Solution {
    public List<String> restoreIpAddresses(String s) {
        //loop through four possible digit
        List<String> ls = new ArrayList<>();
        for(int i = 1; i <= 3; i++){
            recFindNextDigit(0, i, 0, ls, "", s);
        }
        return ls;
    }
    private void recFindNextDigit(int start, int digit_num, int digits, List<String> ls, String currentStr, String s){
        if(start +digit_num > s.length() || (digits == 3 && start+digit_num != s.length())){
            return;
        }
        String num_str = s.substring(start, start+digit_num);
        int num = Integer.parseInt(num_str);
        //deal with 0's issue such as "00" means 0 or "010" means 10
        if((num_str.charAt(0) == '0' && num != 0) || (num_str.length() > 1 && num == 0)){
            return;
        }
        if(num < 256){
            if(digits == 0){
                currentStr += num_str;
            }else{
                currentStr += ("." + num_str);
            }
            
        }else{
            return;
        }
        
        if(digits == 3){
            ls.add(currentStr);
            return;
        }else{
            for(int i = 1; i <= 3; i++){
                recFindNextDigit(start+digit_num, i, digits+1, ls, currentStr, s);
            }
        }
    }
}

/* Q2: Given an absolute path for a file (Unix-style), simplify it. */
class Solution {
    public String simplifyPath(String path) {
        //first I assume the path always starts with a slash (root folder)
        if(path.charAt(0) != '/'){
            return "";
        }
        
        String ret = "";
        String current_name = "";
        for(int i = 1; i < path.length(); i++){
            char ch = path.charAt(i);
            if(ch == '/'){
                if(current_name.equals("")){
                    continue;
                }else if(current_name.equals("..")){
                    ret = goBackOneLevel(ret);
                }else if(current_name.equals(".")){
                    //do nothing stay in current folder
                }else{
                    ret += ("/" + current_name);
                }
                current_name = "";
            }else{
                current_name += ch;
            }
        }
        if(!current_name.equals("") && !current_name.equals(".")){
            if(current_name.equals("..")){
                ret = goBackOneLevel(ret);
            }else{
                ret += ("/" + current_name);
            }
            
        }
        if(ret.equals("")){
            ret = "/";
        }
        return ret;
        
        
    }
    
    private String goBackOneLevel(String s){
        if(s == "/"){
            return s;
        }
        String ret = "";
        for(int i = s.length()-1; i >= 0; i--){
            if(s.charAt(i) == '/'){
                return s.substring(0, i);
            }
        }
        return ret;
    }
    
    
}

/* Q3 Invert a binary tree. */

class Solution {
    public TreeNode invertTree(TreeNode root) {
        recInvert(root);
        return root;
    }
    
    private void recInvert(TreeNode node){
        if(node == null){
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        recInvert(node.left);
        recInvert(node.right);
        return;
    }
}

/* Q4 Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL. */

public class Solution {
    public void connect(TreeLinkNode root) {
        //breath first search
        if(root == null){
            return;
        }
        TreeLinkNode node = root;
        TreeLinkNode prev_node = null;
        int level_num = 1;
        int next_level_num = 0;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            node = queue.poll();
            level_num --;
            if(prev_node != null){
                prev_node.next = node;
            }
            if(node.left != null){
                queue.add(node.left);
                next_level_num++;
            }
            if(node.right != null){
                queue.add(node.right);
                next_level_num++;
            }
            if(level_num == 0){
                level_num = next_level_num;
                next_level_num = 0;
                prev_node = null;
            }else{
                prev_node = node;
            }
            
        }
        
    }
}