import java.awt.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import tester.*;
import java.util.Random;


/* Why Multiple World Classes

When designing our program, we decided to utilize three different Worlds rather than only one. 
We chose to do this because in Simon Says, there are essentially two different stages of the game:
watching and inputting. As such, we started with one world for observing and one world for playing,
and towards the end, we decided to also include a world for when the player loses, so that the game
neither automatically resets nor immediately closes upon an incorrect input.

We noted that in the observing stage of the game, the onTick method is the method that matters, 
and really, a user should not be able to click anywhere except the start over button. If we had 
only a single world, we would have to implement some sort of method to prevent a user from clicking
on squares while the sequence was being displayed. Likewise, when the player is inputting what they
remember the sequence to be, onTick does not need to be running, since all input functionally
should be based around onMousePressed and onMouseReleased. As such, we decided that it would be
easiest to design the program with two distinct worlds: one for displaying the sequence and one for
accepting user input. Any shared functionality could be implemented using this abstract class.*/


// abstract class to contain consistent data between three worlds 
// and draw the observing and the playing world
abstract class ASimonWorld extends World {
  Random rand;
  ILoColor currentColors;
  ColorScheme squareColors;
  int brightSquare;
  
  
  ASimonWorld(Random rand, ILoColor currentColors, ColorScheme squareColors, int brightSquare) {
    this.rand = rand;
    this.currentColors = currentColors;
    this.squareColors = squareColors;
    this.brightSquare = brightSquare;
  }
  
  // draws the world
  public WorldScene makeScene() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    
    // local variables for our buttons
    RectangleImage redSquare = new RectangleImage(200, 200, OutlineMode.SOLID, this.squareColors.getRed());
    RectangleImage blueSquare = new RectangleImage(200, 200, OutlineMode.SOLID, this.squareColors.getBlue());
    RectangleImage greenSquare = new RectangleImage(200, 200, OutlineMode.SOLID, this.squareColors.getGreen());
    RectangleImage yellowSquare = new RectangleImage(200, 200, OutlineMode.SOLID, this.squareColors.getYellow());
    TextImage restart = new TextImage("Start Over", 24, Color.BLACK);
    RectangleImage button = new RectangleImage(200, 100, OutlineMode.OUTLINE, Color.BLACK);
    WorldImage restartButton = new OverlayImage(restart, button);
    TextImage currentScore = new TextImage("Score: " + String.valueOf(this.currentColors.getLength() - 1), 24, Color.BLACK);
    WorldImage currentScoreDisplay = new OverlayImage(currentScore, button);
    
    return this.getEmptyScene().placeImageXY(redSquare, 100, 100).placeImageXY(blueSquare, 
        300, 100).placeImageXY(greenSquare, 100, 300).placeImageXY(yellowSquare, 300, 
            300).placeImageXY(restartButton, 100, 450).placeImageXY(currentScoreDisplay, 300, 450);
  }
  
  // allows the player to restart the game using the restart button in observing and playing worlds
  public ASimonWorld onMouseClicked(Posn pos) {
    if (pos.x <= 200 && pos.y > 400) {
      return new ObservingWorld(new MtLoColor(), new MtLoColor(), 
          this.squareColors.makeDarker(), false, 0);
    } else {
      return this;
    }
  }
}


