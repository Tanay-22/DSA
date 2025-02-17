import java.io.*;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{

    private static void readChunk(RandomAccessFile file, long start, long end)
    {
        try
        {
            file.seek(start);
            byte[] buffer = new byte[4096];
            long bytesRead = start;

            while (bytesRead < end)
            {
                int toRead = (int) Math.min(buffer.length, end - bytesRead);
                int read = file.read(buffer, 0, toRead);
                if (read == -1) break;
                bytesRead += read;

                String chunkData = new String(buffer, 0, read);
                processChunk(chunkData);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void processChunk(String data)
    {
        System.out.println(Thread.currentThread().getName() + " processed: " + data + " bytes");
    }


    public static void main(String[] args) throws IOException
    {
        RandomAccessFile file = new RandomAccessFile("C:\\DSA\\FileReading\\src\\temperatures.txt", "r");
        FileChannel channel = file.getChannel();
        long fileSize = channel.size();

        int numThreads = 4;
        long chunkSize = fileSize / numThreads;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++)
        {
            long start = i * chunkSize;
            long end = (i == numThreads - 1) ? fileSize : start + chunkSize;
            executor.submit(() -> readChunk(file, start, end));
        }
        executor.shutdown();
    }
}
