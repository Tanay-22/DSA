package stacknqueue;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    //20. Valid Parentheses
    public static boolean isValid(String s)
    {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if ("{[(".indexOf(ch) > -1)
                stack.push(ch);
            else
            {
                if (stack.isEmpty())
                    return false;
                char match = stack.peek();
                if ((ch == ')' && match == '(') || (ch == '}' && match == '{') || (ch == ']' && match == '['))
                    stack.pop();
                else
                    return false;
            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }

    //1021. Remove Outermost Parentheses
    public static String removeOuterParentheses(String s)
    {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '(')
            {
                open++;
                if (open > 1)
                    sb.append('(');
            } else
            {
                if (open > 1)
                    sb.append(')');
                open--;
            }
        }
        return sb.toString();
    }


    public static String removeDuplicates(String s)
    {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++)
        {
            if (stack.isEmpty())
            {
                stack.push(s.charAt(i));
                continue;
            }
            if (stack.peek() == s.charAt(i))
                stack.pop();
            else
                stack.push(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.insert(0, stack.pop());
        return sb.toString();
    }

    //921. Minimum Add to Make Parentheses Valid
    public static int minAddToMakeValid(String s)
    {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++)
        {
            if (stack.isEmpty())
            {
                stack.push(s.charAt(i));
                continue;
            }

            if (s.charAt(i) == ')' && stack.peek() == '(')
                stack.pop();
            else
                stack.push(s.charAt(i));
        }
        int count = 0;
        while (!stack.isEmpty())
        {
            count++;
            stack.pop();
        }
        return count;
    }

    //394. Decode String
    public static String decodeString(String s)
    {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            if (stack.isEmpty())
            {
                stack.push("" + s.charAt(i));
                continue;
            }

            if (s.charAt(i) == ']')
            {
                StringBuilder sub = new StringBuilder();
                while (stack.peek().charAt(0) != '[')
                    sub.insert(0, stack.pop());
                stack.pop();
                StringBuilder n = new StringBuilder();
                while (!stack.isEmpty() && "1234567890".indexOf(stack.peek()) != -1)
                    n.insert(0, stack.pop());
                int num = Integer.parseInt(n.toString());
                String str = sub.toString();
                sub = new StringBuilder();
                while (num-- > 0)
                    sub.append(str);
                stack.push(sub.toString());
            } else
                stack.push("" + s.charAt(i));
        }
        while (!stack.isEmpty())
            sb.insert(0, stack.pop());

        return sb.toString();
    }

    //735. Asteroid Collision
    public static int[] asteroidCollision(int[] asteroids)
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(asteroids[0]);
        for (int i = 1; i < asteroids.length; i++)
        {
            if (stack.isEmpty())
            {
                stack.push(asteroids[i]);
                continue;
            }
            if (stack.peek() > 0 && asteroids[i] < 0)
            {
                if (magnitude(stack.peek()) == magnitude(asteroids[i]))
                    stack.pop();
                else if (magnitude(stack.peek()) < magnitude(asteroids[i]))
                {
                    stack.pop();
                    i--;
                }
            } else
                stack.push(asteroids[i]);
        }
        int arr[] = new int[stack.size()];
        for (int j = 0; j < stack.size(); j++)
            arr[j] = stack.get(j);
        return arr;
    }

    private static int magnitude(int num)
    {
        if (num < 0)
            return -num;
        return num;
    }


    private static int getMax(int arr[])
    {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            max = Math.max(max, arr[i]);
        return max;
    }

    public static int longestValidParentheses(String s)
    {
        Stack<Character> stack = new Stack<>();
        int maxCount = 0, count = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (stack.isEmpty())
            {
                stack.push(s.charAt(i));
                continue;
            }
            if (stack.peek() == '(' && s.charAt(i) == ')')
            {
                count += 2;
                stack.pop();
            } else
            {
                if (maxCount < count)
                    maxCount = count;
                count = 0;
                stack.push(s.charAt(i));
            }
        }
        return maxCount;
    }

    public static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode()
        {
        }

        TreeNode(int val)
        {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> rightSideView(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();

        if (root == null)
            return list;
        while (root.right != null)
        {
            list.add(root.val);
            root = root.right;
        }
        list.add(root.val);
        return list;
    }

    public static String simplifyPath(String path)
    {
        StringTokenizer st = new StringTokenizer(path, "/.");

        StringBuilder sb = new StringBuilder("/");

        while (st.hasMoreTokens())
            sb.append(st.nextToken() + "/");
        return sb.substring(0, sb.length() - 1);
    }

    public static int largestRectangleArea(int[] arr)
    {
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        stack.push(0);

        for (int i = 1; i < arr.length; i++)
        {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()])
                max = getMaxHeight(arr, stack, max, i);
            stack.push(i);
        }

        int i = arr.length;
        while (!stack.isEmpty())
            max = getMaxHeight(arr, stack, max, i);

        return max;
    }

    private static int getMaxHeight(int arr[], Stack<Integer> stack, int max, int i)
    {
        int area;
        int popped = stack.pop();
        if (stack.isEmpty())
            area = arr[popped] * i;
        else
            area = arr[popped] * (i - 1 - stack.peek());

        return Math.max(max, area);
    }

    //1541. Minimum Insertions to Balance a Parentheses String
    public static int minInsertions(String s)
    {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char ch = s.charAt(i);
            if (ch == '(')
                stack.push(ch);
            else
            {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')')
                    i++;
                else
                    count++;

                if (!stack.isEmpty())
                    stack.pop();
                else
                    count++;
            }
        }
        count += 2 * stack.size();
        return count;
    }

    //224. Basic Calculator HARD
    public static int calculate(String s)
    {
        List<String> tokens = tokenizeExpression(s);
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++)
        {
            String t = tokens.get(i);

            if (t.equals(")"))
            {
                List<String> list = new ArrayList<>();
                while (!stack.peek().equals("("))
                    list.add(0, stack.pop());
                stack.pop();
                int ans = calculateHelper(list);
                stack.push("" + ans);
            } else
                stack.push(t);
        }
        List<String> rem = new ArrayList<>();
        while (!stack.isEmpty())
            rem.add(0, stack.pop());
        return calculateHelper(rem);
    }

    private static int calculateHelper(List<String> equation)
    {
        int ans = 0;
        boolean isAdd = true;
        for (int i = 0; i < equation.size(); i++)
        {
            String s = equation.get(i);
            if ("+-".indexOf(s) > -1)
            {
                if (s.equals("-"))
                    isAdd = false;
                else
                    isAdd = true;

            } else
            {
                if (isAdd)
                    ans += Integer.parseInt(s);
                else
                    ans -= Integer.parseInt(s);
            }
        }
        return ans;
    }

    //739. Daily Temperatures
    public static int[] dailyTemperatures(int[] temperatures)
    {
        Stack<Integer> stack = new Stack<>();
        int arr[] = new int[temperatures.length];

        for (int i = 0; i < arr.length; i++)
        {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()])
            {
                arr[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return arr;
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

                if ("+-()".indexOf(ch) != -1)
                    tokens.add(String.valueOf(ch));
            }
        }
        if (currentToken.length() > 0)
            tokens.add(currentToken.toString());

        return tokens;
    }

    public static int evalRPN(String[] tokens)
    {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if(tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("/") || tokens[i].equals("*"))
            {
                int y = Integer.parseInt(stack.pop());
                int x = Integer.parseInt(stack.pop());

                int res = 0;
                switch (tokens[i])
                {
                    case "+":
                        res = x + y;
                        break;

                    case "-":
                        res = x - y;
                        break;

                    case "*":
                        res = x * y;
                        break;

                    case "/":
                        res = x / y;
                        break;
                }
                stack.push(String.valueOf(res));
            }
            else
                stack.push(tokens[i]);
        }
        return Integer.parseInt(stack.pop());
    }

    public static int sumSubarrayMins(int[] arr)
    {
        Stack<Integer> stack = new Stack<>();
        long sum = 0l;
        for (int i = 0; i < arr.length; i++)
        {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++)
            {
                stack.push(arr[j]);
                min = Math.min(stack.peek(), min);
                sum += min;
            }
            stack.clear();
        }
        return (int)(sum % (long)(1e9 + 7));
    }

   

    public static void main(String[] args)
    {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
