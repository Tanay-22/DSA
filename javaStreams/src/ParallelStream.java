import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ParallelStream
{
    public static void main(String[] args)
    {
        /*
         stream that enables parallel processing of elements
         Allowing multiple threads to process parts of the stream simultaneously
         This can significantly improve performance for large data sets
         // workload is distributed across multiple threads
        */
        long startTime = System.currentTimeMillis();
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000).toList();
        List<Long> factorials = list.stream().map(ParallelStream::factorial).toList();
        System.out.println("Total time taken with sequential stream: " + (System.currentTimeMillis() - startTime)
                + "ms");


        startTime = System.currentTimeMillis();
        factorials = list.parallelStream().map(ParallelStream::factorial).toList();
        System.out.println("Total time taken with parallel stream: " + (System.currentTimeMillis() - startTime)
                + "ms");

        /*
            Parallel streams are most effective for CPU-intensive or large datasets where tasks are independent
            They may add overhead for simple tasks or small datasets
        */

        // Cumulative sum [1, 2, 3, 4, 5] -> [1, 3, 6, 10, 15]
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Expected result: [1, 3, 6, 10, 15]");
        AtomicInteger sum = new AtomicInteger();
//        List<Integer> cumulativeSum = numbers.parallelStream().map(sum:: addAndGet).sequential().toList();
        List<Integer> cumulativeSum = numbers.stream().map(sum:: addAndGet).toList();
        System.out.println("Acutal result with parallel streams: " + cumulativeSum);
    }

    private static long factorial(int n)
    {
        long res = 1L;
        for (int i = 2; i <= n; i++)
        {
            res *= i;
        }
        return res;
    }
}
