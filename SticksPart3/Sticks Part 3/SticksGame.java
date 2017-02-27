import java.util.Scanner;
import java.util.Arrays;
 
 // Class SticksGame
 // Authors: Nathaniel Sauerberg, David Galambos, Dave Musicant
 // Class used to run the game, contains main method
public class SticksGame {

   private static final int ROUNDS_TO_TRAIN = 100000; 
   
   // playGameOnce method
   // Based on method written by Dave Musicant
   // Manages game by calling turns/moves for players at appropriate time
   /* Takes Human/AI objects, initial number of sticks on board, and 
      print toggle as parameters*/
   public static void playGameOnce(Player player1, Player player2, int totalSticks, boolean print){
      int turn = 0;
      if (print){
         System.out.println("");
         player1.startGame();    
         player2.startGame();
      }

      /*can't combine if and else efficiently because loser
        has to be printed before winner */
      while(totalSticks > 0){
         if (turn % 2 == 0){
            totalSticks = player1.move(totalSticks);
            if (totalSticks == 0){
               if (print){
                  System.out.println("\nPlayer 1: You lose.");
                  System.out.println("Player 2: You win!");
               }
               player1.endGame(false);
               player2.endGame(true);
            }
         } 
         else {
            totalSticks = player2.move(totalSticks);
            if (totalSticks == 0){
               if (print){
                  System.out.println("\nPlayer 2: You lose.");
                  System.out.println("Player 1: You win!");
               }
               player2.endGame(false);
               player1.endGame(true);
            }
         }
         turn++;
      }
   }   
      
// main method
// Based on method written by Dave Musicant
   public static void main(String[] args){
      // Initial setup
      System.out.println("Welcome to the game of sticks!");
      Scanner scanner = new Scanner(System.in);
      System.out.print("How many sticks are there on the table initally? (10-100)? ");
      int totalSticks = scanner.nextInt();
      //error checking loop
      while (totalSticks < 10 || totalSticks > 100) {
         System.out.println("\nPlease enter a number between 10 and 100.");
         System.out.print("How many sticks are there on the table initally? (10-100)? ");
         totalSticks = scanner.nextInt();
      }
      
      //error checking loop
      int option = -1;
      while (option >= 4 || option <= 0){
         System.out.println("\nOptions:");
         System.out.println("Play against a friend (1)");
         System.out.println("Play against the computer (2)");
         System.out.println("Play against a trained AI (3)");
         System.out.print("Which option do you take (1-3)? ");
         option = scanner.nextInt();
      }
      
      /* Dummy players need to be instantiated outside
         of if block to make compiler happy */
      Player player1 = new Human("Error");
      Player player2 = new Human("Error"); 
      Player player3 = new Human("Error"); 
      
      if (option == 1){
         player1 = new Human("Player 1");
         player2 = new Human("Player 2");
      }
      else if (option == 2){
         player1 = new Human("Player 1");
         player2 = new AI("Player 2", totalSticks);
      } 
      else if (option == 3) {
         player1 = new Human("Player 1");
         player2 = new AI("Player 2", totalSticks);
         player3 = new AI("AI Trainer", totalSticks);
         ((AI)(player2)).setPrint(false);
         ((AI)(player3)).setPrint(false);
         System.out.println("\nTraining AI, please wait...");
         for (int i = 0; i < ROUNDS_TO_TRAIN; i++){
            playGameOnce(player2, player3, totalSticks, false);
         }
         ((AI)(player2)).setPrint(true);
         System.out.println("AI successfully trained. Resulting Hats:");
         ((AI)(player2)).printHats();
      }
      else {
         System.out.println("Bad choice.");
      }
      
      boolean again = true; 
      while (again) {
         playGameOnce(player1, player2, totalSticks, true);
         System.out.print("\nEnter 0 to exit, or any other integer to play again: ");
         if (scanner.nextInt()== 0){
            again = false; 
         }
      }
   }
}