package se.iths.java24.textadventure.world;

import java.util.HashMap;
import java.util.Map;

public class Room {
    String name;
    String description;
    private Map<String, Room> exits; // Nytt fält för utgångar

    // Konstruktor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>(); // Initiera kartan för utgångar
    }

    // Metod för att visa rummets information
    public void displayRoomInfo() {
        System.out.println("Du är i: " + name);
        System.out.println(description);
        System.out.println("Utgångar: ");
        if (exits.isEmpty()) {
            System.out.println("Inga uppenbara utgångar.");
        } else {
            for (String direction : exits.keySet()) { //loopa igenom alla nycklar (riktningar) i utgångs-mappen
                System.out.println(direction + " ");
            }
            System.out.println(); // Ny rad efter utgångarna
        }
    }

    // Metod för att lägga till en utgång
    public void setExit(String direction, Room neighbor) {
        exits.put(direction.toLowerCase(), neighbor);
    }
    // Metod för att hämta rummer som en viss utgång leder till
    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }
}
