import java.util.*;
import java.io.*;

public class HashSearchEngineToFile {

   static Runtime runtime = Runtime.getRuntime();
   static double memory = 0;
   static double time = 0;
   static int ARRAY_SIZE = 100;
   static int entries = 0;

   static PrintWriter write;

   public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
   
      HashMapForStrings map = new HashMapForStrings(ARRAY_SIZE);
      write = new PrintWriter("HashSearchEngineNumbers.txt", "UTF-8");
   
      for (int i = 100; i < 100000000; i *= 10){
         ARRAY_SIZE = i;
         entries = 0;
      // reads file from arguements, finds titles and associated
      // plots and puts into map, discards the rest of the file
         try {
            double startTime = System.currentTimeMillis();    
            Scanner inp = new Scanner(new File(args[0]), "ISO-8859-1");
            String title;
            String plot;
            while (inp.hasNext()){
               plot = "";
               if (inp.next().equals("MV:")){
                  title = inp.nextLine();
                  title = title.replace("\"" , "");
                  title = title.substring(1); // gets rid of leading space
                  while (inp.next().equals("PL:")){
                     plot += inp.nextLine();
                  }
                  map.put(title, plot);
                  entries++;
               //System.out.println("\nTitle: " + title + "\nPlot: " + map.getValue(title));
               } 
               else {
                  inp.nextLine();
               }
            }
            memory = runtime.totalMemory() - runtime.freeMemory();
            double endTime = System.currentTimeMillis(); 
            time = endTime - startTime;  
         //promptUser(map);
            printToFile();
         }
         
         
         // for if file ends in the middle of a plot
         catch (java.util.NoSuchElementException e){
            //promptUser(map);
            printToFile();
         }
         
         catch (FileNotFoundException e) {
            System.out.println("Arguements Incorrect.\n" + e);
         } 
      }
      write.close();
   }
   
   // prompts user to enter movie titles and prints plots
   public static void promptUser(HashMapForStrings map){
      System.out.println("Array Size: " + ARRAY_SIZE);
      System.out.println("Entries: " + entries);
      System.out.println("Lambda: " + ( entries / (double) ARRAY_SIZE ) );
      System.out.println("Memory Used: " + memory);
      System.out.println("Time Taken: " + time);
      
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
   
   public static void printToFile(){
      write.println(ARRAY_SIZE);
      write.println(entries);
      write.println( ( entries / (double) ARRAY_SIZE ) );
      write.println(memory);
      write.println(time);
      write.println("");
   }
}