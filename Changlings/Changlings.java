import java.io.*;
import java.util.*;

// takes 3 command line args: a file location for a wordlist and 2 words
// and prints the shortest path from the first to the second
// where each step changes only 1 letter in the word
public class Changlings {

   public static void main (String[] args) throws FileNotFoundException{
   
      Map<String, Set<String>> graph = new HashMap<>();
      Scanner input = new Scanner(new File(args[0]));
      
      // for each word in the input file
      // checks all words in the file to see if they are connections
      // and puts a set of all connections into the map
      while(input.hasNextLine()){
         String key = input.nextLine(); 
         Set<String> connections = new HashSet<>();
         
         // check every string in the original input for connection
         Scanner tempInput = new Scanner(new File(args[0]));         
         while (tempInput.hasNextLine()){
            String nextWord = tempInput.nextLine();
            if ((nextWord.length() == key.length()) && (!nextWord.equals(key)))
               // for each position in key, if the rest of the strings are the same
               for (int i = 0; i < key.length(); i++){
                  if ((key.substring(0,i) + key.substring(i+1, key.length())).equals(
                  (nextWord.substring(0,i) + nextWord.substring(i+1, key.length()))))
                     connections.add(nextWord);
               }
         }
         tempInput.close();
         graph.put(key, connections);
      }
      input.close();
   
      String startNode = args[1];
      String endNode = args[2];
      
      // Do a Breadth-First Search from startWord for endWord
      // Set up queue for BFS, create a map of all keys/nodes
      // init all keys' values to null but start to "start"            
      Queue<String> queue = new ArrayDeque<String>();
      Map<String, String> prevNodes = new HashMap<>();
      Set<String> allKeys = graph.keySet();
      for (String key : allKeys){
         prevNodes.put(key, "null");
      }
      prevNodes.replace(startNode, "starting node");
      boolean found = false;
      
      queue.offer(startNode);
      while (!found && !queue.isEmpty()){
         String currentNode = queue.poll();
         Set<String> connections = graph.get(currentNode);
         for (String nextNode : connections){
            // exit loop if found the endNode
            if (nextNode.equals(endNode)){
               prevNodes.replace(nextNode, currentNode);
               found = true;
               break; 
            } 
            // else if nextNode not visited, visit and offer
            else if (prevNodes.replace(nextNode, "null", currentNode)){
               queue.offer(nextNode);
            }
         }
      }
      Scanner keyboard = new Scanner(System.in);
      while (!startNode.equals("XXXX") && !endNode.equals("XXXX")){
      if (!prevNodes.get(endNode).equals("null")){
         String currentNode = endNode;
         //Stack<String> stack = new Stack<String>();
         while (!prevNodes.get(currentNode).equals("starting node")){
            System.out.println(currentNode);
            //stack.push(currentNode);
            currentNode = prevNodes.get(currentNode);
         }
         System.out.println(startNode);
         //stack.push(startNode);
         //while (!stack.empty()) 
         //   System.out.println(stack.pop());                  
      } 
      else {
         System.out.println("No solution found.");
      }
      System.out.println("\nEnter two words, or XXXX for both words to quit.");     
      System.out.print("First Word: ");
      startNode = keyboard.next();
      System.out.print("Second Word: ");
      endNode = keyboard.next();
      }
   }
}