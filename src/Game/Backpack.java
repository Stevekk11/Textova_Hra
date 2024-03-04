package Game;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
  // A field for the list of items
  private List<String> items;

  // A constructor that initializes the list
  public Backpack () {
    items = new ArrayList<>();
  }

  // A method to add an item to the backpack
  public void addItem (String item) {
    items.add (item);
  }

  // A method to remove an item from the backpack
  public void removeItem (String item) {
    items.remove (item);
  }

  // A method to check if the backpack has a given item
  public boolean hasItem (String item) {
    return items.contains (item);
  }

  // A method to print the backpack contents
  public void printBackpack () {
    System.out.println ("You have the following items in your backpack: " + items);
  }
}