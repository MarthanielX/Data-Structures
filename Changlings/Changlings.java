import java.io.*;
import java.util.*;

// takes 3 command line args: a file location for a wordlist and 2 words
// and prints the shortest path from the first to the second
// where each step changes only 1 letter in the word
public class Changlings {

   private static Map<String, Set<String>> graph;
   private static String startNode;
   private static String endNode;
   private static Map<String, String> prevNodes;

   public static void main (String[] args) throws FileNotFoundException{
      if (args.length != 3)
         throw new UnsupportedOperationException("\nThis program can only be run with 3 " +
            "command-line args: a file location and two words.\nPlease run it again with new args");
   
      // initialize class variables
      graph = new HashMap<>();
      startNode = args[1];
      endNode = args[2];
      prevNodes = new HashMap<>();      
    
      // for each word in the input file
      // checks all words in the file to see if they are connections
      // and puts a set of all connections into the map
      Scanner input = new Scanner(new File(args[0]));      
      while(input.hasNextLine()){
         String key = input.nextLine();
         putConnections(key, args[0]); 
      }
      input.close();
      
      // init all keys' values in prevNodes to "notVisited" except startNode   
      Set<String> allKeys = graph.keySet();
      for (String key : allKeys){
         prevNodes.put(key, "notVisited");
      }
      prevNodes.replace(startNode, "starting node");
   
      BFS();      
      printSolution(); 
   }
   
   // finds all connections for @param key and puts them into the graph
   private static void putConnections(String key, String fileLocation) throws FileNotFoundException{
      // check every string in the original input for connection
      Scanner tempInput = new Scanner(new File(fileLocation));
      Set<String> connections = new HashSet<>();         
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
   
   // Do a Breadth-First Search from startWord for endWord
   // Set up queue for BFS, create a map of all keys/nodes 
   private static void BFS(){   
      Queue<String> queue = new ArrayDeque<String>();
      boolean found = false;      
      queue.offer(startNode);
      // adds all unvisited connections of current Node to the queue 
      // to continue BFS; until end found or the queue runs out
      while (!found && !queue.isEmpty()){
         String currentNode = queue.poll();
         Set<String> connections = graph.get(currentNode);
         for (String nextNode : connections){
            // exit loop if found the endNode
            if (nextNode.equals(endNode)){
               prevNodes.replace(nextNode, currentNode);
               found = true;
               break; 
            }// else if nextNode not visited, visit and offer
            else if (prevNodes.replace(nextNode, "notVisited", currentNode)){
               queue.offer(nextNode);
            }
         }
      }
   }
   
   // Prints solution if the endNode was visited (solution was found) 
   private static void printSolution(){
      if (!prevNodes.get(endNode).equals("notVisited")){
         // pushes keys onto stack from end to start
         // so it can print them out from first to last
         Stack<String> stack = new Stack<String>();
         String currentNode = endNode;
         while (!prevNodes.get(currentNode).equals("starting node")){
            stack.push(currentNode);
            currentNode = prevNodes.get(currentNode);
         }
         stack.push(startNode);
         while (!stack.empty()) 
            System.out.println(stack.pop());                  
      } 
      else 
         System.out.println("No solution found.");
   }
}