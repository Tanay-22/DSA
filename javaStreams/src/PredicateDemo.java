import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateDemo
{
    public static void main(String[] args)
    {
        // Predicate --> Functional interface (Boolean valued function)
        Predicate<Integer> isEven = x -> (x & 1) == 0;
        Predicate<String> startsWithA = s -> s.toLowerCase().startsWith("a");
        Predicate<String> endsWithH = s -> s.toLowerCase().endsWith("h");
        Predicate<String> and = startsWithA.and(endsWithH);
        System.out.println(isEven.test(44));
        System.out.println(and.test("Ashish"));

        // BiPredicate
        BiPredicate<Integer, Integer> isSumEven = (x, y) -> ((x + y) & 1) == 0;
        System.out.println(isSumEven.test(5, 8));
    }
}
