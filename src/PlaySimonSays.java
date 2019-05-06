import java.awt.Color;
import tester.Tester;

// Run this class to play the game!
class PlaySimonSays {
  
  // data needed for starting the game
  Color red = Color.RED.darker();
  Color green = Color.GREEN.darker();
  Color blue = Color.BLUE.darker();
  Color yellow = Color.YELLOW.darker();
  ColorScheme cs = new ColorScheme(this.red, this.blue, this.green, this.yellow);
  ILoColor mt = new MtLoColor();
  ObservingWorld start = new ObservingWorld(this.mt, this.mt, this.cs, false, 0);
  
  // big bang method that starts the game
  boolean testBigBang(Tester t) {
    return start.bigBang(400, 500, .5);
  }
}
