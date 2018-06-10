import java.util.*;

public class TournamentPlayer8 extends Player {

  int thisPlayer;
  int lookahead;

  public TournamentPlayer8(int thisPlayer, int lookahead) {
    this.thisPlayer = thisPlayer;
    this.lookahead = lookahead;
  }

  //Put your own screen name below.
  //Keep this clean, guys
  public String callSign() {
    return "Smarty AI";
  }

  public int decideMove(Board b) {
    ArrayList<Integer> moveList = b.validMoves();
    Collections.shuffle(moveList);
    int tempVal, bestVal = Integer.MIN_VALUE, bestMove = -3;
    for (int m: moveList) {
      b.makeMove(m);
      tempVal = -negaMax(b, 1, Integer.MIN_VALUE+1, Integer.MAX_VALUE); //Integer.MIN +1 because that's the negative of Integer.MAX
      b.revert(b.numTotalMoves()-1);
      if (tempVal > bestVal) {
        bestVal = tempVal;
        bestMove = m;
      }
    }
    return bestMove;
  }

  private int negaMax(Board b, int depth, int lowerBound, int upperBound) {
    // Terminal Nodes
    if (b.isWinning() != 0 || depth >= lookahead) {
      return (depth%2==0? 1 : -1) * heuristicEva(b, depth);
    }
    // Normal Nodes
    int bestVal = Integer.MIN_VALUE, tempVal;
    ArrayList<Integer> moveList = b.validMoves();
    Collections.shuffle(moveList);
    // Search every sub-tree
    for (int m: moveList) {
      b.makeMove(m);
      tempVal = -negaMax(b, depth+1, -upperBound, -lowerBound);
      b.revert(b.numTotalMoves()-1); // undo the move just made

      if (tempVal > bestVal) {
        bestVal = tempVal;
      }
      if (tempVal > lowerBound) {
        lowerBound = tempVal;
      }
      if (lowerBound >= upperBound) {
        break;
      }
    }
    return bestVal;
  }

  private int heuristicEva(Board b, int depth) { 
  // Evaluation in favor of thisPlayer(the AI)
  // If the score is positive (has advantage), substract depth (encourage winning with smaller depth)
  // If the score is negative or 0, add depth (wait for opponent's mistake)
    // Winning boards:
    if (b.isWinning() == thisPlayer) {
      return Integer.MAX_VALUE - depth;
    } else if (b.isWinning() == 3 - thisPlayer) {
      return Integer.MIN_VALUE + depth + 1;
    } else if (b.isWinning() == 3) {
      return depth;
    }
    // Inconclusive boards criteria:

    // Criteria:
    // +1000: Great bonus for opponent having no threat (if both have 0 threat than they would cancel each other out)
    // +100 for a threat
    // -100 Penalty for threat above opponent's good threat (useless threat)
    // +2000: Great bonus for having 2 threat on top of each other (almost sure win)
    // +500: Bonus for having "good" threats (odd threat for player1, even for player2)
    
    // generate 2 maps of threats (any square that if filled can create a 4-in-a-row)
    int score = 0;

    long[] threatMap = findThreat(boardToBitboards(b));
    if (threatMap[1] == 0) {score += 1000;}
    if (threatMap[0] == 0) {score -= 1000;}
    if ((threatMap[0] & (threatMap[0]<<1)) != 0) {score += 2000;}
    if ((threatMap[1] & (threatMap[1]<<1)) != 0) {score -= 2000;}

    long t = threatMap[0];
    while (t != 0) {
      if (Long.numberOfTrailingZeros(t)%7%2 == 0) {
        score += 500;
      } else {
        score += 100;
      }
      t &= t - 1;
    }
    t = threatMap[1];
    while (t != 0) {
      if (Long.numberOfTrailingZeros(t)%7%2 == 1) {
        score -= 500;
      } else {
        score -= 100;
      }
      t &= t - 1;
    }

    if (thisPlayer == 2) {
      score = -score;
    }
    if (score < 0) {
      score += depth;
    } else if (score > 0) {
      score -= depth;
    }
    return score;
  }

  private long[] boardToBitboards(Board brd) {
    Connect4Board b = (Connect4Board) brd;
    long[] bitboard = new long[] {0L, 0L}; //bitboards for white and black
      /*6 13 20 27 34 41 48
        --------------------
        5 12 19 26 33 40 47 
        4 11 18 25 32 39 46 
        3 10 17 24 31 38 45 
        2  9 16 23 30 37 44 
        1  8 15 22 29 36 43 
        0  7 14 21 28 35 42 */
    for (int c = 0; c < 7; c++) {
      for (int r = 0; r < 6; r++) {
        if (b.getBoardPiece(r, c) == 1) {
          bitboard[0] += 1L<<(c*7+r);
        } else if (b.getBoardPiece(r, c) == 2) {
          bitboard[1] += 1L<<(c*7+r);
        }
      }
    } return bitboard;
  }
  private long[] findThreat(long[] boards) { 
  // find all connect3 (except vertical)
    long threatMap[] = new long[] {0L, 0L};
    long empty =  ~(boards[0] | boards[1]) & 279258638311359L; // the number is a full board of Connect4
    for (int i = 0; i < 2; i++) {
      // horizontal
      threatMap[i] |= (boards[i]>>7) & (boards[i]>>14) & (boards[i]>>21) & empty;
      threatMap[i] |= (boards[i]>>7) & (boards[i]>>14) & (boards[i]<<7) & empty;
      threatMap[i] |= (boards[i]>>7) & (boards[i]<<7) & (boards[i]<<14) & empty;
      threatMap[i] |= (boards[i]<<7) & (boards[i]<<14) & (boards[i]<<21) & empty;
      // diagonal "/"
      threatMap[i] |= (boards[i]>>8) & (boards[i]>>16) & (boards[i]>>24) & empty;
      threatMap[i] |= (boards[i]>>8) & (boards[i]>>16) & (boards[i]<<8) & empty;
      threatMap[i] |= (boards[i]>>8) & (boards[i]<<8) & (boards[i]<<16) & empty;
      threatMap[i] |= (boards[i]<<8) & (boards[i]<<16) & (boards[i]<<24) & empty;
      // diagonal "\"
      threatMap[i] |= (boards[i]>>6) & (boards[i]>>12) & (boards[i]>>18) & empty;
      threatMap[i] |= (boards[i]>>6) & (boards[i]>>12) & (boards[i]<<6) & empty;
      threatMap[i] |= (boards[i]>>6) & (boards[i]<<6) & (boards[i]<<12) & empty;
      threatMap[i] |= (boards[i]<<6) & (boards[i]<<12) & (boards[i]<<18) & empty;
    }
    return threatMap;
  }
}
