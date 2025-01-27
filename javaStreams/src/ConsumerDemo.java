import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerDemo
{
    public static void main(String[] args)
    {
        Consumer<Integer> printTill = x ->
        {
            for (int i = 0; i < x; i++)
            {
                System.out.print(i + " ");
            }
        };
        printTill.accept(20);

        Supplier<String> giveHello = () -> "Hemlo Doge";
        System.out.println(giveHello.get());

        BiConsumer<Integer, String> biConsumer = (x, y) -> System.out.println("No. " + x + " mapped to " + y);
    }
}
