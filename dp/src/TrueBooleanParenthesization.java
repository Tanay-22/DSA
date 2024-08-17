import java.util.HashMap;
import java.util.Map;

public class TrueBooleanParenthesization
{
    /*Input: symbol[]    = {T, F, T}
    operator[]  = {^, &}
    Output: 2
    The given expression is "T ^ F & T", it evaluates true
    in two ways "((T ^ F) & T)" and "(T ^ (F & T))"

    Input: symbol[]    = {T, F, F}
    operator[]  = {^, |}
    Output: 2
    The given expression is "T ^ F | F", it evaluates true
    in two ways "( (T ^ F) | F )" and "( T ^ (F | F) )".

    Input: symbol[]    = {T, T, F, T}
    operator[]  = {|, &, ^}
    Output: 4
    The given expression is "T | T & F ^ T", it evaluates true
    in 4 ways ((T|T)&(F^T)), (T|(T&(F^T))), (((T|T)&F)^T)
    and (T|((T&F)^T)).*/

    public static int solve(String str, int i, int j, boolean isTrue, Map<String, Integer> hashMap)
    {
        if (i > j)
            return 0;
        if (i == j)
        {
            if (isTrue)
                return str.charAt(i) == 'T' ? 1 : 0;
            else
                return str.charAt(i) == 'F' ? 1 : 0;
        }
        if (hashMap.containsKey(i + " " + j + " " + isTrue))
            return hashMap.get(i + " " + j + " " + isTrue);

        int ans = 0;

        for (int k = i + 1; k <= j - 1; k = k + 2)
        {
            int leftT, leftF, rightT, rightF;

            if(!hashMap.containsKey(i + " " + (k-1) + true))
                leftT = solve(str, i, k - 1, true, hashMap);
            else
                leftT = hashMap.get(i + " " + (k-1) + true);

            if (!hashMap.containsKey(i + " " + (k-1) + false))
                leftF = solve(str, i, k - 1, false, hashMap);
            else
                leftF = hashMap.get(i + " " + (k-1) + false);

            if (!hashMap.containsKey((k+1) + " " + j + true))
                rightT = solve(str, k + 1, j, true, hashMap);
            else
                rightT = hashMap.get((k+1) + " " + j + true);

            if (!hashMap.containsKey((k+1) + " " + j + false))
                rightF = solve(str, k + 1, j, false, hashMap);
            else
                rightF = hashMap.get((k+1) + " " + j + false);

            if (str.charAt(k) == '^')   // XOR
            {
                // result of XOR is true or false
                if (isTrue)
                    ans += (leftT * rightF) + (leftF * rightT);
                else
                    ans += (leftT * rightT) + (leftF * rightF);
            }
            else if (str.charAt(k) == '&')  // AND
            {
                // result of AND is true or false
                if (isTrue)
                    ans += (leftT * rightT);
                else
                    ans += (leftT * rightF) + (leftF * rightT) + (leftF * rightF);
            }
            else if (str.charAt(k) == '|')  // OR
            {
                // result of OR is true or false
                if (isTrue)
                    ans += (leftT * rightF) + (leftF * rightT) + (leftT * rightT);
                else
                    ans += (leftF * rightF);
            }
            hashMap.put(i + " " + j + isTrue, ans);
        }
        return ans;
    }

    public static int countWays(int N, String S)
    {
        Map<String, Integer> hashMap = new HashMap<>();
        return solve(S, 0, N - 1, true, hashMap);
    }

    public static void main(String[] args)
    {
        String s = "T|F&T^F";
        System.out.println(countWays(s.length(), s));
    }
}
