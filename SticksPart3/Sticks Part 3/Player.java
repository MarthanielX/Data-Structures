// Player interface
// Implemented by AI and Human classes
// Written by Dave Musicant

interface Player {
   int move(int numSticks);
   void startGame();
   void endGame(boolean win);
}