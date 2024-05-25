package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RecursionQuestions
{
//    17. Letter Combinations of a Phone Number

    public static List<String> letterCombinations(String digits)
    {
        List<String> list = new ArrayList<>();

        if(digits.isEmpty())
            return list;

        list = letterCombinationsHelper("", digits);

        return list;
    }
    // p - processed    up - unprocessed
    public static List<String> letterCombinationsHelper(String p, String up)
    {
        if(up.isEmpty())
        {
            List<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        int digit = up.charAt(0) - '0';
        int i = (digit - 2) * 3;
        if (digit > 7)
            i += 1;
        int len = (digit == 7 || digit == 9) ? i + 4: i + 3;

        List<String> list = new ArrayList<>();

        for (; i < len; i++)
        {
            char ch = (char)('a' + i);
            list.addAll(letterCombinationsHelper(p + ch, up.substring(1)));
        }
        return list;
    }


    //1155. Number of Dice Rolls With Target Sum
    static int countDice = 0;
    public static void numRollsToTargetHelper(int n, int k, int target)
    {

        if (n == 0)
        {
            if (target == 0)
                countDice++;
            return;
        }

        for (int i = 1; i <= k && i <= target; i++)
            numRollsToTargetHelper(n - 1, k,target - i);
    }

    public static int numRollsToTarget(int n, int k, int target)
    {
        numRollsToTargetHelper(n, k, target);
        return countDice;
    }

    /*public static boolean predictTheWinner(int[] nums)
    {
        return predictTheWinnerHelper(nums, 0, nums.length-1,0, 0, true);
    }

    public static boolean predictTheWinnerHelper(int[] nums, int start, int end, int sum1, int sum2, boolean turn)
    {
        if(start > end)
           return sum1 >= sum2;

        if(turn)
            return predictTheWinnerHelper(nums, start+1, end, sum1+nums[start], sum2, !turn) ||
                    predictTheWinnerHelper(nums, start, end-1, sum1+nums[end], sum2, !turn);
        else
            return predictTheWinnerHelper(nums, start+1, end, sum1, sum2+nums[start], !turn) ||
                    predictTheWinnerHelper(nums, start, end-1, sum1, sum2+nums[end], !turn);
    }*/

    public static void main(String[] args)
    {
//        System.out.println(predictTheWinner(new int[]{1,5,2}));
    }
}


