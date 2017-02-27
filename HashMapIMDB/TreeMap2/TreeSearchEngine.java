import java.util.*;
import java.io.*;
 
// Loads in file from command line args, puts
// the data into the Map, and then allows the
// user to retrive plots with movie prefixes
public class TreeSearchEngine {
 
   static Runtime runtime = Runtime.getRuntime();
   static double time = 0;
   static int entries = 0;
 
   public static void main(String[] args){
    
      TreeMapForStrings map = new TreeMapForStrings();
    
      // reads file from arguements, finds titles and associated
      // plots and puts into map, discards the rest of the file
      try {
      
         double startTime = System.nanoTime();    
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
         double endTime = System.nanoTime(); 
         time = endTime - startTime;
         System.out.println(time);  
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
    
   // prompts user to enter movie prefixes, prints corresponding titles and plots
   public static void promptUser(TreeMapForStrings map){
      Scanner keyboard = new Scanner(System.in);
      String prefix = "";
      System.out.print("\nEnter a prefix, or '####' to quit: ");
      prefix = keyboard.nextLine();
      while (!prefix.equals("####")){ 
         ArrayList<String> titles = map.getKeysForPrefix(prefix);
         for (String title : titles){
            System.out.println("\nTitle: " + title);
            System.out.println("Plot: " + map.getValue(title));
            /* To print plot 80 lines at a time
            System.out.println("Plot: ");
            String plot = map.getValue(title);
            for (int i = 80; i < plot.length(); i += 80){
               System.out.println(plot.substring(i-80, i));
            }
            */
         }
         System.out.print("\nEnter a prefix, or '####' to quit: ");
         prefix = keyboard.nextLine();
      }    
   }
}