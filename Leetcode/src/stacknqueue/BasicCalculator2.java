package stacknqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculator2
{
    //227. Basic Calculator II
    public static int calculate(String s)
    {
        List<String> tokens = tokenizeExpression(s);
        Stack<Integer> stack = new Stack<>();
        String operator = "+";
        for (int i = 0; i < tokens.size(); i++)
        {
            String t = tokens.get(i);
            if(operator.equals("+"))
                stack.push(Integer.parseInt(t));
            else if(operator.equals("-"))
                stack.push(-Integer.parseInt(t));
            else if(operator.equals("*"))
                stack.push(stack.pop() * Integer.parseInt(t));
            else if(operator.equals("/"))
                stack.push(stack.pop() / Integer.parseInt(t));

            operator = t;
        }
        int ans = 0;
        while (!stack.isEmpty())
            ans += stack.pop();
        return ans;
    }

    private static int calculateHelper(List<String> equation)
    {
        int ans = 0;
        boolean isAdd = true;
        for (int i = 0; i < equation.size(); i++)
        {
            String s = equation.get(i);
            if ("+-*/".indexOf(s) > -1)
            {
                if (s.equals("-"))
                    isAdd = false;
                else
                    isAdd = true;

            }
            else
            {
                if (isAdd)
                    ans += Integer.parseInt(s);
                else
                    ans -= Integer.parseInt(s);
            }
        }
        return ans;
    }

    private static List<String> tokenizeExpression(String expression)
    {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();

        for (int i = 0; i < expression.length(); i++)
        {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch))
                currentToken.append(ch);
            else
            {
                if (currentToken.length() > 0)
                {
                    tokens.add(currentToken.toString());
                    currentToken.setLength(0);
                }

                if ("+-*/".indexOf(ch) != -1)
                    tokens.add(String.valueOf(ch));
            }
        }
        if (currentToken.length() > 0)
            tokens.add(currentToken.toString());

        return tokens;
    }

    public static int brokenCalc(int start, int target)
    {
        int count = 0;
        while(target > start)
        {
            if((target & 1) == 0)
                target /= 2;
            else
                target++;
            count++;
        }
        return count + start - target;
    }

    public static void main(String[] args)
    {
        System.out.println(brokenCalc(5,8));
    }
}
