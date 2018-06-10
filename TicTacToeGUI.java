import java.awt.Color;
public class TicTacToeGUI extends BoardGUI{

  /*
   * Constructs a GUI for TicTacToe. Sets up the Boxes for the way it works
   */
  public TicTacToeGUI() {
    boxes = new Box[10];
    for(int r = 0; r < 3; r++) {
      for(int c = 0; c < 3; c++) {
        boxes[3*r+c] = new Box(c,r,c+1,r+1,3*r+c+1);
      }
    }
    //Undo Box
    boxes[9] = new Box(3.75,1,4.75,2,-1);
  }

  public void draw(Board b) {
    StdDraw.setXscale(-1.5,4.5);
    StdDraw.setYscale(0,4);
    StdDraw.clear();
    //Draw lines
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.line(0,1,3,1);
    StdDraw.line(0,2,3,2);
    StdDraw.line(1,0,1,3);
    StdDraw.line(2,0,2,3);

    for(int r = 0; r < 3; r++) {
      for(int c = 0; c < 3; c++) {
        switch(b.getBoardPiece(r,c)){
          case 1: 
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(c+.5,r+.5,.375);
            break;
          case 2: 
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(c+.5,r+.5,.375);
            break;
        }
      }
    }

    StdDraw.setPenColor(titleColor);
    StdDraw.text(1.5,3.8,title);
    //Draw UndoBox
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.rectangle(3.75,1.5,.5,.5);
    StdDraw.text(4,1.5,"Undo");
    StdDraw.show();
  }
}
