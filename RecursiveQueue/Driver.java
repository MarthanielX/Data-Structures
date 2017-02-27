public class Driver {
   public static void main (String [] args){
      RQueue <String> r = new RQueue<>();
      System.out.println(r.dequeue());
      r.enqueue("a");
      r.enqueue("b");
      r.enqueue("c");
      r.enqueue("d");
      r.enqueue("e");
      r.enqueue("f");
      r.enqueue("g");
      r.enqueue("h");
      r.enqueue("i");
      r.enqueue("j");
      r.enqueue("k");
      r.enqueue("l");
      r.display();
      System.out.println("\nRemove: " + r.dequeue());
      r.display();
      System.out.println("Front: " + r.getFront());
      System.out.println("\nRemoved: " + r.dequeue());
      r.enqueue("m");
      System.out.println("\nRemoved: " + r.dequeue());
      System.out.println("\nRemoved: " + r.dequeue());
      r.enqueue("n");
      r.enqueue("o");
      r.enqueue("p");
      r.display();  
      System.out.println("Front: " + r.getFront());
   } 
}