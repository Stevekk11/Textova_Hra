package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class WorldLoader {
  // A field for the 2D array of rooms
  private Room [] [] map;

  // A constructor that takes the file name as a parameter
  public WorldLoader (String fileName) {
    // Initialize the map as a 3x3 array
    map = new Room [3] [3];

    try {
      // Create a BufferedReader to read the file
      BufferedReader br = new BufferedReader (new FileReader(fileName));

      // Read the file line by line
      String line;
      while ( (line = br.readLine ()) != null) {
        // Split the line by comma
        String [] values = line.split (",");

        // Parse the values to create a new room
        int id = Integer.parseInt (values [0]);
        String name = values [1];
        List<Direction> directions = new ArrayList<>();
        List<String> items = new ArrayList<> ();

        // Loop through the values from index 2 to the end
        for (int i = 2; i < values.length; i++) {
          // Try to parse the value as a direction
          try {
            Direction direction = Direction.valueOf (values [i]);
            directions.add (direction);
          }
          // If it is not a direction, assume it is an item
          catch (IllegalArgumentException e) {
            String item = values [i];
            items.add (item);
          }
        }

        // Create a new room object
        Room room = new Room (id, name, directions, items);

        // Add the room to the map based on its id
        switch (id) {
          case 1 -> map[0][0] = room;
          case 2 -> map[0][1] = room;
          case 3 -> map[0][2] = room;
          case 4 -> map[1][0] = room;
          case 5 -> map[1][1] = room;
          case 6 -> map[1][2] = room;
          case 7 -> map[2][0] = room;
          case 8 -> map[2][1] = room;
          case 9 -> map[2][2] = room;
          default -> System.out.println("Invalid room id: " + id);
        }
      }
      // Close the BufferedReader
      br.close ();
    }
    // Catch any other exception that may occur
    catch (Exception e) {
      e.printStackTrace ();
    }
  }
  // A getter for the map
  public Room [] [] getMap () {
    return map;
  }
  // A method to print the 2D room grid to the console for debug purposes
public void printGrid () {
  for (int i = 0; i < map.length; i++) {

    for (int j = 0; j < map [i].length; j++) {
      // Print the room object at the current position
      System.out.print (map [i] [j].toString ());
    }
    // Print a new line character after each row
    System.out.println ();
  }
}

}