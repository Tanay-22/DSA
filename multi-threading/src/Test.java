public class Test
{
    public static void main(String[] args)
    {
        World world = new World();
        world.start();
        for (; ;)
        {
            System.out.println("Hemlo from Thread - " + Thread.currentThread().getName());
        }

//        System.out.println(Thread.currentThread().getName());
    }
}
