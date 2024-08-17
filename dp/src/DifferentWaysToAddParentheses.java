import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToAddParentheses
{
    /*
    Example 1:

    Input: expression = "2-1-1"
    Output: [0,2]
    Explanation:
        ((2-1)-1) = 0
        (2-(1-1)) = 2
    Example 2:

    Input: expression = "2*3-4*5"
    Output: [-34,-14,-10,-10,10]
    Explanation:
        (2*(3-(4*5))) = -34
        ((2*3)-(4*5)) = -14
        ((2*(3-4))*5) = -10
        (2*((3-4)*5)) = -10
        (((2*3)-4)*5) = 10
    */
    public static List<Integer> diffWaysToCompute(String expression)
    {
        List<Integer> list = new ArrayList<>();
        return solve(expression);
    }

    public static List<Integer> solve(String str)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*')
            {
                List<Integer> left = solve(str.substring(0, i));
                List<Integer> right = solve(str.substring(i+1));

                for (Integer l : left)
                {
                    for (Integer r: right)
                    {
                        int res = 0;
                        switch (ch)
                        {
                            case '+':
                                res = l + r;
                                break;

                            case '-':
                                res = l - r;
                                break;

                            case '*':
                                res = l * r;
                                break;
                        }
                        list.add(res);
                    }
                }
            }
        }
        if(list.isEmpty())
            list.add(Integer.valueOf(str));

        return list;
    }



    public static void main(String[] args)
    {
        String s = "2*3-4*5";
        System.out.println(diffWaysToCompute(s));
    }
}
