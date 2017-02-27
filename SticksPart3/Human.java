import java.util.Scanner;

// Class Human
// Authors: Nathaniel Sauerberg, David Galambos
// Implementation of Player interface for human players of "Game of Sticks"
public class Human implements Player {
   private String name; 
   
   public Human(String name) {
      this.name = name; 
   }
   
   // move method
   // Manages input of moves by human players
   // Takes number of sticks on board as parameter
   // Returns number of sticks after human move
   public int move(int numSticks){
      Scanner scan = new Scanner(System.in);
      System.out.println("\n" + name + ": There are " + numSticks 
         + " stick(s) on the board.");
      System.out.print("How many sticks do you take (1-3)? ");
      int taken = scan.nextInt();
      while (taken < 1 || taken > 3 || taken > numSticks) {
         System.out.println("\nPlease enter a number between 1 and 3.");
         System.out.print("How many sticks do you take (1-3)? ");
         taken = scan.nextInt();
      }
      return numSticks - taken;
   }
   
   // startGame method
   // Prints message to start human vs. human or human vs. AI game
   public void startGame(){
      System.out.println(this.name + ": Good luck! ");
   }
   
   // endGame method
   // human end-of-game actions managed in SticksGame.playGameOnce method
   public void endGame(boolean win){
   
   }
}