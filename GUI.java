/*
 * GUI stands for graphical user interface. It controls all the user
 * input/output.
 * It should be separate from the "model" that stores/manipulates data. 
 */
import java.awt.Color;
public abstract class GUI {
  protected String title = "";
  protected Color titleColor = StdDraw.BLACK;

  //Available to subtypes
  protected Box[] boxes;

  public void setTitleAndColor(String title, Color titleColor) {
    this.title = title;
    this.titleColor = titleColor;
  }

  /*
   * This something that the main game should call
   * Returns 0 if no valid move has been made.
   * Returns an integer representing the move if one has been made
   * This should work for any type of GUI -> the code gets written here.
   */
  public int getMove(){
    if(StdDraw.mousePressed()) {
      while(StdDraw.mousePressed()) {
      }
      double x = StdDraw.mouseX();
      double y = StdDraw.mouseY();
      for(int i=0; i < boxes.length; i++) {
        if(boxes[i].contains(x,y)) {
          return boxes[i].getBoxcode();
        }
      }
    }
    return 0;
  }

  /*
   * display output to the screen
   */
  abstract void draw();

  //Available to subtypes
  protected class Box{
    private double x0,y0,x1,y1;
    private int boxcode;

    public Box(double x0, double y0, double x1, double y1, int boxcode) {
      this.x0 = x0;
      this.y0 = y0;
      this.x1 = x1;
      this.y1 = y1;
      this.boxcode = boxcode;
    }

    /*
     * Return true if this Box contains this point
     */
    public boolean contains(double x, double y) {
      return(x0 < x && x < x1 && y0 < y && y < y1);
    }

    public int getBoxcode() {
      return boxcode;
    }

    public void drawBox() {
      StdDraw.rectangle((x0+x1)/2, (y0+y1)/2, Math.abs(x1-x0)/2, Math.abs(y1-y0)/2);
    }

  }




}
