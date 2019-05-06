import java.awt.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import tester.*;
import java.util.Random;

public class ExamplesSimonSays {
  
  // Data examples for testing
  
  Color red = Color.RED.darker();
  Color green = Color.GREEN.darker();
  Color blue = Color.BLUE.darker();
  Color yellow = Color.YELLOW.darker();
 
  Color brightRed = Color.RED;
  Color brightBlue = Color.BLUE;
  Color brightGreen = Color.GREEN;
  Color brightYellow = Color.YELLOW;
 
  ColorScheme cs = new ColorScheme(this.red, this.blue, this.green, this.yellow);
  ColorScheme csBrightRed = new ColorScheme(this.brightRed, this.blue, this.green, this.yellow);
  ColorScheme csBrightBlue = new ColorScheme(this.red, this.brightBlue, this.green, this.yellow);
  ColorScheme csBrightGreen = new ColorScheme(this.red, this.blue, this.brightGreen, this.yellow);
  ColorScheme csBrightYellow = new ColorScheme(this.red, this.blue, this.green, this.brightYellow);
  
  
  ILoColor mt = new MtLoColor();
  ILoColor colorList1 = new ConsLoColor(this.green, this.mt);
  ILoColor colorList2 = new ConsLoColor(this.green, new ConsLoColor(this.red, this.mt));
  ILoColor colorList3 = new ConsLoColor(this.green, new ConsLoColor(this.red, 
      new ConsLoColor(this.green, this.mt)));
  
  ILoColor loc0 = new ConsLoColor(this.green, this.mt);
  ILoColor loc1 = new ConsLoColor(this.blue, this.mt);
  ILoColor loc2 = new ConsLoColor(this.red, this.mt);
  
  ILoColor loc3 = new ConsLoColor(this.green, this.loc1);
  ILoColor loc4 = new ConsLoColor(this.green, new ConsLoColor(this.blue, this.loc1));
  ILoColor loc5 = new ConsLoColor(this.green, new ConsLoColor(this.blue, this.loc1));
  ILoColor loc6 = new ConsLoColor(this.green, new ConsLoColor(this.blue, 
      new ConsLoColor(this.yellow, this.mt)));
  ILoColor loc7 = new ConsLoColor(this.yellow, this.mt);
  
  ConsLoColor cloc1 = new ConsLoColor(this.green, this.mt);
  ConsLoColor cloc2 = new ConsLoColor(this.yellow, this.mt);
  ConsLoColor cloc3 = new ConsLoColor(this.red, this.cloc1);
  ConsLoColor cloc4 = new ConsLoColor(this.blue, this.cloc3);
  ConsLoColor cloc5 = new ConsLoColor(this.blue, new ConsLoColor(this.red, this.cloc1));
 
  ObservingWorld start = new ObservingWorld(this.mt, this.mt, this.cs, false, 0);
  ObservingWorld testStart = new ObservingWorld(new Random(1), this.mt, this.mt, this.cs, false, 0);
  ObservingWorld switchTestWorld = new ObservingWorld(new Random(1), this.colorList1, this.mt, this.cs, true, 0);
  ObservingWorld world1 = new ObservingWorld(new Random(1), this.colorList1, this.colorList1, this.cs, true, 0);
  ObservingWorld rtWorld1 = new ObservingWorld(new Random(1), this.colorList1, this.mt, this.csBrightGreen, true, 3);
  ObservingWorld world2 = new ObservingWorld(new Random(1), this.colorList2, this.colorList2, this.cs, false, 0);
  ObservingWorld world3 = new ObservingWorld(new Random(1), this.colorList3, this.colorList3, this.cs, true, 0);
  ObservingWorld world4 = new ObservingWorld(this.colorList2, this.colorList2, this.cs, true, 0);
  ObservingWorld rtWorld4 = new ObservingWorld(this.colorList2, this.loc2, this.csBrightGreen, true, 3);
  ObservingWorld freshWorld = new ObservingWorld(new Random(1), this.colorList2, this.mt, this.cs, false, 0);
  
  ObservingWorld redBright = new ObservingWorld(this.colorList1, this.colorList1, 
      this.csBrightRed, true, 1);
  ObservingWorld blueBright = new ObservingWorld(this.colorList1, this.colorList1, 
      this.csBrightBlue, true, 2);
  ObservingWorld greenBright = new ObservingWorld(this.colorList1, this.colorList1, 
      this.csBrightGreen, true, 3);
  ObservingWorld yellowBright = new ObservingWorld(this.colorList1, this.colorList1, 
      this.csBrightYellow, true, 4);
  
  PlayingWorld playWorld = new PlayingWorld(this.mt, new Random(1), this.colorList2, this.cs, 0);
  PlayingWorld closeWorld = new PlayingWorld(this.colorList1, new Random(1), this.colorList2, 
      this.csBrightGreen, 3);
  PlayingWorld closerWorld = new PlayingWorld(this.colorList1, new Random(1), this.colorList2,
      this.cs, 0);
  PlayingWorld almostWorld = new PlayingWorld(this.colorList2, new Random(1), this.colorList2, 
      this.csBrightRed, 1);
  PlayingWorld correctWorld = new PlayingWorld(this.colorList2, new Random(1), this.colorList2, 
      this.cs, 0);
  PlayingWorld whoopsWorld = new PlayingWorld(this.loc1, new Random(1), this.colorList2, 
      this.csBrightBlue, 2);
  
  GameOver gameOverWorld = new GameOver(new Random(1), this.colorList2, this.csBrightBlue, 2);
  
  // Tests for methods
  
  // List of Colors tests (some from 5A assignment, some new to this assignment)
  
  // tests for same sequence method
  boolean testSameSequence(Tester t) {
    return t.checkExpect(this.loc1.sameSequence(this.loc2), false)
        && t.checkExpect(this.loc1.sameSequence(this.mt), false)
        && t.checkExpect(this.loc4.sameSequence(this.loc5), true);
  }
  
  // tests for the same color method
  boolean testSameColor(Tester t)  {
    return t.checkExpect(this.cloc1.sameColor(this.cloc2), false)
        && t.checkExpect(this.cloc2.sameColor((ConsLoColor)this.loc7), true)
        && t.checkExpect(this.cloc4.sameColor(this.cloc5), true)
        && t.checkExpect(this.cloc5.sameColor(this.cloc3), false);
  }
  
  // tests for starts with method
  boolean testStartsWith(Tester t) {
    return t.checkExpect(this.loc4.startsWith(this.loc3), true)
        && t.checkExpect(this.loc3.startsWith(this.loc5), false)
        && t.checkExpect(this.mt.startsWith(this.loc4), false)
        && t.checkExpect(this.loc4.startsWith(this.mt), true);
  }
  
  // tests for same start list method. Casting used in parameter for testing purposes.
  boolean testSameStartList(Tester t) {
    return t.checkExpect(this.mt.sameStartList((ConsLoColor)this.loc1), true)
        && t.checkExpect(this.loc3.sameStartList((ConsLoColor)this.loc6), true)
        && t.checkExpect(this.loc6.sameStartList((ConsLoColor)this.loc5), false);
  }
  
  // tests for end of list method. Casting used in parameter for testing purposes.
  boolean testEndOfList(Tester t) {
    return t.checkExpect(this.mt.endOfList((MtLoColor)this.mt), true)
        && t.checkExpect(this.loc1.endOfList((MtLoColor)this.mt), false);
  }
  
  // tests for new color method
  boolean testNewColor(Tester t) {
    return t.checkExpect(this.mt.newColor(1, this.cs), this.loc1)
        && t.checkExpect(this.loc3.newColor(4, this.cs), this.loc6);
  }
  
  // tests for check first color method
  boolean testCheckFirstColor(Tester t) {
    return t.checkExpect(this.mt.checkFirstColor(this.cs), 0)
        && t.checkExpect(this.loc1.checkFirstColor(this.cs), 2)
        && t.checkExpect(this.loc2.checkFirstColor(this.cs), 1)
        && t.checkExpect(this.loc6.checkFirstColor(this.cs), 3)
        && t.checkExpect(this.loc7.checkFirstColor(this.cs), 4);
  }
  
  // tests for get rest method
  boolean testGetRest(Tester t) {
    return t.checkExpect(this.mt.getRest(), this.mt)
        && t.checkExpect(this.loc1.getRest(), this.mt)
        && t.checkExpect(this.loc3.getRest(), this.loc1);
  }
  
  // tests for get length method
  boolean testGetLength(Tester t) {
    return t.checkExpect(this.mt.getLength(), 0)
        && t.checkExpect(this.loc1.getLength(), 1)
        && t.checkExpect(this.loc6.getLength(), 3);
  }
  
  // Tests for Observing World class
  
  // tests for the onTick method
  boolean testOnTick(Tester t) {
    return t.checkExpect(this.testStart.onTick(), 
        new ObservingWorld(new Random(1), this.loc0, this.loc0, this.cs, true, 0))
        && t.checkExpect(this.world2.onTick(), this.world3)
        && t.checkExpect(this.redBright.onTick(), this.world1)
        && t.checkExpect(this.blueBright.onTick(), this.world1)
        && t.checkExpect(this.greenBright.onTick(), this.world1)
        && t.checkExpect(this.yellowBright.onTick(), this.world1)
        && t.checkExpect(this.world1.onTick(), this.rtWorld1);
  }
  
  // tests for add new color method on an Observing World
  boolean testAddNewColorOW(Tester t) {
    return t.checkExpect(this.start.addNewColor(2), this.world1)
        && t.checkExpect(this.world1.addNewColor(0), this.world4)
        && t.checkExpect(this.world2.addNewColor(2), this.world3);
  }
  
  // tests for display and switch method
  boolean testDisplayAndSwitch(Tester t) {
    return t.checkExpect(this.world1.displayAndSwitch(), this.rtWorld1)
        && t.checkExpect(this.world4.displayAndSwitch(), this.rtWorld4)
        && t.checkExpect(this.switchTestWorld.displayAndSwitch(), 
            new PlayingWorld(this.mt, new Random(1), this.colorList1, this.cs, 0));
  }
  
  // Tests for Playing World
  
  // tests for on mouse pressed method
  boolean testOnMousePressed(Tester t) {
    return t.checkExpect(this.playWorld.onMousePressed(new Posn(100, 100)), 
        new PlayingWorld(this.loc2, new Random(1), this.colorList2, this.csBrightRed, 1))
        && t.checkExpect(this.playWorld.onMousePressed(new Posn(300, 100)), 
            new PlayingWorld(this.loc1, new Random(1), this.colorList2, this.csBrightBlue, 2))
        && t.checkExpect(this.playWorld.onMousePressed(new Posn(100, 300)), 
            new PlayingWorld(this.loc0, new Random(1), this.colorList2, this.csBrightGreen, 3))
        && t.checkExpect(this.playWorld.onMousePressed(new Posn(300, 300)), 
            new PlayingWorld(this.loc7, new Random(1), this.colorList2, this.csBrightYellow, 4))
        && t.checkExpect(this.playWorld.onMousePressed(new Posn (401, 401)), this.playWorld);
  }
  
  // tests for on mouse released method in Playing World
  boolean testOnMouseReleasedPW(Tester t) {
    return t.checkExpect(this.correctWorld.onMouseReleased(new Posn(0, 0)), this.freshWorld)
        && t.checkExpect(this.closeWorld.onMouseReleased(new Posn(0, 0)), this.closerWorld)
        && t.checkExpect(this.whoopsWorld.onMouseReleased(new Posn(0, 0)), this.gameOverWorld);
  }
  
  // tests for add new color on a Playing World
  boolean testAddNewColorPW(Tester t) {
    return t.checkExpect(this.playWorld.addNewColor(1), this.whoopsWorld)
        && t.checkExpect(this.closerWorld.addNewColor(0), this.almostWorld);
  }
  
  // Tests for Methods shared in all Simon Worlds
  
  // tests for on mouse clicked method
  boolean testOnMouseClicked(Tester t) {
    return t.checkExpect(this.world2.onMouseClicked(new Posn(100, 450)), this.start)
        && t.checkExpect(this.almostWorld.onMouseClicked(new Posn(100, 450)), this.start)
        && t.checkExpect(this.gameOverWorld.onMouseClicked(new Posn(100, 450)), this.start);
  }
  
  // Tests for Game Over World
  
  // tests for on mouse released in Game Over World
  boolean testOnMouseReleasedGOW(Tester t) {
    return t.checkExpect(this.gameOverWorld.onMouseReleased(new Posn(0, 0)), this.start);
  }
  
  // Tests for Color Scheme
  
  // tests for get red method
  boolean testGetRed(Tester t) {
    return t.checkExpect(this.cs.getRed(), this.red);
  }
  
  // tests for get blue method
  boolean testGetBlue(Tester t) {
    return t.checkExpect(this.cs.getBlue(), this.blue);
  }
  
  // tests for get green method
  boolean testGetGreen(Tester t) {
    return t.checkExpect(this.cs.getGreen(), this.green);
  }
  
  // tests for get yellow method
  boolean testGetYellow(Tester t) {
    return t.checkExpect(this.cs.getYellow(), this.yellow);
  }
  
  // tests for make darker method
  boolean testMakeDakrer(Tester t) {
    return t.checkExpect(this.csBrightBlue.makeDarker(), this.cs)
        && t.checkExpect(this.csBrightRed.makeDarker(), this.cs)
        && t.checkExpect(this.csBrightGreen.makeDarker(), this.cs)
        && t.checkExpect(this.csBrightYellow.makeDarker(), this.cs);
  }
  
  // tests for make brighter method
  boolean testMakeBrighter(Tester t) {
    return t.checkExpect(this.cs.makeBrighter(1), this.csBrightRed)
        && t.checkExpect(this.cs.makeBrighter(2), this.csBrightBlue)
        && t.checkExpect(this.cs.makeBrighter(3), this.csBrightGreen)
        && t.checkExpect(this.cs.makeBrighter(4), this.csBrightYellow);
  }
  
}
