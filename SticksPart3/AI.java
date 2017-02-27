import java.util.Arrays;
import java.lang.Number;

// Class AI
// Authors: Nathaniel Sauerberg, David Galambos
/* Implementation of Player interface for "dumb"
   and trained AI in "Game of Sticks" */

public class AI implements Player {

   private String name;
   private int[][] options;   //2D array: Possible board states, Moves at each state
   private int[] chosen;      //Moves made this game
   private boolean print;     //Toggles print so can disable while training AI

   public AI (String n, int sticks){
      name = n;
      options = new int[sticks][3];
      print = true;
      
      //initialize "balls" in each hat to 1
      for (int i = 0; i < options.length; i++){
         for (int j = 0; j < options[j].length; j++){
            options[i][j] = 1;
         }
      }
      
      chosen = new int[sticks];
   }
     
   // getPrint method
   // returns value of boolean print
   public boolean getPrint(){
      return print; 
   }
   
   // setPrint method
   // Allows SticksGame to set value of boolean print
   public void setPrint( boolean p){
      print = p;
   }

   // move method
   // Manages movement of AI
   // Takes number of sticks on board as parameter
   // Returns number of sticks after move
   public int move(int numSticks){
      if (print){
         System.out.println("\n" + name + ": There are " + numSticks 
            + " stick(s) on the board.");
      }
      
      int choice = Integer.MAX_VALUE;
      
      while (choice > numSticks){
         int random = (int)(Math.random() * (options[numSticks-1][0] 
            + options[numSticks-1][1] + options[numSticks-1][2]) +1);
         if (random <= options[numSticks-1][0]){
            choice = 1;
         } 
         else if (random <= (options[numSticks-1][0] + options[numSticks-1][1])){
            choice = 2;
         } 
         else {
            choice = 3;
         }
      }
      
      chosen[numSticks-1] = choice;
      if (print){
         System.out.println(name + " selects " + choice + " sticks.");
      }
      return (numSticks-choice);
   }
      
   // method startGame
   // Prints message in human vs. AI game
   public void startGame(){
      System.out.println(name + " says 'I, the AI, will hope to defeat you!'");
   }
   
   // method endGame
   /* takes boolean win as parameter 
      (win is relative to current player object)*/
   /* Prints end-of-game messages & modifies arrays 
      chosen & options based on win/loss*/
   public void endGame(boolean win){
      if (win){
         if (print){
            System.out.println("\n" + name + " says 'Good game! Better luck next time!'\n");
         }
         // increase winning numbers in hats
         for (int i = 0; i < chosen.length; i++){
            if (chosen[i] > 0){
               options[i][chosen[i]-1]++;
            }
         }     
      } 
      
      else /* loss */ {
         if (print){
            System.out.println("\n" + name + " says 'That was fun, thank you!'");
         }
         
         // decrease losing numbers in hats
         for (int i = 0; i < chosen.length; i++){
            if (chosen[i] > 0 && options[i][chosen[i]-1] > 1){
               options[i][chosen[i]-1]--;
            }
         }
      }
      
      if (print){
         System.out.println("Moves from this game: " + Arrays.toString(chosen));
         System.out.println("Updated hats: ");
         printHats();
      }
      
      //reset all of array chosen to 0 for next game
      for (int i = 0; i < chosen.length; i++){
         chosen[i] = 0;
      }
   }
   
   // method getOptions
   // returns options array
   // getter method to pass hats into SticksGame
   public int[][] getOptions() {
      return options;
   }
   
   // method printHats
   // prints formatted options at each board position
   public void printHats() {
      for (int i = 0; i < options.length; i++){
         System.out.println((i+1) + " stick(s): " + Arrays.toString(options[i]));
      }      
   }
}