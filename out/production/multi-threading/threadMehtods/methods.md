# Thread Methods

### start()
- Causes this thread to begin execution; 
the JVM calls the run method of this thread.

### join()
- Waits for this thread to die.

### sleep(long millis)
- Causes the currently executing thread to sleep (temporarily cease execution) for the specified number of milliseconds, 
subject to the precision and accuracy of system timers and schedulers.

### setPriority(int newPriority)
- set the priority of the thread although not strictly followed by JVM

### interrupt()
- Interrupts this thread.

### yield()
- A hint to the scheduler that the current thread is willing to yield its current use of a processor.

### setDaemon(boolean on)
- Marks this thread as either a daemon thread or a user thread.
- Daemon threads cannot prevent the JVM from exiting when all user threads finish their execution
- If all user threads complete their tasks, the JVM terminates itself, regardless of whether any daemon threads are running.
-  lowest priority among all threads in Java.
- If the JVM detects a running daemon thread, it terminates the thread and subsequently shuts it down. The JVM does not check if the daemon thread is actively running; it terminates it regardless.