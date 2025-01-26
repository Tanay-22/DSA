public class LambdaExpression
{
    public static void main(String[] args)
    {
        // Lambda expressions are applicable to functional interfaces
        // Functional Interface - interfaces with only one function without body

        Runnable task1 = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println("Task 1");
            }
        };
        Thread t1 = new Thread(task1);

        Runnable task2 = () -> System.out.println("Task 2");
        Thread t2 = new Thread(task2);

        Thread t3 = new Thread(() ->
        {
            for (int i = 0; i < 10; i++)
            {
                System.out.println("Task 3");
            }
        });
    }
}
