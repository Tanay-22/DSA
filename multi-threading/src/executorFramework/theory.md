# Difference Between `Runnable` and `Callable` Interface

| Feature                  | `Runnable`                          | `Callable`                          |
|--------------------------|--------------------------------------|--------------------------------------|
| **Package**              | `java.lang`                        | `java.util.concurrent`              |
| **Method to Implement**  | `void run()`                       | `V call()`                          |
| **Return Type**          | Does not return a result (`void`).  | Returns a result of type `V`.       |
| **Checked Exception**    | Cannot throw checked exceptions.   | Can throw checked exceptions.       |
| **Use Case**             | Suitable for tasks that do not need a result. | Suitable for tasks that require a result or can throw exceptions. |
| **Example Usage**        | Used with `Thread` or `Executor`.  | Used with `ExecutorService` to return `Future`. |
| **Result Handling**      | No direct result from the task.    | Result can be obtained via `Future`. |

---
### `void shutdown()`
- Orderly shutdown in which previously submitted tasks are executed, but no new tasks will be accepted

### `List<Runnable> shutdownNow()`
- Attempts to stop all actively executing tasks, halts the processing of waiting tasks, and returns a list of the tasks that were awaiting execution.

### `boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException`
- Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs, or the current thread is interrupted, whichever happens first.

### `<T> Future<T> submit(Callable<T> task)`
- Submits a value-returning task for execution and returns a Future representing the pending results of the task. The Future's get method will return the task's result upon successful completion.
- `<T>` is return type of get()

### `<T> Future<T> submit(Runnable task, T result)`
- Submits a Runnable task for execution and returns a Future representing that task. The Future's get method will return the given result upon successful completion.
- `<T>` is return type of get()

### `Future<?> submit(Runnable task)`
- Submits a Runnable task for execution and returns a Future representing that task. The Future's get method will return null upon successful completion.

### `<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException`
- Executes the given tasks, returning a list of Futures holding their status and results when all complete or the timeout expires, whichever happens first

### `<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException`
- Executes the given tasks, returning a list of Futures holding their status and results when all complete. Future.isDone() is true for each element of the returned list

### `<T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException`
- Executes the given tasks, returning the result of one that has completed successfully (i.e., without throwing an exception), if any do
- Upon normal or exceptional return, tasks that have not completed are cancelled
- The results of this method are undefined if the given collection is modified while this operation is in progress.

### `<T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException`
- Executes the given tasks, returning the result of one that has completed successfully (i.e., without throwing an exception), if any do before the given timeout elapses. 
- Upon normal or exceptional return, tasks that have not completed are cancelled. 
- The results of this method are undefined if the given collection is modified while this operation is in progress.