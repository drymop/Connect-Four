import java.util.ArrayList;

public class TicTacToeBoard extends Board{
  private int rows = 3;
  private int cols = 3;
  private final static char[] gamePieces = {' ','O','X'};
  //private final static char[] gamePieces = {' ','\u26AA','\u26AB'};
  //private final static char[] gamePieces = {' ','\u2620','\u2603'};

  private int curPlayer = 1;
  private int[][] gameBoard;

  public int minMove() {
    return 1;
  }

  public int maxMove() {
    return 9;
  }

  public TicTacToeBoard() {
    this.gameBoard = new int[rows][cols];
  }

  public int getRows() {
    return rows;
  }

  public int getCols() {
    return cols;
  }

  public int getBoardPiece(int r, int c) {
    return gameBoard[r][c];
  }

  public boolean isValidMove(int move) {
    move--;
    boolean ans = !( move < 0 || move > 8 || gameBoard[move/3][move%3] != 0);
    return ans;
  }

  /*
   * Make a move
   * Return true if the move was successfully made
   * Return false otherwise
   */
  public boolean makeMove(int move) {
    if( !super.makeMove(move) ){
      return false;
    }
    move--;
    gameBoard[move/3][move%3] = curPlayer;
    curPlayer = 3 - curPlayer;
    return true;
  }

  /*
   * Undo a move
   * Return true if the move was successfully made
   * Return false otherwise
   */
  public boolean undoMove(int move) {
    move--;
    if( move < 0 || move > 8 || gameBoard[move/3][move%3] == 0) {
      return false;
    }
    curPlayer = 3 - curPlayer;
    gameBoard[move/3][move%3] = 0;
    return true;
  }


  public int isWinning() {
    for(int r = 0; r < rows; r++) {
      if( (gameBoard[r][0] != 0) &&
          (gameBoard[r][0] == gameBoard[r][1]) && 
          (gameBoard[r][0] == gameBoard[r][2]) ) {
        return gameBoard[r][0];
      }
    }
    for(int c = 0; c < cols; c++) {
      if( (gameBoard[0][c] != 0) &&
          (gameBoard[0][c] == gameBoard[1][c]) && 
          (gameBoard[0][c] == gameBoard[2][c]) ) {
        return gameBoard[0][c];
      }
    }
    if( (gameBoard[0][0] != 0) &&
        (gameBoard[0][0] == gameBoard[1][1]) && 
        (gameBoard[0][0] == gameBoard[2][2]) ) {
      return gameBoard[0][0];
    }
    if( (gameBoard[0][2] != 0) &&
        (gameBoard[0][2] == gameBoard[1][1]) && 
        (gameBoard[0][2] == gameBoard[2][0]) ) {
      return gameBoard[0][2];
    }
    if( numTotalMoves() == 9 ){
      return 3; //There was a draw
    }
    return 0;
  }






}
