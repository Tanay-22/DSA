package heaps;

import java.util.*;

public class Main
{
    class ClassRecord implements Comparable<ClassRecord>
    {
        int pass;
        int total;

        public ClassRecord(int pass, int total)
        {
            this.pass = pass;
            this.total = total;
        }

        @Override
        public int compareTo(ClassRecord o)
        {
            double a = 1.0 * (this.pass + 1) / (this.total + 1) -
                    1.0 * this.pass / this.total;

            double b = 1.0 * (o.pass + 1) / (o.total + 1) -
                    1.0 * o.pass / o.total;

            return Double.compare(b, a);
        }
    }


    public double maxAverageRatio(int[][] classes, int extraStudents)
    {
        PriorityQueue<ClassRecord> pq = new PriorityQueue<>();

        for(int[] c: classes)
            pq.offer(new ClassRecord(c[0], c[1]));

        while(extraStudents-- > 0 && !pq.isEmpty())
        {
            ClassRecord top = pq.poll();
            top.pass++;
            top.total++;
            pq.offer(top);
        }
        double total = 0.0;

        for(ClassRecord cr: pq)
            total += 1.0 * cr.pass / cr.total;

        return total / classes.length;
    }
    
    public static void main(String[] args)
    {

    }
}
