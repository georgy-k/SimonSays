import java.awt.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import tester.*;
import java.util.Random;


class PlayingWorld extends ASimonWorld {
  ILoColor playerInputs;
  
  
  PlayingWorld(ILoColor playerInputs, Random rand, ILoColor currentColors, ColorScheme squareColors, int brightSquare) {
    super(rand, currentColors, squareColors, brightSquare);
    this.playerInputs = playerInputs;
  }
  
  /* Template
   * Fields:
   * ... playerInputs ...                                                   -- ILoColor
   * ... rand ...                                                           -- Random
   * ... currentColors ...                                                  -- ILoColor
   * ... squareColors ...                                                   -- ColorScheme
   * ... brightSquare ...                                                   -- int
   * Methods:
   * ... onMousePressed(Posn pos) ...                                       -- ASimonWorld
   * ... onMouseRelease(Posn pos) ...                                       -- World
   * ... addNewColor(int whichColor) ...                                    -- ASimonWorld
   * Methods on Fields:
   * ... currentColors.sameSequence(ILoColor other) ...                     -- boolean
   * ... squareColors.makeDarker() ...                                      -- ColorScheme
   * ... currentColors.startsWith(ILoColor given) ...                       -- boolean
   * ... currentColors.getLength() ...                                      -- int
   * ... playerInputs.newColor(int randNum, ColorScheme squareColors) ...   -- ILoColor
   * ... squareColors.makeBrighter(int whichSquare) ...                     -- ColorScheme
   */
  
  // Brightens and adds a color to the player inputs list depending on the square the player clicks
  public ASimonWorld onMousePressed(Posn pos) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... pos ...   -- Posn
     */
    if (pos.x > 0 && pos.x <= 200 && pos.y > 0 && pos.y <= 200) {
      return this.addNewColor(0);
    }
    if (pos.x > 200 && pos.x <= 400 && pos.y > 0 && pos.y <= 200) {
      return this.addNewColor(1);
    }
    if (pos.x > 0 && pos.x <= 200 && pos.y > 200 && pos.y <= 400) {
      return this.addNewColor(2);
    }
    if (pos.x > 200 && pos.x <= 400 && pos.y > 200 && pos.y <= 400) {
      return this.addNewColor(3);
    } else {
    return this;
    }
  }
  
  // Darkens the clicked square and evaluates the two color lists when the player releases
  public ASimonWorld onMouseReleased(Posn pos) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... pos ...   -- Posn
     */
    if (this.currentColors.sameSequence(this.playerInputs)) {
      return new ObservingWorld(this.rand, this.playerInputs, new MtLoColor(), 
          this.squareColors.makeDarker(), false, 0);
    }
    if (this.currentColors.startsWith(this.playerInputs)) {
      return new PlayingWorld(this.playerInputs, this.rand, this.currentColors, 
          this.squareColors.makeDarker(), 0);
    } else {
      return new GameOver(this.rand, this.currentColors, this.squareColors, this.brightSquare);
    }
  }
  
  // Brightens the correct square and adds the correst color to the list
  ASimonWorld addNewColor(int whichColor) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... whichColor ...   -- int
     */
    return new PlayingWorld(this.playerInputs.newColor(whichColor, this.squareColors), this.rand, 
        this.currentColors, this.squareColors.makeBrighter(whichColor + 1), whichColor + 1);
  } 
 
}


