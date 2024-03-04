package Game;

import java.util.List;

public class Room {
     // Fields for the room properties
  private int id;
  private String name;
  private List<Direction> directions;
  private List<String> items;

  // A constructor that takes the values as parameters
  public Room (int id, String name, List<Direction> directions, List<String> items) {
    this.id = id;
    this.name = name;
    this.directions = directions;
    this.items = items;
  }

  // Getters and setters for the fields
  public int getId () {
    return id;
  }

  public String getName () {
    return name;
  }

  public List<Direction> getDirections () {
    return directions;
  }

  public List<String> getItems () {
    return items;
  }

  public void setId (int id) {
    this.id = id;
  }

  public void setName (String name) {
    this.name = name;
  }

  public void setDirections (List<Direction> directions) {
    this.directions = directions;
  }

  public void setItems (List<String> items) {
    this.items = items;
  }

  // A method to check if the room has a given direction
  public boolean hasDirection (Direction direction) {
    return directions.contains (direction);
  }

  // A method to check if the room has a given item
  public boolean hasItem (String item) {
    return items.contains (item);
  }

  // A method to remove an item from the room
  public void removeItem (String item) {
    items.remove (item);
  }

  // A method to print the room information
  public void printRoom () {
    System.out.println ("You are in room " + id + ": " + name);
    System.out.println ("You can go to the following directions: " + directions);
    System.out.println ("You see the following items: " + items);
  }
//to string
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Room{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", directions=").append(directions);
    sb.append(", items=").append(items);
    sb.append('}');
    return sb.toString();
  }
}
