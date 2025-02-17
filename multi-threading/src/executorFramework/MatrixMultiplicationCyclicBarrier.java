package executorFramework;

import java.util.concurrent.*;

class MatrixMultiplicationCyclicBarrier
{
    private static final int SIZE = 3; // Matrix size (NxN)
    private static final int THREADS = SIZE; // One thread per row
    private static int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    private static int[][] B = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
    };
    private static int[][] C = new int[SIZE][SIZE];

    private static CyclicBarrier barrier = new CyclicBarrier(THREADS, () ->
    {
        System.out.println("All threads have completed their computation.");
        printMatrix(C);
    });

    static class Worker implements Runnable
    {
        private int row;

        public Worker(int row)
        {
            this.row = row;
        }

        @Override
        public void run()
        {
            for (int col = 0; col < SIZE; col++)
            {
                for (int k = 0; k < SIZE; k++)
                {
                    C[row][col] += A[row][k] * B[k][col];
                }
            }
            try
            {
                barrier.await(); // Wait for all threads to finish
            }
            catch (InterruptedException | BrokenBarrierException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void printMatrix(int[][] matrix)
    {
        for (int[] row : matrix)
        {
            for (int num : row)
            {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < SIZE; i++)
        {
            executor.execute(new Worker(i));
        }

        executor.shutdown();
    }
}
