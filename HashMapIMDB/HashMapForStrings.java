import java.util.*;

// public method headers by Dave Musicant
// Represents and tests a HashMap
// with key and value both Strings
public class HashMapForStrings {

   LinkedList<Entry>[] array;
   static int ARRAY_SIZE = 10;
   
   // create array, initialize all array entries to
   // empty linked lists of type Entry
   @SuppressWarnings("unchecked")
   public HashMapForStrings(){
      array = new LinkedList[ARRAY_SIZE];
      for (int i = 0; i < array.length; i++){
         array[i] = new LinkedList<Entry>();
      }
   }
   
   // overload to accept @param arraySize
   @SuppressWarnings("unchecked")
   public HashMapForStrings(int arraySize){
      ARRAY_SIZE = arraySize;
      array = new LinkedList[ARRAY_SIZE];
      for (int i = 0; i < array.length; i++){
         array[i] = new LinkedList<Entry>();
      }
   }
   
   // creates new entry, adds to list at hashValue
   public void put(String key, String value){
      Entry e = getEntry(key);
      if (e == null){
         array[hashValue(key)].add(new Entry(key, value));
      } else {
         e.setData(value);
      }
   }

   // searches list at hashValue for key and returns it
   // returns null if key isn't found
   public String getValue(String key){
      Entry e = getEntry(key);
      if (e == null) {
         return null;
      }
      return e.getData();
   }
      
   // calculates hashValue for a string
   private int hashValue(String s){
      return Math.abs(s.hashCode() % ARRAY_SIZE);
   }
   
   // returns Entry with given key, null if not there
   private Entry getEntry(String key){
      LinkedList<Entry> list = array[hashValue(key)];
      if (list != null){
         for (Entry e : list){
            if(e.getKey().equals(key)){
               return e;
            }
         }           
      }
      return null;
   }
   
   public static void main (String[] args){
      HashMapForStrings map = new HashMapForStrings();
      test(map.getValue("a") == null);
      map.put("asdf", "plot for movie asdf");
      map.put("bat.", "plot for movie bat.");
      map.put("cow!", "plot for cow!");
      map.put("dog", "plot for dog");
      map.put("elephant", "plot for elephant"); 
      test(map.getValue("asdf").equals("plot for movie asdf"));
      test(map.getValue("bat.").equals("plot for movie bat."));
      map.put("bat.", "replacement plot for bat.");   
      test(map.getValue("bat.").equals("replacement plot for bat."));
      map.put("A Really Long Titleajsdklf; ajskdlf; asjkldf; as", "long plot");
      test(map.getValue("A Really Long Titleajsdklf; ajskdlf; asjkldf; as").equals("long plot"));
      System.out.println("All Tests Pass!");
   }
      
   public static void test(boolean condition){
      if (!condition)
         throw new RuntimeException("Test failed");
   }      
}