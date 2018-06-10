// Abstract Class for any 2-Dimensional game board.
import java.util.ArrayList;

// It is entirely up to you to decide how you want to store the game
// state, what variables you want you use, what helper methods you want to
// write, etc.
// Your code will be graded primarily on correctness. It will also be graded
// on good style, efficiency, organization, and proper encapsulation

public abstract class Board {

  //We don't really use there anymore
  private char vertLine = '|'; 
  private char horzLine = '-'; 
  private char crossLine = '+'; 
  //Added for lab 9
  private ArrayList<Integer> history = new ArrayList<Integer>();

  /*
   * Return the total number of rows of the Board
   */
  abstract int getRows();

  /*
   * Return the total number of Columns of the Board
   */
  abstract int getCols();

  /*
   * Return the piece at row r, column c on the board.
   * Row 0 means the bottom row
   * Column 0 means the left most column
   */
  abstract int getBoardPiece(int r, int c);

  /*
   * Make a move for the current player
   * Return true if the move was successfully made
   * Return false otherwise
   *
   * Edited for lab 9
   * Your subclasses should call super.makeMove as part of their own makeMove
   * methods.
   */
  public boolean makeMove(int move){
    if(!isValidMove(move)) {
      return false;
    }
    history.add(new Integer(move));
    return true;
  }

  /*
   * Remove the top piece in the given column
   * Return true if the removal was successfully done
   * Return false otherwise
   */
  abstract boolean undoMove(int move);

  /*
   * Return 0 if no player has won this board.
   * Return 1 is Player 1 has won this board.
   * Return 2 if Player 2 has won this board.
   */
  abstract int isWinning();

  abstract boolean isValidMove(int move);

  abstract int minMove();

  abstract int maxMove();

  public ArrayList<Integer> validMoves() {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = minMove(); i <= maxMove(); i++) {
      if (isValidMove(i)) {
        list.add(new Integer(i));
      }
    }
    return list;
  }

  public int numTotalMoves(){
    return history.size();
  }

  public int nextTurn(){
    return numTotalMoves()%2;
  }

  public void revert(int moveNum) {
    if (moveNum < 0) {
      moveNum = 0;
    }
    int i = history.size();
    while (i > moveNum) {
      undoMove(history.get(i-1));
      history.remove(i-1);
      i--;
    }
  }


  /*
   * Return a String representation of the current board state.
   * It should be understandable enough for two players to play the game.
   * We don't really use this anymore
   */
  public String toString() {
    //A StringBuilder is an advanced way to avoid creating lots of string
    //objects as you do your addition.
    int rows = getRows();
    int cols = getCols();
    StringBuilder s = new StringBuilder(2*(2*rows+1)*(cols+1));
    s.append(crossLine);
    for(int c = 0; c < cols; c++) {
      s.append(horzLine);
      s.append(crossLine);
    }
    s.append('\n');
    for(int r = rows-1; r >= 0; r--) {
      for(int c = 0; c < cols; c++) {
        s.append(vertLine);
        s.append(getBoardPiece(r,c));
      }
      s.append(vertLine);
      s.append('\n');
      //A horizontal Line
      s.append(crossLine);
      for(int c = 0; c < cols; c++) {
        s.append(horzLine);
        s.append(crossLine);
      }
      s.append('\n');
    }
    return s.toString();
  }

}
