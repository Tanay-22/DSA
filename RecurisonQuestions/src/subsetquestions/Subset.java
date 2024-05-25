package subsetquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset
{
    static ArrayList<String> subSeq(String p, String up)
    {
        if(up.isEmpty())
        {
            ArrayList<String> strings = new ArrayList<>();
            if(!p.isEmpty())
                strings.add(p);
            return strings;
        }
        char ch = up.charAt(0);

        ArrayList<String> left = subSeq(p, up.substring(1));
        ArrayList<String> right = subSeq(p+ch, up.substring(1));

        left.addAll(right);
        return left;
    }

    static List<List<Integer>> subset(int arr[])
    {
        List<List<Integer>> outerList = new ArrayList<>();
        outerList.add(new ArrayList<>());
        for (int num: arr)
        {
            int n = outerList.size();
            for (int i = 0; i < n; i++)
            {
                List<Integer> internal = new ArrayList<>(outerList.get(i));
                internal.add(num);
                 outerList.add(internal);
            }
        }
        return outerList;
    }


    public static void main(String[] args)
    {
//        Arrays.stream(subSeq("", "abcd").toArray()).forEach(System.out::println);
        int arr[] = {1, 2, 3};
        List<List<Integer>> list = subset(arr);
        for (List<Integer> l: list)
        {
            System.out.println(l);
        }
    }
}
