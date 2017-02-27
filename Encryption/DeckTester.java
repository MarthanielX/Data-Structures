import java.io.*;

public class DeckTester {

   public static void main (String[] args) throws FileNotFoundException {
      Deck cards = new Deck("deck.txt"); 
 
      cards.print(3);
      cards.print(30);
      cards.printBackwards(30);
      System.out.println(cards.nextKeyValue());
      System.out.println(cards.nextKeyValue());   
     
      
      /*
      String WORD = "ENCRYPTTHIS";
      int[] word = new int[WORD.length()];
      for (int i = 0; i < WORD.length(); i++){
         //word[i] = __________;
         word[i] = (word[i] + cards.nextKeyValue()) % 26;
      } 
      // print encrypted word
      */
       
   }
}
