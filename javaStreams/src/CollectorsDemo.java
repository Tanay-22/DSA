import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo
{
    public static void main(String[] args)
    {
        // Collectors is a utility class
        // provides a set of methods tp create common collectors

        // 1. Collecting to a List
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> res = names.stream().filter(x -> x.startsWith("A")).collect(Collectors.toList());
        System.out.println(res);

        // 2. Collecting to a Set
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> set = numbers.stream().collect(Collectors.toSet());
        System.out.println(set);

        // 3. Collecting to a Specific Collection
        Stack<String> stack = names.stream().collect(Collectors.toCollection(Stack::new));

        // 4. Joining Strings --> Concatenate stream elements into a string
        String concatedString = names.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        System.out.println(concatedString);

        // 5. Summarizing Data
        // Generates statistical summary (count, avg, max, min, sum)
        IntSummaryStatistics intSummaryStatistics = numbers.stream().collect(Collectors.summarizingInt(x -> x));
        System.out.println(
            "Count: " + intSummaryStatistics.getCount() + "\n" +
            "Sum: " + intSummaryStatistics.getSum() + "\n" +
            "Min: " + intSummaryStatistics.getMin() + "\n" +
            "Max: " + intSummaryStatistics.getMax() + "\n" +
            "Average: " + intSummaryStatistics.getAverage()
        );
        
    }
}
