
public abstract class BoardGUI extends GUI {
  // Draw does nothing for a BoardGui.
  public void draw(){
    return;
  }

  abstract void draw(Board b);

} 
