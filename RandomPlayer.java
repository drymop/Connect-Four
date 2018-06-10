import java.util.*;

public class RandomPlayer extends Player{

  /* Fields */
  Random rand;

  /* Methods */
  public RandomPlayer() {
    this.rand = new Random();
  }

  public String callSign() {
    return "Random";
  }

  public int decideMove(Board b) {
    ArrayList<Integer> valids = b.validMoves();
    return valids.get(rand.nextInt(valids.size())).intValue();
  }
}
