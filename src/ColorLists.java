import tester.*;                   
import java.awt.Color;          

// Represents a list of colors that is either empty or contains colors
interface ILoColor {
  // computes whether this list of colors contains the same colors as the given list of colors
  boolean sameSequence(ILoColor other);
  
  // checks whether this list of colors begins with the given list of colors
  boolean startsWith(ILoColor given);
  
  // checks whether all of the list of colors are in the given list of colors
  boolean sameStartList(ConsLoColor original);
  
  // checks whether the given list of colors ends when the original list of colors ends
  boolean endOfList(MtLoColor original);
  
  // adds a new Color to the list depending on what the random number is
  ILoColor newColor(int randNum, ColorScheme squareColors);
  
  // returns an int representing the first color of the list
  int checkFirstColor(ColorScheme squareColors);
  
  // returns either the rest of the list or the empty list
  ILoColor getRest();
  
  // finds the length of the list to calculate the player's score
  int getLength();
}

class MtLoColor implements ILoColor {
  
  /* Template
   * Fields:
   * Methods:
   * ... sameSequence(ILoColor other) ...                      -- boolean
   * ... startsWith(ILoColor other) ...                        -- boolean
   * ... sameStartList(ConsLoColor original) ...               -- boolean
   * ... endOfList(MtLoColor original) ...                     -- boolean
   * ... newColor(int randNum, ColorScheme squareColors) ...   -- ILoColor
   * ... checkFirstColor(ColorScheme squareColors) ...         -- int
   * ... getRest() ...                                         -- ILoColor
   * ... getLength() ...                                       -- int
   * Methods on Fields:
   */

  // check whether the other ILoColor is an empty list of colors
  public boolean sameSequence(ILoColor other) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... other ...   -- ILoColor
     */
    return other instanceof MtLoColor;
  }

  // check if other is also empty or is nonempty. Calls appropriate Method via Dynamic Dispatch
  public boolean startsWith(ILoColor other) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... other ...                                 -- ILoColor
     * Methods on Parameters:
     * ... other.endOfList(MtLoColor original) ...   -- boolean
     */
    return other.endOfList(this);
  }

  // returns true because the original list starts with all of the colors in this list
  public boolean sameStartList(ConsLoColor original) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... original ...   -- ConsLoColor
     */
    return true;
  }

  // returns true because both lists are empty
  public boolean endOfList(MtLoColor original) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... original ...   -- MtLoColor
     */
    return true;
  }
  
  // adds the new color in front of the empty list depending on the randomly generated number
  public ILoColor newColor(int randNum, ColorScheme squareColors) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... randNum ...        -- int
     * ... squareColors ...   -- ColorScheme
     * Methods on Parameters:
     * ... squareColors.getRed() ...      -- Color
     * ... squareColors.getBlue() ...     -- Color
     * ... squareColors.getGreen() ...    -- Color
     * ... squareColors.getYellow() ...   -- Color
     */
    if (randNum == 0) {
      return new ConsLoColor(squareColors.getRed(), this);
    }
    if (randNum == 1) {
      return new ConsLoColor(squareColors.getBlue(), this);
    }
    if (randNum == 2) {
      return new ConsLoColor(squareColors.getGreen(), this);
    } else {
      return new ConsLoColor(squareColors.getYellow(), this);
    }
  }
  
  // since an empty list doesn't have a first color, returns 0
  public int checkFirstColor(ColorScheme squareColors) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... squareColors ...   -- ColorScheme
     */
    return 0;
  }
  
  // the rest of an empty list should just return the empty list
  public ILoColor getRest() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return this;
  }
  
  // an empty list has length 0
  public int getLength() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return 0;
  }
}

class ConsLoColor implements ILoColor {
  Color first;
  ILoColor rest;
  
  ConsLoColor(Color first, ILoColor rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /* Template
   * Fields:
   * ... first ...                                                  -- Color
   * ... rest ...                                                   -- ILoColor
   * Methods:
   * ... sameSequence(ILoColor other) ...                           -- boolean
   * ... sameColor(ConsLoColor other) ...                           -- boolean
   * ... startsWith(ILoColor other) ...                             -- boolean
   * ... sameStartList(ConsLoColor original) ...                    -- boolean
   * ... endOfList(MtLoColor original) ...                          -- boolean
   * ... newColor(int randNum, ColorScheme squareColors) ...        -- ILoColor
   * ... checkFirstColor(ColorScheme squareColors) ...              -- int
   * ... getRest() ...                                              -- ILoColor
   * ... getLength() ...                                            -- int
   * Methods on Fields:
   * ... rest.sameSequence(ILoColor other) ...                      -- boolean
   * ... rest.sameColor(ConsLoColor other) ...                      -- boolean
   * ... rest.startsWith(ILoColor other) ...                        -- boolean
   * ... rest.sameStartList(ConsLoColor original) ...               -- boolean
   * ... rest.endOfList(MtLoColor original) ...                     -- boolean
   * ... rest.newColor(int randNum, ColorScheme squareColors) ...   -- ILoColor
   * ... rest.getLength() ...                                       -- int
   */

  // computes whether this list of colors contains the same colors as the given list of colors
  public boolean sameSequence(ILoColor other) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... other ...   -- ILoColor
     */
    if (other instanceof ConsLoColor) {
      return this.sameColor((ConsLoColor)other);
    } else {
      return false;
    }
  }
  
  // checks that the first two colors in the lists are the same, then checks the rest
  public boolean sameColor(ConsLoColor other) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... other ...   -- ConsLoColor
     */
    return this.first.equals(other.first)
        && this.rest.sameSequence(other.rest);
  }

  // checks that this list of colors starts with the given ILoColor by initiating double dispatch
  public boolean startsWith(ILoColor other) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... other ...                                       -- ILoColor
     * Methods on Parameters:
     * ... other.sameStartList(ConsLoColor original) ...   -- boolean
     */
    return other.sameStartList(this);
  }

  // checks that the first two colors are equal, and if true, continues the comparison
  public boolean sameStartList(ConsLoColor original) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... original ...                                   -- ConsLoColor
     * Methods on Parameters:
     * ... original.rest.startsWith(ILoColor other) ...   -- boolean
     */
    if (this.first.equals(original.first)) {
      return original.rest.startsWith(this.rest);
    } else {
      return false;
    }
  }
  
  // the original list is empty, but this list is not, so false
  public boolean endOfList(MtLoColor original) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... original ...                                   -- MtLoColor
     */
    return false;
  }
  
  // adds the new color to the end of the list by recurring until hitting the empty list
  public ILoColor newColor(int randNum, ColorScheme squareColors) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... randNum ...        -- int
     * ... squareColors ...   -- ColorScheme
     */
      return new ConsLoColor(this.first, this.rest.newColor(randNum, squareColors));
    }
  
  // returns an integer representing which color the first color in the list is
  public int checkFirstColor(ColorScheme squareColors) {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     * ... squareColors ...   -- ColorScheme
     */
    if (this.first.equals(squareColors.getRed())) {
      return 1;
    }
    if (this.first.equals(squareColors.getBlue())) {
      return 2;
    }
    if (this.first.equals(squareColors.getGreen())) {
      return 3;
    } else {
      return 4;
    }
  }
  
  // returns the rest of the list (a necessary evil)
  public ILoColor getRest() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return this.rest;
  }
  
  // returns the length of the list to find a player's score
  public int getLength() {
    /* Method Template
     * EVERYTHING from the class template
     * Parameters:
     */
    return 1 + this.rest.getLength();
  }
}