package se.iths.java24.textadventure.world;

public class Item {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // En metod för att visa föremålets detaljer, kan vara användbar
    public void look() {
        System.out.println(description);
    }
}
    