package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Game.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorldLoaderTest {
    WorldLoader loader;
    World world;
    Room current;
    Room next;
    Room forest;

    @BeforeEach
    void setUp() {
        loader = new WorldLoader("C:\\Users\\vegh\\OneDrive - SPŠE Ječná 30, Praha 2\\IdeaProjects\\cviceni-22-1-hra\\src\\Game\\rooms.csv");
        world = new World(loader);
        current = world.getRoom(0, 0);
        next = world.moveCommandExecute(current, Direction.E);
        forest = world.getRoom(1, 0);
    }

    @Test
    void loadMap() {
        assertEquals("Cave", current.getName());
        assertEquals("Tomb", next.getName());
        assertEquals(List.of("Stone"), current.getItems());
        assertEquals(List.of("Apple"), next.getItems());
        world.interactCommandExecute(next, "Apple");
        assertNotEquals(List.of("Apple"), next.getItems());
        assertEquals("Forest",forest.getName());
        forest.removeItem("Enemy");
        assertNotEquals(List.of("Enemy","Meal"),forest.getItems());
    }
}