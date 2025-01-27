import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo
{
    public static void main(String[] args)
    {
        // process collections of data in a functional and declarative manner
        // simplify data processing
        // embrace functional programming
        // improves Readability and Maintainability
        // enable Easy Parallelism

        // Count Even Numbers
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // Traditional Way
        int count = 0;
        for (Integer e: list)
        {
            if((e & 1) == 0)
            {
                count++;
            }
        }
        System.out.println("Count = " + count);

        // Streams way
        System.out.println("Count 2 = " + list.stream().filter(x -> (x & 1) == 0).count());


        // Creating Streams

        // 1. Form collections
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream1 = list.stream();

        // 2. Form Arrays
        String[] arr = {"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(arr);

        // 3. Using Stream.of()
        Stream<String> stream3 = Stream.of("a", "b");

        // 4. Infinte Stream
        Stream<Integer> stream4 = Stream.generate(() -> 1);
        Stream.iterate(1, x -> x + 1);
    }
}
