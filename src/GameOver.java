import java.awt.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import tester.*;
import java.util.Random;

class GameOver extends ASimonWorld{
  
  GameOver(Random rand, ILoColor currentColors, ColorScheme squareColors, int brightSquare) {
    super(rand, currentColors, squareColors, brightSquare);
    //this.score = score;
  }
  
  /* Template
   * Fields:
   * ... score ...                       -- int
   * ... rand ...                        -- Random
   * ... currentColors ...               -- ILoColor
   * ... squareColors ...                -- ColorScheme
   * ... brightSquare ...                -- int
   * Methods:
   * ... makeScene() ...                 -- WorldScene
   * ... onMouseReleased(Posn pos) ...   -- ASimonWorld
   * Methods on Fields:
   * ... squareColors.makeDarker() ...   -- ColorScheme
   */

  @Override
  //draw the game over screen
  public WorldScene makeScene() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    
    TextImage score = new TextImage("Score: " + String.valueOf(this.currentColors.getLength() - 1),
        24, Color.BLACK);
    TextImage reset = new TextImage("Click anywhere to reset.", 24, Color.BLACK);
    TextImage exit = new TextImage("Exit by closing the window.", 24, Color.BLACK);
    
    return this.getEmptyScene().placeImageXY(score, 200, 200).placeImageXY(reset, 
        200, 230).placeImageXY(exit, 200, 260);
  }
  
  // reset the game from anywhere on the screen
  public ASimonWorld onMouseReleased(Posn pos) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... pos ...   -- Posn
     */
    return new ObservingWorld(new MtLoColor(), new MtLoColor(), 
      this.squareColors.makeDarker(), false, 0);
  }
}
