package se.iths.java24.textadventure.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    String name;
    String description;
    private Map<String, Room> exits;
    private List<Item> items; // Fält för föremål i rummet

    // Konstruktor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>(); // Initiera listan för föremål
    }

    // Metod för att visa rummets information, utgångar och föremål
    public void displayRoomInfo() {
        System.out.println("Du är i: " + name);
        System.out.println(description);

        System.out.print("Utgångar: ");
        if (exits.isEmpty()) {
            System.out.println("Inga uppenbara utgångar.");
        } else {
            for (String direction : exits.keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println(); // Ny rad efter utgångarna
        }

        // Visa föremål i rummet
        if (!items.isEmpty()) {
            System.out.print("Du ser också: ");
            for (int i = 0; i < items.size(); i++) {
                System.out.print(items.get(i).getName());
                if (i < items.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(".");
        }
    }

    // Metod för att lägga till ett föremål i rummet
    public void addItem(Item item) {
        this.items.add(item);
    }

    // Metod för att hämta ett föremål från rummet baserat på dess namn
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    // Metod för att lägga till en utgång från detta rum till ett annat
    public void setExit(String direction, Room neighbor) {
        exits.put(direction.toLowerCase(), neighbor);
    }

    // Metod för att hämta rummet som en viss utgång leder till
    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }
}