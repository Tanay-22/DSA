
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOps
{
    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(1, 2, 3);

        // 1. collect
        list.stream().skip(1).collect(Collectors.toList());
        list.stream().skip(1).toList();

        // 2. forEach
        list.stream().forEach(System.out::println);

        // 3. reduce: Combines element to produce a single result
        Optional<Integer> optionalInteger = list.stream().reduce(Integer::sum);
        System.out.println(optionalInteger.get());

        // 4. count

        // 5. anyMatch, allMatch, noneMatch
        System.out.println(list.stream().anyMatch(x -> x % 2 == 0));
        System.out.println(list.stream().allMatch(x -> x > 0));
        System.out.println(list.stream().noneMatch(x -> x < 0));

        // 6. findFirst, findAny
        System.out.println(list.stream().findFirst().get());
        System.out.println(list.stream().findAny().get());

        // 7. toArray,
        // 8. min/max
        int[] arr = {1, 8, 7, 56, 90};
        Integer i = Arrays.stream(arr).max().getAsInt();
        System.out.println(i);

        // 9. forEachOrdered
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Using forEach with parallel stream:");
        numbers.parallelStream().forEach(System.out::println);
        System.out.println("Using forEachOrdered with parallel stream:");
        numbers.parallelStream().forEachOrdered(System.out::println);

        // Examples: Filtering and Collecting Names
        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
//        names.stream().filter(x -> x.length() > 3).forEach(System.out::println);
        System.out.println(names.stream().filter(x -> x.length() > 3).toList());

        // Examples: Squaring and Sorting Numbers
        List<Integer> numbers1 = Arrays.asList(5, 2, 9, 1, 6);
        System.out.println(numbers.stream().map(x -> x * x).sorted().toList());
        System.out.println(numbers.stream().reduce(Integer::sum).get());

        // Example: Counting Occurrence of a Character
        String sentence = "Hemlo World";
        System.out.println(sentence.chars().filter(x -> x == 'l' | x == 'L').count());

        // Streams cannot be reused after a terminal operation has been called
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println);
//        List<String> list1 = stream.map(String::toUpperCase).toList(); // exception
    }
}
