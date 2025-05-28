package se.iths.java24.textadventure;

public class Room {
    String name;
    String description;

    // Konstruktor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Metod för att visa rummets information
    public void displayRoomInfo(){
        System.out.println("Du är i: " + name);
        System.out.println(description);
    }
}
