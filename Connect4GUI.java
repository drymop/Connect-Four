
public class Connect4GUI extends BoardGUI{
  private double BOX_X = 56.4, BOX_Y = 54;
  /*
   * Constructs a GUI for TicTacToe. Sets up the Boxes
   */
  public Connect4GUI() {
    boxes = new Box[8];
    // move boxes
    for (int i = 0; i < 7; i++) {
      boxes[i] = new Box(53+i*BOX_X, 30, 53+(i+1)*BOX_X, 380, i+1);
    }
    // undo box
    boxes[7] = new Box(530, 320, 590, 380, -1);
  }

  public void draw(Board brd) {
    Connect4Board b = (Connect4Board) brd;
    StdDraw.setXscale(0, 600);
    StdDraw.setYscale(0, 400);
    StdDraw.clear(StdDraw.BLACK);

    StdDraw.picture(250, 215, "image/Connect4/board.png", 400, 400);
    
    for (int i = 0; i < 42; i++) {
      double x = 52.5+(i%7)*BOX_X;
      double y = 40.5+(i/7)*BOX_Y;
      switch(b.getBoardPiece(i/7, i%7)){
        case 1:
          StdDraw.picture(x+BOX_X/2, y+BOX_Y/2, "image/Connect4/redButton.png", BOX_X-7, BOX_Y-7);
          break;
        case 2:
          StdDraw.picture(x+BOX_X/2, y+BOX_Y/2, "image/Connect4/blueButton.png", BOX_X-7, BOX_Y-7);
          break;
      }
    }
    StdDraw.picture(250, 210, "image/Connect4/board.png", 400, 400);
    StdDraw.picture(560, 350, "image/Connect4/undo.png", 60, 60);
    
    // Player name
    if (brd.nextTurn() == 0) {
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.filledRectangle(525, 210, 72, 20);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.text(525, 210, title);
    } else {
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.filledRectangle(525, 170, 72, 20);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.text(525, 170, title);
    }
    
    
    StdDraw.show();
  }
}
