public class Connect4Board extends Board {
  private long[] bitBoard = new long[2]; //each bitboard represent 1 player
  /*
  represent the board by 64-bit bitboards (only 42 bits are being used though)
  .  .  .  .  .  .  . 
  5 12 19 26 33 40 47
  4 11 18 25 32 39 46
  3 10 17 24 31 38 45
  2  9 16 23 30 37 44
  1  8 15 22 29 36 43
  0  7 14 21 28 35 42
  */

  //constants:
  private int ROW = 6, COL = 7;
  private char WPIECE = '1', BPIECE = '2';

  //tracking variables:
  private int totPiece = 0;
  private int[] count = new int[COL]; //the highest piece of each collumn

  public int getRows() {
    return ROW;
  }
  public int getCols() {
    return COL;
  }
  public int minMove() {
    return 1;
  }
  public int maxMove() {
    return 7;
  }

  public int getBoardPiece(int r, int c) {
    if ((bitBoard[0]>>(c*(ROW+1) + r) & 1) == 1) {
      return 1;
    } else if ((bitBoard[1]>>(c*(ROW+1) + r) & 1) == 1) {
      return 2;
    } else {
      return 0;
    }
  }

  /*
   * Make a move for the current player
   * Return true if the move was successfully made
   * Return false otherwise
   */
  public boolean isValidMove(int move) {
    move--;
    if (move < 0 || move >= COL || count[move] >= ROW) {
      return false;
    } return true;
  }
  public boolean makeMove(int move) {
    if (!super.makeMove(move)) {return false;}
    move--;
    bitBoard[totPiece&1] ^= 1L << move*(ROW+1)+count[move];
    count[move]++;
    totPiece++;
    return true;
  } 

  /*
   * Remove the top piece in the given column
   * Return true if the removal was successfully done
   * Return false otherwise
   */
  public boolean undoMove(int move) {
    move--;
    if (count[move] == 0) {return false;}
    bitBoard[(totPiece-1)&1] -= 1L << (move*(ROW+1)+count[move]-1);
    count[move]--;
    totPiece--;
    return true;
  }

  /*
   * Return 0 if no player has won this board.
   * Return 1 is Player 1 has won this board.
   * Return 2 if Player 2 has won this board.
   */
  public int isWinning() {
    if (isWinning(bitBoard[0])) {return 1;}
    if (isWinning(bitBoard[1])) {return 2;}
    if (totPiece >= 42) {
      return 3;}
    return 0;
  }

  private boolean isWinning(long brd) {
    long x = brd & (brd >> 1); // check vertical
    long y = brd & (brd >> ROW); // check \ diagonal
    long z = brd & (brd >> ROW+1); // check horizontal
    long t = brd & (brd >> ROW+2); // check / diagonal
    if (( x & x>>2
        | y & y>>2* ROW
        | z & z>>2*(ROW+1)
        | t & t>>2*(ROW+2))
        != 0) {
      return true;
    } return false;
  }
}