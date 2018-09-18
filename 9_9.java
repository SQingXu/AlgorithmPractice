/* Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0. */

class Solution {
    public int compareVersion(String version1, String version2) {
        List<Integer> l1, l2;
        l1 = versionList(version1);
        l2 = versionList(version2);
        for(int i = 0; i < Math.min(l1.size(), l2.size()); i++){
            int i1 = l1.get(i);
            int i2 = l2.get(i);  
            if(i1 > i2){
                return 1;
            }else if(i2 > i1){
                return -1;
            }else{
                continue;
            }
        }
        if(l1.size() > l2.size()){
            for(int i = l2.size(); i < l1.size(); i++){
                if(l1.get(i) > 0){
                    return 1;
                }
            }
            return 0;
        }else if(l1.size() < l2.size()){
            for(int i = l1.size(); i < l2.size(); i++){
                if(l2.get(i) > 0){
                    return -1;
                }
            }
            return 0;
        }else{
            return 0;
        }
    }
    
    private List<Integer> versionList(String s){
        String num_str = "";
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < s.length(); i ++){
            char ch = s.charAt(i);
            if(ch == '.'){
                ret.add(Integer.parseInt(num_str));
                num_str = "";
            }else{
                num_str += ch;
            }
        }
        if(!num_str.equals("")){
            ret.add(Integer.parseInt(num_str));
        }
        return ret;
    }
}

/* */