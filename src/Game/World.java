package Game;

import java.util.Arrays;
import java.util.List;

public class World {
  // A field for the 2D array of rooms
  private Room [] [] map;

  // A field for the backpack object
  private Backpack backpack;

  // A field for the move count
  private int moveCount;
 private List<String> pickableItems = Arrays.asList ("Sword", "Apple", "Meal", "Cigarette");

  // A constructor that takes a WorldLoader object as a parameter
  public World (WorldLoader loader) {
    // Get the map from the loader
    map = loader.getMap ();
    // Create a new backpack object
    backpack = new Backpack ();
    // Initialize the move count
    moveCount = 0;
  }

  // A method to get the room at a given position
  public Room getRoom (int x, int y) {
    // Check if the position is valid
    if (x >= 0 && x < 3 && y >= 0 && y < 3) {
      // Return the room at that position
      return map [x] [y];
    }
    else {
      // Return null if the position is invalid
      return null;
    }
  }

  // A method to move between rooms
  public Room moveCommandExecute (Room current, Direction direction) {
    // Get the current room's position
    int x = (current.getId () - 1) / 3;
    int y = (current.getId () - 1) % 3;

    // Check if the current room has the given direction
    if (current.hasDirection (direction)) {
      // Move to the new position based on the direction
      switch (direction) {
        case N -> x--;
        case S -> x++;
        case E -> y++;
        case W -> y--;
      }

      // Get the room at the new position
      Room next = getRoom (x, y);

      // Check if the next room has an enemy
      if (next.hasItem ("Enemy")) {
        // Check if the backpack has a sword
        if (backpack.hasItem ("Sword")) {
          // Remove the enemy from the room
            System.out.println ("There is an enemy in this room! You have used your sword and fought the enemy successfully.");
          backpack.removeItem("Sword");
          next.removeItem ("Enemy");

        }
        else {
          // End the game with a loss message
          System.out.println ("You encountered an enemy without a sword. You are dead!");
          System.exit(0);
          return null;
        }
      } else if (backpack.hasItem("Cigarette")){
            System.out.println("You smoked the cigarette and died from cancer. ☹️ ");
              System.exit(0);
      }

      // Increment the move count
      moveCount++;
      backpack.printBackpack();

      // Check if the move count reaches the limit
      if (moveCount == 3 || moveCount == 6 || moveCount == 9) {
        // Check if the backpack has an apple or a meal
        if (backpack.hasItem ("Apple") || backpack.hasItem ("Meal")) {
          // Remove the apple or the meal from the backpack
          if (backpack.hasItem ("Apple")) {
            backpack.removeItem ("Apple");
            System.out.println("You have eaten");
          }
          else {
            backpack.removeItem ("Meal");
            System.out.println("You have eaten");
          }
        }
        else {
          // End the game with a loss message
          System.out.println ("You ran out of food. You starved to death!");
          System.exit(0);
          return null;
        }
      }

      // Return the room at the new position
      return next;
    }
    else {
      // Return null if the current room does not have the given direction
      return null;
    }
  }

  // A method to interact with items
 public void interactCommandExecute (Room room, String item) {
  // Check if the room has the given item
  if (room.hasItem (item)) {
    // Check if the item is pickable or not
    if (pickableItems.contains (item)) {
      // Add the item to the backpack
      backpack.addItem (item);
      // Remove the item from the room
      room.removeItem (item);

      // Print a message
      System.out.println ("You picked up the " + item + ".");
    }
    else {
      // Do something based on the item
      switch (item) {
        case "The End":
          System.out.println ("You reached the end of the game.");
          System.exit(0);
          break;
        default:
          System.out.println ("You cannot pick up: " + item);
      }
    }
  }
  else {
    // Print a message if the room does not have the given item
    System.out.println ("There is no item called " + item + " in this room.");
  }
}
}
