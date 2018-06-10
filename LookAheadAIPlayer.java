import java.util.*;
public class LookAheadAIPlayer extends Player {

  // The number of the player (1 or 2)
  int thisPlayer;
  int lookahead;

  public LookAheadAIPlayer(int thisPlayer, int lookahead) {
    this.thisPlayer = thisPlayer;
    this.lookahead = lookahead;
  }

  public String callSign() {
    switch(lookahead) {
      case(2): return "Easy AI";
      case(4): return "Medium AI";
      case(6): return "Hard AI";
      case(8): return "Tournament AI";
    }
    //This is for you to experiment with if you want.
    return "Custom AI";
  }

  /*
   * Featurea:
   * - Negamax
   * - Alpha-beta pruning
   * - Random between equally good moves
   */

  public int decideMove(Board b) {
    ArrayList<Integer> moveList = b.validMoves();
    Collections.shuffle(moveList);
    int tempVal, bestVal = -100, bestMove = -3;
    for (int m: moveList) {
      b.makeMove(m);
      tempVal = -negaMax(b, 1, -100, 100);
      b.revert(b.numTotalMoves()-1);
      //System.out.println("move "+m+ " val "+ tempVal);
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
    int bestVal = -100, tempVal;
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
    if (b.isWinning() == thisPlayer) {
      return 100 - depth;
    } else if (b.isWinning() == 3 - thisPlayer) {
      return depth - 100;
    } else {
      return depth;
    }
  }
}