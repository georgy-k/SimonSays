import java.awt.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import tester.*;
import java.util.Random;

class ObservingWorld extends ASimonWorld {
  boolean newColorAdded;
  ILoColor runThrough;
  
  ObservingWorld(ILoColor currentColors, ILoColor runThrough, ColorScheme squareColors, 
      boolean newColorAdded, int brightSquare) {
    this(new Random(), currentColors, runThrough, squareColors, newColorAdded, brightSquare);
  }
  
  ObservingWorld(Random rand, ILoColor currentColors, ILoColor runThrough, 
      ColorScheme squareColors, boolean newColorAdded, int brightSquare) {
    super(rand, currentColors, squareColors, brightSquare);
    this.runThrough = runThrough;
    this.newColorAdded = newColorAdded;
  }
  
  /* Template:
   * Fields:
   * ... rand ...                       -- Random
   * ... currentColors ...              -- ILoColor
   * ... runThrough ...                 -- ILoColor
   * ... squareColors ...               -- ColorScheme
   * ... newColorAdded ...              -- boolean
   * ... brightSquare ...               -- int
   * Methods:
   * ... makeScene() ...                -- WorldScene
   * ... onTick() ...                   -- ASimonWorld
   * ... addNewColor(int randNum) ...   -- ASimonWorld
   * ... displayAndSwitch() ...         -- ASimonWorld
   * Methods on Fields:
   * ... squareColors.makeDarker() ...  -- ColorScheme
   * ... currentColors.newColor(int randNum, ColorScheme squareColors) ...   -- ILoColor
   * ... runThrough.checkFirstColor(ColorScheme squareColors) ...            -- int
   * ... runThrough.getRest() ...                                            -- ILoColor
   * ... squareColors.makeBrighter(int whichSquare) ...                      -- ColorScheme
   */
  
  // adjusts the world on each tick
  public ASimonWorld onTick() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    if (!this.newColorAdded) { // adds a new color is one has not been added yet
      return this.addNewColor(this.rand.nextInt(4));
    }
    if (this.brightSquare > 0) { // resets the brightened square's color
      return new ObservingWorld(this.rand, this.currentColors, this.runThrough, 
          this.squareColors.makeDarker(), this.newColorAdded, 0);
    } else { // initiates showing the player the generated list of colors
      return displayAndSwitch();
    }
  }
  
  // Adds new color to the preserved current list and the list to be run through in display method
  ASimonWorld addNewColor(int randNum) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... randNum ...   -- int
     */
    return new ObservingWorld(this.rand, this.currentColors.newColor(randNum, 
        this.squareColors), this.currentColors.newColor(randNum, this.squareColors), 
        this.squareColors, true, this.brightSquare);
  }
  
  // brightens each color in the list in order, then switches to the playing state
  ASimonWorld displayAndSwitch() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    if (this.runThrough.checkFirstColor(this.squareColors) > 0) {
      return new ObservingWorld(this.rand, this.currentColors, this.runThrough.getRest(),
          this.squareColors.makeBrighter(this.runThrough.checkFirstColor(this.squareColors)), 
          this.newColorAdded, this.runThrough.checkFirstColor(this.squareColors));
    } else {
       return new PlayingWorld(new MtLoColor(), this.rand, this.currentColors, this.squareColors, this.brightSquare);
    }
  }
}
