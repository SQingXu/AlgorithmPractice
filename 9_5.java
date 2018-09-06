//Tech Interview Practice
/* Sep 6 */
/* Q1: Justify String */
public class JustifyPractice {
	public static void main(String[] args) {
		try {
			System.out.println(justify(30, "This is some sample text, really just enough to generate a few lines in the output to show what the text justify function is supposed to do."));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static String justify(int length, String string) throws Exception{
	    int start_index = 0;
	    int end_index = 0;
	    String current_word = "";
	    List<String> current_line = new ArrayList<String>();
	    String ret = "";
	    for(int i = 0; i < string.length(); i++){
	        char ch = string.charAt(i);
	        if(ch == ' ' || i == string.length()-1){
	            if(ch == ' '){
	                end_index = i;
	            }else if(i == string.length()-1){
	                end_index = i+1;
	            }
	            current_word = string.substring(start_index, end_index);
	            start_index = i+1;
	            if(current_word.length() > length){
	                throw new 
	                        Exception("length of word exceed the reuqired length");
	            }else if(current_word.length()+1+getMinLineLength(current_line) > length){
	                ret = addLine(ret, length, current_line);
	                current_line = new ArrayList<String>();
	            }else{
	                current_line.add(current_word);
	            }
	        }
	    }
	    if(current_line.size() > 0) {
	    	ret = addLine(ret, length, current_line);
	    }
	    return ret;
	}

	static int getMinLineLength(List<String> ls){
	    int ret= 0;
	    for(int i =0; i< ls.size(); i++){
	        ret += (ls.get(i).length() + 1);
	    }
	    return ret-1;//no need for the last word
	}

	static String addLine(String orig, int length, List<String> ls){
	    String ret = orig;
	    int minLen = getMinLineLength(ls);
	    int extraSpace = length - minLen;
	    int extraSpaceAvg;
	    int extraSpaceRemain;
	    //deal with divide by 0 error
	    if(ls.size() > 1) {
	    	extraSpaceAvg = extraSpace/(ls.size()-1);
		    extraSpaceRemain = extraSpace%(ls.size() - 1);
	    }else {
	    	extraSpaceAvg = 0;
	    	extraSpaceRemain = extraSpace;
	    }
	    
	    
	    for(int i = 0; i < ls.size(); i++){
	        ret += ls.get(i);
	        if(i != ls.size()-1){
	        	int extraSpaceRemainAvg = (extraSpaceRemain > 0)?1:0;
	            for(int j = 0; j < extraSpaceAvg+1+extraSpaceRemainAvg; j++){
	                ret += ' ';
	            }
	            extraSpaceRemain -= 1;
	        }else{
	        	for(int j = 0; j < extraSpaceRemain; j++) {
	        		ret += ' ';
	        	}
	            ret += '\n';
	        }
	        
	    }
	    return ret;
	}
}


/* Q2: Pascal Triangles: Given a non-negative index k where k ≤ 33, return
 the kth index row of the Pascal's triangle. */
 class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre_row, current_row;
        pre_row = new ArrayList<>();
        current_row = new ArrayList<>();
        pre_row.add(1);
        for(int i = 1; i <= rowIndex; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i){
                    current_row.add(1);
                }else{
                    current_row.add(pre_row.get(j-1) + pre_row.get(j));
                }
            }
            pre_row = current_row;
            current_row = new ArrayList<>();
        }
        return pre_row;
    }
}

/* Q3: Add Binary Given two binary strings, return their sum (also a binary string).
The input strings are both non-empty and contains only characters 1 or 0. */

class Solution {
    public String addBinary(String a, String b) {
        int length = Math.max(a.length(), b.length());
        String ret = "";
        int carry = 0;
        for(int i = 0; i < length; i++){
            int index_a = a.length()-1-i;
            int index_b = b.length()-1-i;
            int value_a = (index_a >= 0)? ((a.charAt(index_a) == '1')?1:0):0;
            int value_b = (index_b >= 0)? ((b.charAt(index_b) == '1')?1:0):0;
            //System.out.println("a: " + value_a + " b: " + value_b);
            int value = value_a + value_b + carry;
            int add_val = 0;
            add_val = value%2;
            carry = value/2;
            ret = (add_val == 0)?('0' + ret):('1' + ret);
        }
        if(carry == 1){
            ret = (carry == 0)?('0' + ret):('1' + ret);
        }
        return ret;
    }
}

/* Q2: Given two non-negative integers num1 and num2 represented as strings
, return the product of num1 and num2, also represented as a string.*/
class Solution {
    public String multiply(String num1, String num2) {
        String ret = "";
        for(int i = 0; i < num1.length(); i++){
            char ch = num1.charAt(num1.length()-1-i); 
            if(i != 0){
                String res_m = multiplySingle(num2, ch);
                for(int j = 0; j < i; j++){
                    res_m += '0';
                }
                //System.out.println("before add " + ret);
                ret = addTwoString(ret, res_m);
                //System.out.println("after add " + res_m + " res is: " + ret);
            }else{
                ret = multiplySingle(num2, ch);
                //System.out.println("first string is " + ret);
            }
        }
        boolean isZero = true;
        for(int i = 0; i < ret.length(); i++){
            if(ret.charAt(i) != '0'){
                isZero = false;
                break;
            }
        }
        if(isZero){
            return "0";
        }
        return ret;
    }
    private String addTwoString(String n1, String n2){
        String ret = "";
        int length = Math.max(n1.length(), n2.length());
        int carry = 0;
        for(int i = 0; i < length; i++){
            int index1 = n1.length()-1-i;
            int index2 = n2.length()-1-i;
            int value1 = (index1 >= 0)?Character.getNumericValue(n1.charAt(index1)):0;
            int value2 = (index2 >= 0)?Character.getNumericValue(n2.charAt(index2)):0;
            int value = value1 + value2 + carry;
            carry = value/10;
            int add_val = value%10;
            ret = Integer.toString(add_val) + ret;
        }
        if(carry != 0){
            ret = Integer.toString(carry) + ret;
        }
        return ret;
    }
    
    private String multiplySingle(String num1, char num2){
        int valueS = Character.getNumericValue(num2);
        int carry = 0;
        String ret = "";
        for(int i = 0; i < num1.length(); i++){
            int valueM = Character.getNumericValue(num1.charAt(num1.length()-1-i));
            int product = valueS * valueM + carry;
            int add_val = product%10;
            carry = product/10;
            ret = Integer.toString(add_val) + ret;
        }
        if(carry != 0){
            ret = Integer.toString(carry) + ret;
        }
        return ret;
    }
}
