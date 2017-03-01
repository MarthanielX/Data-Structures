import java.util.*;
import java.io.File;
import java.io.IOException; 

// Lisp1 class
// By Nathaniel Sauerberg, David Galambos
// Implement an ArrayDeque stack to evaluate Lisp expressions
public class Lisp1 {
   
   private static Deque<String> stack = new ArrayDeque<>(); 
   
   // evaluate method
   // create a 2nd stack, add items from 1st stack, evaluate
   // Double.parseDouble() from StackOverflow
   private static void evaluate (){
      Deque<String> tempStack = new ArrayDeque<>();
      
      //add items to 2nd stack until hit an open parens
      String temp = stack.removeFirst(); 
      while(!temp.equals("(")) {
         tempStack.addFirst(temp);
         temp = stack.removeFirst();
      }
      
      //do operation on temp stack until empty, add result to main stack
      String operator = tempStack.removeFirst();
      double value = Double.parseDouble(tempStack.removeFirst());
      if (operator.equals("+")){
         while (!tempStack.isEmpty()){
            value += Double.parseDouble(tempStack.removeFirst());
         }
      } 
      else if (operator.equals("-")){
         while (!tempStack.isEmpty()){
            value -= Double.parseDouble(tempStack.removeFirst());
         }
      } 
      else if (operator.equals("*")){
         while (!tempStack.isEmpty()){
            value *= Double.parseDouble(tempStack.removeFirst());
         }
      } 
      else if (operator.equals("/")){
         while (!tempStack.isEmpty()){
            value /= Double.parseDouble(tempStack.removeFirst());
         }
      } 
      stack.addFirst(value+"");
   }

   // main method
   // File scanner code written by Dave Musicant
   public static void main(String[] args) throws IOException {
      Scanner input = new Scanner(new File(args[0]));
      while (input.hasNext()) {
         String symbol = input.next();
         if (!symbol.equals(")")) {
            stack.addFirst(symbol); 
         } 
         else {
            evaluate();
         }
      } 
      System.out.println(stack.peekFirst());          
   }
}