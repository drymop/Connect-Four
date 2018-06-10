
public class GameEndGUI extends GUI{
  public GameEndGUI() {
    boxes = new Box[2];
    //replay box
    boxes[0] = new Box(200, 100, 400, 150, 1);
    //quit box
    boxes[1] = new Box(200, 50, 400, 100, -1);
  }
  
  public void draw() {
    return;
  }
  
  /*
   * Winner of one game
   */
  public void draw(int winner) {
    StdDraw.clear();
    StdDraw.setXscale(0, 600);
    StdDraw.setYscale(0, 400);
    StdDraw.picture(300, 200, "image/menu/backgroundB.png", 600, 400);
    StdDraw.setPenColor(StdDraw.WHITE);
    if (winner != 3) {
      StdDraw.text(300, 300, "Player "+winner+" wins! Congrat!");
    } else {
      StdDraw.text(300, 300, "It's a draw!");
    }
    StdDraw.filledRectangle(300, 125, 100, 23);
    StdDraw.filledRectangle(300, 75, 100, 23);
    //boxes[0].drawBox();
    //boxes[1].drawBox();
    StdDraw.setPenColor(StdDraw.BLACK);

    StdDraw.text(300, 125, "Play Again");
    StdDraw.text(300, 75, "Quit");

    StdDraw.show();
  }

  /*
   * Ultimate winner
   */
  public void draw(int[] totWins) {
    String message;
    if (totWins[0] > totWins[1]) {
      message = "Player 1 wins!";
    } else if (totWins[0] < totWins[1]) {
      message = "Player 2 wins!";
    } else {
      message = "It's a draw!";
    }

    StdDraw.clear();
    StdDraw.picture(300, 200, "image/menu/backgroundW.png", 600, 400);
    StdDraw.text(300, 300, message);
    StdDraw.textLeft(200, 200, "Player 1: ");
    StdDraw.textLeft(200, 180, "Player 2: ");
    StdDraw.textLeft(200, 160, "Draw: ");

    StdDraw.textRight(400, 200, (totWins[0] + " wins"));
    StdDraw.textRight(400, 180, (totWins[1] + " wins"));
    StdDraw.textRight(400, 160, (totWins[2] + "        "));

    StdDraw.show();
  }
}
