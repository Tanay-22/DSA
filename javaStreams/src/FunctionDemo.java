import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FunctionDemo
{
    public static void main(String[] args)
    {
        // Function -> same working as Predicate but with dynamic return type
        // Function<T, R>
        Function<Integer, Integer> doubleIt = x -> x * 2;
        Function<Integer, Integer> tripleIt = x -> x * 3;
        System.out.println(doubleIt.apply(100));
        System.out.println(doubleIt.andThen(tripleIt).apply(50));
        System.out.println(tripleIt.andThen(doubleIt).apply(50)); // same
        System.out.println(doubleIt.compose(tripleIt).apply(50)); // same

        Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(47)); // returns the same value as input

        // <T, U, R> == (T, U) -> R
        BiFunction<String, String, Integer> lenDiff = (x, y) -> x.length() - y.length();
        System.out.println(lenDiff.apply("awqw", "asdasd"));

        // UnaryOperator is child class of Function
        // input type == return type
        UnaryOperator<Integer> doubleItUnary = x -> x * 2;

        // BinaryOperator is child class of BiFunction
        // both input type == return type
        BinaryOperator<Integer> sum = (x, y) -> x + y;
    }
}
