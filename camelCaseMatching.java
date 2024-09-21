//Time complexity: O(n * m)
//space complexity is O(n)

import java.util.ArrayList;
import java.util.List;

public class camelCaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries==null || queries.length==0)
        {
            return new ArrayList<>();
        }
        List<Boolean> res= new ArrayList<>();
        for(String s: queries)
        {
            int j=0;
            boolean flag=false;
            for(int i=0; i<s.length(); i++)
            {
                if(j<pattern.length() && s.charAt(i)== pattern.charAt(j))
                {
                    j++;
                }
                else if(Character.isUpperCase(s.charAt(i)))
                {
                    flag=false;
                    break;
                }
            
                if(j==pattern.length())
                {
                    flag=true;
                }
            }
                res.add(flag);
        }  
        return res;
    }
    public static void main(String[] args) {
        // Test case
        String[] queries = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        String pattern = "FB";
        camelCaseMatching c= new camelCaseMatching();
        List<Boolean> result = c.camelMatch(queries, pattern);
        
        // Printing the results
        System.out.println(result);  // Output should be [true, false, true, true, false]
    }
}
    
