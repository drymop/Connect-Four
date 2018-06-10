public class MainMenuGUI extends GUI {
  public MainMenuGUI() {
    boxes = new Box[2];
    boxes[0] = new Box(30, 20, 280, 270, 1);
    boxes[1] = new Box(320, 20, 570, 270, 2);
  }

  public void draw() {
    StdDraw.clear();
    StdDraw.setXscale(0,600);
    StdDraw.setYscale(0,400);
    StdDraw.picture(300, 200, "image/menu/backgroundW.png", 600, 400);
    StdDraw.text(300, 350, "Choose Your Game!");
    StdDraw.picture(155, 145, "image/menu/TicTacToe.png", 250, 250);
    StdDraw.picture(445, 145, "image/menu/Connect4.png", 250, 250);
    StdDraw.show();
  }
}