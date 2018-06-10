public class ChoosePlayerGUI extends GUI {
  private int select1 = 1, select2 = 2;

  public ChoosePlayerGUI() {
    boxes = new Box[13];
    for (int i = 0; i < 12; i++) {
      boxes[i] = new Box((i%2==0? 155 : 415), (285-(i/2)*35), (i%2==0? 185 : 445), (315-(i/2)*35), i+1);
    }

    boxes[12] = new Box(200, 25, 400, 75, -1);
  }

  public void select(int x) {
    if (x<1 || x>12) {return;}
    if (x%2==1) {
      select1 = x;
    } else {
      select2 = x;
    }
  }

  public int getSelection1() {
    return select1;
  }
  public int getSelection2() {
    return select2;
  }

  public void draw() {
    StdDraw.clear();
    StdDraw.setXscale(0,600);
    StdDraw.setYscale(0,400);
    StdDraw.picture(300, 200, "image/menu/backgroundW.png", 600, 400);
    
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(170, 350, "Player 1");
    StdDraw.text(430, 350, "Player 2");

    StdDraw.text(300, 300, "Human");
    StdDraw.text(300, 265, "Random");
    StdDraw.text(300, 230, "Easy");
    StdDraw.text(300, 195, "Medium");
    StdDraw.text(300, 160, "Hard");
    StdDraw.text(300, 125, "Tournament");

    for (int i = 0; i < 12; i++) {
      if (i == select1-1 || i == select2-1) {
        StdDraw.picture( (i%2==0? 170 : 430), (300-(i/2)*35) ,"image/menu/button1.png", 25, 25);
      } else {
        StdDraw.picture( (i%2==0? 170 : 430), (300-(i/2)*35) ,"image/menu/button0.png", 25, 25);
      }
    }
    StdDraw.picture(300, 50, "image/menu/playButton.png", 200, 50);
    /*for (int i = 0; i < 13; i++) {
      boxes[i].drawBox();
    }*/

    StdDraw.show();
  }
}
