import java.util.*;
import java.util.stream.Collectors;

public class Example
{
    public static void main(String[] args)
    {
        // 1. Collecting Names by Length
        List<String> l1 = Arrays.asList("Anna", "Bob", "Alexander", "Brian", "Alice");
        System.out.println(l1.stream().collect(Collectors.groupingBy(String::length)));

        // 2. Counting word occurrence
        String sentence = "hello world hello java world";
        System.out.println(Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        // 3. Partitioning Even and Odd Numbers
        List<Integer> l2 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(l2.stream().collect(Collectors.partitioningBy(x -> (x & 1) == 0)));

        int[] arr = {12, 35, 1, 10, 34, 1};
        System.out.println(Arrays.stream(arr)
                .boxed()
                .distinct()
                .sorted((x, y) -> Integer.compare(y, x))
                .skip(1)
                .findFirst()
                .orElse(-1));

        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        int k = 2;
        System.out.println(topKFrequentWords(words, k));

        List<Employee> employees = Arrays.asList(
                new Employee("John", "HR", 50000),
                new Employee("Alice", "IT", 75000),
                new Employee("Bob", "Finance", 60000),
                new Employee("Jane", "IT", 80000),
                new Employee("Paul", "Finance", 85000),
                new Employee("Sara", "HR", 55000),
                new Employee("Mike", "IT", 70000)
        );
        findHighestPaidEmployeeInEachDepartment(employees);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        System.out.println(findCommon(list1, list2));
    }

    private static List<String> topKFrequentWords(List<String> list, int k)
    {
        /*Map<String, Integer> map = list.stream().collect(Collectors.toMap(
                num -> num,
                num -> 1,
                Integer::sum
        ));
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();*/

        return list.stream().collect(Collectors.toMap(
                        num -> num,
                        num -> 1,
                        Integer::sum
                )).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    private static List<Employee> findHighestPaidEmployeeInEachDepartment(List<Employee> employees)
    {
        return employees.stream().collect(Collectors.groupingBy(e -> e.department,
                        Collectors.maxBy(Comparator.comparingDouble(e -> e.salary))))
                .values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private static List<Integer> findCommon(List<Integer> list1, List<Integer> list2)
    {
        return list1.stream().filter(x -> list2.contains(x)).toList();
    }
}

class Employee
{
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary)
    {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}

