import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveStreams
{
    public static void main(String[] args)
    {
        int[] nums = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(nums);
        System.out.println(IntStream.range(1, 5).boxed().collect(Collectors.toList()));
        System.out.println(IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList()));

        IntStream ints = new Random().ints(5);
        System.out.println(ints.boxed().toList());
    }
}
