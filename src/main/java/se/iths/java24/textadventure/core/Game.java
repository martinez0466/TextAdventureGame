package se.iths.java24.textadventure.core;

import se.iths.java24.textadventure.world.Room;

import java.util.Scanner;

public class Game {
    private Room currentRoom;       // Kommer att hålla det rum spelaren är i just nu
    private Scanner scanner;        // För att läsa vad spelaren skriver in
    private boolean gameRunning;    // En flagga för att veta om spelet ska fortsätta köras

    // Konstruktor för Game-klassen
    public Game() {
        scanner = new Scanner(System.in);   // Initiera scanner för att läsa tangentbordet
        gameRunning = true;                 // Spelet ska köras från start
        initializeGame();                   // Anropa en metod för att sätta upp spelets starttillstånd
    }

    // En privat hjälpmetod för att skapa det första rummet och sätta starttillståndet
    private void initializeGame() {
        // Skapa vårt första rum-objekt
        Room startingRoom = new Room("En mörk grotta", "Fukt droppar från taket. Det är svårt att se något speciellt i mörkret.");
        // Sätt spelarens nuvarande rum till det rum vi just skapade
        currentRoom = startingRoom;
    }

    // Metod för att starta och köra spellopen
    public void start() {
        System.out.println("Välkommen till Äventyret!");
        System.out.println("-------------------------");

        while (gameRunning) {
            currentRoom.displayRoomInfo(); // Visa information om nuvarande rum
            System.out.print("> "); // Skriv ut en prompt för användaren

            String input = scanner.nextLine().toLowerCase().trim();

            processInput(input); // Skicka input till en metod för bearbetning
        }

        System.out.println("-------------------------");
        System.out.println("Tack för att du spelade! Hejdå.");
        scanner.close();
    }

    // Metod för att bearbeta användarens input
    private void processInput(String input) {
        if (input.equals("avsluta")) {
            gameRunning = false; // Sätt gameRunning till false för att avsluta loopen
            System.out.println("Du väljer att avsluta äventyret.");
        } else {
            System.out.println("Jag förstår inte kommandot: '" + input + "'. Försök 'avsluta'.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Game adventureGame = new Game();
        adventureGame.start();
    }
}
