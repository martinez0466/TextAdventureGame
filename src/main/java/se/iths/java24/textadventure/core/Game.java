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
}
