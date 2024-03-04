package Game;

import java.util.Scanner;

public class Main {
    // The main method
public static void main (String [] args) {
  // Create a WorldLoader object with the file name of the csv file
  WorldLoader loader = new WorldLoader("src/rooms.csv");

  // Create a World object with the loader
  World world = new World(loader);

  // Get the first room as the current room
  Room current = world.getRoom(0, 0);

  // Create a Scanner object to read user input
  Scanner sc = new Scanner(System.in);
  System.out.println("""
          [INFO] to pick up an item, write its name to the console. To go to the direction, write it.
          The story: You just woke up in a dark place, you don't know the way home, but you must go forward.
           Some items are useful, some not. You can die if you won't be careful.
           [INFO] Your head is spinning,
            and you struggle to remember how you got there. The air is damp and musty,
             and a faint sense of foreboding fills the room. As you start to get your bearings,
              you notice a dim light flickering in the corner, drawing your attention.
               You realize that you must find your way out of this place and back to safety.""");

  // Start the game loop
  while (true) {
    // Print the current room information
    current.printRoom();

    // Read the user input
    String input = sc.nextLine();

    // Check if the input is a direction
    try {
      Direction direction = Direction.valueOf(input);
      // Try to move to the new room
      Room next = world.moveCommandExecute(current, direction);
      // Check if the move was successful
      if (next != null) {
        // Update the current room
        current = next;
      } else {
        // Print a message if the move was invalid
        System.out.println("You cannot go to that direction.");
      }
    }
    // If the input is not a direction, assume it is an item
    catch (IllegalArgumentException e) {
      // Try to interact with the item
      world.interactCommandExecute(current, input);
    }
  }

}

}