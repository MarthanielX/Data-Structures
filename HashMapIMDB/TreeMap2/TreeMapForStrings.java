import java.util.ArrayList;

// accepts entries with String keys and data,
// stores them in a Map, and allows access
// some public methods by Dave Musicant
public class TreeMapForStrings {
   
   private Node root;
   
   // represents a node of a BST with String key & data
   private class Node {
   
      private String key;
      private String data;
      private Node leftChild;
      private Node rightChild;
      
      public Node (String k, String d){
         key = k;
         data = d;
         leftChild = null;
         rightChild = null;
      }
   
   }
   
   // basic constructor
   public TreeMapForStrings(){
      root = null;
   }
   
   // idea for structure of put methods from Dave Musicant
   // stores the key and associated value in the BST
   public void put (String key, String value){
      root = put(key, value, root);
   }
   
   // base case: creates and returns new Node
   // otherwise calls itself on subtree and
   // updates its children to the return
   private Node put (String key, String value, Node subroot){
      if (subroot == null) {
         subroot = new Node(key, value);
      } 
      else if (subroot.key.compareTo(key) < 0){
         subroot.leftChild = put(key, value, subroot.leftChild);
      } 
      else if (subroot.key.compareTo(key) > 0){
         subroot.rightChild = put(key, value, subroot.rightChild);
      } 
      else {
         subroot.data = value;
      }
      return subroot;
   } 
   
   // returns the data (plot) assiciated with param key
   public String getValue(String key){
      return getValue(key, root);
   }
   
   // base case: subroot is null; key not in DST, returns null
   // else recursive call on relevent child
   // or returns data of subroot if key has been found
   public String getValue (String key, Node subroot){
      if (subroot == null){ 
         return null;
      } else if (subroot.key.compareTo(key) < 0){
         return getValue(key, subroot.leftChild);
      } else if (subroot.key.compareTo(key) > 0){
         return getValue(key, subroot.rightChild);
      } else {
         return subroot.data;
      }
   } 
   
   // returns keys of all entries that begin with param prefix
   public ArrayList<String> getKeysForPrefix(String prefix){
      ArrayList<String> keys = new ArrayList();
      getKeysForPrefix(prefix, root, keys);
      return keys;
   }
   
   // recursive; base case; empty subroot --> do nothing
   // else compare beginning prefix length part of subroot's key to prefix
   // and do recusive call on relevent child if key < || > prefix or
   // if key == prefix add key to ArrayList and call on both children
      private void getKeysForPrefix(String prefix, Node subroot, ArrayList<String> keys){
      if (subroot == null){
         // do nothing
      } else if (subroot.key.substring(0,prefix.length()).compareTo(prefix) > 0 ){
         getKeysForPrefix(prefix, subroot.rightChild, keys);
      } else if (subroot.key.substring(0,prefix.length()).compareTo(prefix) < 0 ){
         getKeysForPrefix(prefix, subroot.leftChild, keys);
      } else {
         keys.add(subroot.key);
         getKeysForPrefix(prefix, subroot.leftChild, keys);
         getKeysForPrefix(prefix, subroot.rightChild, keys);
      }
   }
   
   // main method tests the code
   public static void main (String[] args){
      TreeMapForStrings map = new TreeMapForStrings();
      
      // tests put and getValue
      test(map.getValue("a") == null);
      map.put("cow!", "plot for cow!");
      map.put("dog", "plot for dog");
      map.put("asdf", "plot for movie asdf");
      map.put("bat.", "plot for movie bat.");
      map.put("elephant", "plot for elephant"); 
      test(map.getValue("asdf").equals("plot for movie asdf"));
      test(map.getValue("bat.").equals("plot for movie bat."));
      map.put("bat.", "replacement plot for bat.");   
      test(map.getValue("bat.").equals("replacement plot for bat."));
      map.put("A Really Long Titleajsdklf; ajskdlf; asjkldf; as", "long plot");
      test(map.getValue("A Really Long Titleajsdklf; ajskdlf; asjkldf; as").equals("long plot"));
      
      // tests getKeysForPrefix
      test(map.getKeysForPrefix("z").isEmpty());
      map.put("ba", "asdf");
      map.put("bee", "asdf");
      map.put("bzzz", "asdf");
      map.put("byeee", "asdf");
      map.put("bat", "asdf");
      test(map.getKeysForPrefix("b").contains("bat."));
      test(map.getKeysForPrefix("b").contains("bzzz"));
      test(map.getKeysForPrefix("b").contains("bee"));
      test(map.getKeysForPrefix("b").contains("bat"));
      test(!map.getKeysForPrefix("b").contains("dog"));
      System.out.println("All Tests Pass!");
   }
      
   public static void test(boolean condition){
      if (!condition) throw new RuntimeException("Test failed");
   }      
   
}


