// class RQueue
// Recursively implements the ADT Queue
// Each queue holds a front and back value and possibly
// an inside queue with the rest of the values if needed
public class RQueue<T> implements CarlQueue<T> {

   private T front;
   private RQueue<T> inside;
   private T rear;
   
   // constructor method, same as the default constructor
   public RQueue(){
      front = null;
      inside = null;
      rear = null;
   }

   // method enqueue
   // adds the parameter to the back of the queue
   // base cases: empty or only 1 item
   // else add rear to inside queue & set rear to parameter
   @SuppressWarnings("unchecked")
   public void enqueue(T item){
      if (front == null) {       
         front = item;
      } else if (rear == null){  
         rear = item;
      } else {                
         // instantiate inside queue if necessary    
         if (inside == null) {
            inside = new <T> RQueue();
         }
         inside.enqueue(rear);
         rear = item;
      }
   }

   // method getFront
   // returns front value, no need to modify queue
   public T getFront(){
      return front;
   }

   // method dequeue
   // returns & removes first item, shifts other items forward
   // base cases: empty, only 1 item, or only 2 items
   // else return front and dequeue inside queue
   public T dequeue() {
      T toReturn;
      if (front == null){
         toReturn = null;
      } else if (rear == null){
         toReturn = front;
         front = null;
      } else if (inside == null) {
         toReturn = front;
         front = rear;
         rear = null;
      } else {
         toReturn = front;
         front = inside.dequeue();
         // delete inside queue if it's empty
         if (inside.getFront() == null) {
            inside = null; 
         }
      }
      return toReturn; 
   }

   // method Display
   // prints entire queue to console, front to back
   public void display(){
      if (front == null){
         // will only run from outside queue because
         // empty interior queues will be deleted
         System.out.println("Empty Queue!");
      } else if (rear == null){
         System.out.println(front);
      } else {
         System.out.println(front);
         if (!(inside == null)) {
            inside.display();
         }
         System.out.println(rear);
      }
   }
}