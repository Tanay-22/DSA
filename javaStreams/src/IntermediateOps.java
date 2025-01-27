import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOps
{
    public static void main(String[] args)
    {
        // Intermediate transform a stream into another stream
        //  are lazy i.e, they don't execute until a terminal operation is invoked.

        // 1. filter
        List<String> list = Arrays.asList("Sardar Khan", "Definite", "Perpendicular", "Ramadheer Singh");
        Stream<String> filteredStream =  list.stream().filter(x -> x.startsWith("S"));
        // no filtering at this point
        long a = filteredStream.count(); // now the filtering is performed
        System.out.println(a);

        // 2. map
        System.out.println(list.stream().map(String::toUpperCase));

        // 3. sort
        System.out.println(list.stream().sorted((x, y) -> y.length() - x.length()));

        // 4. distinct
        System.out.println(list.stream().filter(x -> x.startsWith("S")).distinct());

        // 5. limit
        System.out.println(Stream.iterate(1, x -> x + 1).limit(100).count());

        // 6. skip
        System.out.println(Stream.iterate(1, x -> x + 1).skip(10).limit(100).count());

        // 7. peek
        System.out.println(Stream.iterate(1, x -> x + 1).skip(10).limit(100).peek(System.out::println)
                .count());
        /*
            8. flatMap
            Returns a stream consisting of the results of replacing each element of this stream with the contents of a
            mapped stream produced by applying the provided mapping function to each element.
        */
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape")
        );
        System.out.println(listOfLists.get(1).get(1));
        System.out.println(listOfLists.stream().flatMap(x -> x.stream()).map(String:: toUpperCase).toList());

        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "flatMap is useful"
        );
        System.out.println(sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split( " "))).map(String::toUpperCase)
                .toList());
    }
}
