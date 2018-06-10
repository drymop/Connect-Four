import java.util.*;
public class HumanPlayer extends Player {

  // The GUI that the Human uses to input/output
  BoardGUI myGUI;
  // The number of the player (1 or 2)
  int number;

  public HumanPlayer(int number, BoardGUI myGUI) {
    this.number = number;
    this.myGUI = myGUI;
  }

  public String callSign() {
    return "Player "+number;
  }

  //Returns -1 for an undo, or any other move
  // This may not work if you have animations
  public int decideMove(Board b) {
    int move;
    while(true) {
      //To get animations to work, do another clear/draw/show right here
      move = myGUI.getMove();
      if(move != 0 && (move==-1 || b.isValidMove(move))) {
        return move;
      }
    }
  }


}
