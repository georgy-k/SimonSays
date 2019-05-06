import java.awt.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import tester.*;
import java.util.Random;

class ColorScheme {
  Color red;
  Color blue;
  Color green;
  Color yellow;
  
  ColorScheme(Color red, Color blue, Color green, Color yellow) {
    this.red = red;
    this.blue = blue;
    this.green = green;
    this.yellow = yellow;
  }
  
  /* Template
   * Fields:
   * ... red ...              -- Color
   * ... blue ...             -- Color
   * ... green ...            -- Color
   * ... yellow ...           -- Color
   * Methods:
   * ... getRed() ...         -- Color
   * ... getBlue() ...        -- Color
   * ... getGreen() ...       -- Color
   * ... getYellow() ...      -- Color
   * ... makeDarker() ...     -- ColorScheme
   * ... makeBrighter() ...   -- ColorScheme
   * Methods on Fields:
   */
  
  // retrieves the red color value
  Color getRed() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return this.red;
  }
  
  // retrieves the blue color value
  Color getBlue() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return this.blue;
  }
  
  // retrieves the green color value
  Color getGreen() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return this.green;
  }
  
  // retrieves the yellow color value
  Color getYellow() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return this.yellow;
  }
  
  // resets each color to the default value, one shade darker than the brightest color
  ColorScheme makeDarker() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return new ColorScheme(Color.RED.darker(), Color.BLUE.darker(), Color.GREEN.darker(), Color.YELLOW.darker());
  }
  
  // brightens a specific color depending on which square the player clicked
  ColorScheme makeBrighter(int whichSquare) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... whichSquare ...   -- int
     */
    if (whichSquare == 1) {
      return new ColorScheme(Color.RED, this.blue, this.green, this.yellow);
    }
    if (whichSquare == 2) {
      return new ColorScheme(this.red, Color.BLUE, this.green, this.yellow);
    }
    if (whichSquare == 3) {
      return new ColorScheme(this.red, this.blue, Color.GREEN, this.yellow);
    } else {
      return new ColorScheme(this.red, this.blue, this.green, Color.YELLOW);
    }
  }
}
