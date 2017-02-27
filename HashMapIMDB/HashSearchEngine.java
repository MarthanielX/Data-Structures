import java.util.*;
import java.io.*;


// Loads in file from command line args, puts
// the data into the Map, and then allows the
// user to retrive plots with movie tiles
public class HashSearchEngine {

   static Runtime runtime = Runtime.getRuntime();
   static double memory = 0;
   static double time = 0;
   static final int ARRAY_SIZE = 5000000;
   static int entries = 0;

   public static void main(String[] args){
   
      HashMapForStrings map = new HashMapForStrings(ARRAY_SIZE);
   
      // reads file from arguements, finds titles and associated
      // plots and puts into map, discards the rest of the file
      try {
         double startTime = System.currentTimeMillis();    
         Scanner inp = new Scanner(new File(args[0]), "ISO-8859-1");
         String title;
         String plot;
         // each MV: header tells you there is a title on the rest of the line
         // and the associated plot is the next several PL: lines
         // the entry ends and is put into the Map after the last PL: line
         while (inp.hasNext()){
            plot = "";
            if (inp.next().equals("MV:")){
               title = inp.nextLine();
               title = title.replace("\"" , ""); // gets rid of quotes in titles
               title = title.substring(1); // gets rid of leading space
               while (inp.next().equals("PL:")){
                  plot += inp.nextLine();
               }
               map.put(title, plot);
               entries++;
            } 
            else {
               inp.nextLine();
            }
         }
         // calculates memory useage and runtime
         memory = runtime.totalMemory() - runtime.freeMemory();
         double endTime = System.currentTimeMillis(); 
         time = endTime - startTime;  
         promptUser(map);
      }
      
      // for if file ends in the middle of a plot
      catch (java.util.NoSuchElementException e){
         promptUser(map);
      }
      
      catch (FileNotFoundException e) {
         System.out.println("Arguements Incorrect.\n" + e);
      } 
   }
   
   // prompts user to enter movie titles and prints plots
   public static void promptUser(HashMapForStrings map){
      Scanner keyboard = new Scanner(System.in);
      String title = "";
      System.out.print("\nEnter a movie title, or '####' to quit: ");
      title = keyboard.nextLine();
      while (!title.equals("####")){ 
         System.out.println("Plot: " + map.getValue(title));
         System.out.print("\nEnter a movie title, or '####' to quit: ");
         title = keyboard.nextLine();
      }    
   }
}