package synchronization.locks;

class Pen
{
    public synchronized void writeWithPenAndPaper(Paper paper)
    {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this.getClass().getName() +
                " and trying to get " + paper.getClass().getName());
        paper.finishWriting();

    }

    public synchronized void finishWriting()
    {
        System.out.println(Thread.currentThread().getName() + " finished using pen " + this.getClass().getName());
    }
}

class Paper
{
    public synchronized void writeWithPaperAndPen(Pen pen)
    {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this.getClass().getName() +
                "and trying to get " + pen.getClass().getName());
        pen.finishWriting();
    }

    public synchronized void finishWriting()
    {
        System.out.println(Thread.currentThread().getName() + " finished using paper " + this.getClass().getName());
    }
}

class Task1 implements Runnable
{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper)
    {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run()
    {
        pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
    }
}

class Task2 implements Runnable
{
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper)
    {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run()
    {
//        paper.writeWithPaperAndPen(pen); // thread2 locks paper and tries to lock

        // how to avoid deadlock - consistent ordering
        // let task1 write first
        synchronized (pen)
        {
            paper.writeWithPaperAndPen(pen);
        }
    }
}
public class DeadlockDemo
{
    public static void main(String[] args)
    {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new Task1(pen, paper), "Thread-1");
        Thread t2 = new Thread(new Task2(pen, paper), "Thread-2");

        t1.start();
        t2.start();
    }
}
