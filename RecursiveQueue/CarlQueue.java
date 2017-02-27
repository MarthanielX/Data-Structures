// Written by Dave Musicant

public interface CarlQueue<T>
{
    // Add item to the queue
    void enqueue(T item);

    // Return next item to come out of queue, without changing it.
    // If the queue is empty, return null
    T getFront();

    // Remove the next item from the queue. If the queue is empty, return null.
    T dequeue();

    // Print the contents of the queue to the screen in some cleanly
    // readable way, to help you in debugging and us in grading.
    void display();
}