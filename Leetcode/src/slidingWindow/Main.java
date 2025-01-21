package slidingWindow;

public class Main
{
    public int maxScore(int[] cardPoints, int k)
    {
        int n = cardPoints.length;
        int left = 0;
        int count = 0, sum = 0, max = 0;
        for(int right = 0; right < n; right++)
        {
            sum += cardPoints[right];
            count++;
            while(count > k)
            {
                sum -= cardPoints[left++];
                count--;
            }
            if(count == k)
                max = Math.max(max, sum);
        }
        return max;
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        System.out.println(main.maxScore(new int[]{1,2,3,4,5,6,1}, 3));
    }
}
