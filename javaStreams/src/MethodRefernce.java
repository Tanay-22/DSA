import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodRefernce
{
    public static void main(String[] args)
    {
        // Method reference --> use method without invoking & in place of lambda expression
        List<String> list = Arrays.asList("Daredevil", "Punisher", "Spiderman", "Miles Morales");
        list.forEach(x -> System.out.println(x));
        list.forEach(System.out::println); // method reference

        // Constructor reference
        List<String> names = Arrays.asList("A", "B", "C");
        List<Phone> phones = names.stream().map(Phone::new).collect(Collectors.toList());
    }
}

class Phone
{
    String name;

    public Phone(String name)
    {
        this.name = name;
    }
}