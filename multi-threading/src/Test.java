public class Test
{
    public static void main(String[] args)
    {
        World world = new World();
        World2 world2 = new World2();
        Thread thread99 = new Thread(world2);

        world.start();
        thread99.start();
        
        for (; ;)
        {
            System.out.println("Hemlo from Thread - " + Thread.currentThread().getName());
        }

//        System.out.println(Thread.currentThread().getName());
    }
}
