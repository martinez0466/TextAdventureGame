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
        // Skapa rummen
        Room darkCave = new Room("En mörk grotta", "Fukt droppar från taket. Det är svårt att se något speciellt i mörkret.");
        Room forestPath = new Room("En slingrande skogsstig", "Stigen kantas av höga tallar och solljuset silar ner genom lövverket.");
        Room oldBridge = new Room("En gammal träbro", "Bron knarrar oroväckande under dina fötter och sträcker sig över en djup ravin.");
        Room sunnyClearing = new Room("En solig glänta", "Vilda blommor växer här och ett stilla sus hörs från träden.");

        // Definiera utgångar från rummen
        //Från mörka grottan kan man gå österut till skogsstigen
        darkCave.setExit("öster", forestPath);

        // Från skogsstigen kan man gå västerut tillbaka till grottan, eller norrut till bron
        forestPath.setExit("väster", darkCave);
        forestPath.setExit("norr", oldBridge);

        // Från gamla bron kan man gå söderut tillbaka till skogsstigen, eller österut till gläntan
        oldBridge.setExit("söder", forestPath);
        oldBridge.setExit("öster", sunnyClearing);

        // Från soliga gläntan kan man gå västerut tillbaka till bron
        sunnyClearing.setExit("väster", oldBridge);

        // Sätt spelarens nuvarande rum till det rum vi just skapade
        currentRoom = darkCave;
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
        String command;
        String argument = ""; // för framtida bruk om vi har kommandon med argument
        // Dela upp input i kommando och eventuellt argument
        // T.ex. "gå norr" -> command="gå", argument="norr"
        // T.ex. "norr" -> command="norr", argument=""
        String[] parts = input.split(" ", 2); // Dela vid första mellanslaget, max 2 delar
        command = parts[0];
        if (parts.length > 1) {
            argument = parts[1];
        }

        if (command.equals("avsluta")) {
            gameRunning = false;
            System.out.println("Du väljer att avsluta äventyret.");
        } else if (command.equals("gå")) {
            // Om kommandot är "gå", är argumentet riktningen
            movePlayer(argument);
        } else if (command.equals("norr") || command.equals("söder") || command.equals("öster") || command.equals("väster")) {
            // Om kommandot direkt är en riktning
            movePlayer(command);
        }
        // Här kan vi lägga till fler kommandon i framtiden med else if
        else {
            System.out.println("Jag förstår inte kommandot: '" + input + "'. Försök 'gå <riktning>', '<riktning>' eller 'avsluta'.");
        }
        System.out.println(); // Lägg till en tom rad för bättre läsbarhet
    }

    // Ny privat hjälpmetod för att hantera spelarens rörelse
    private void movePlayer(String direction) {
        if (direction.isEmpty()) {
            System.out.println("Gå vart då?");
            return;
        }

        Room nextRoom = currentRoom.getExit(direction); // Hämta rummet som utgången leder till

        if (nextRoom == null) {
            System.out.println("Du kan inte gå åt det hållet.");
        } else {
            currentRoom = nextRoom; // Flytta spelaren till det nya rummet
            // currentRoom.displayRoomInfo(); // Visa info om det nya rummet direkt (redan i huvudloopen)
            System.out.println("Du går " + direction + ".");
        }
    }

    public static void main(String[] args) {
        Game adventureGame = new Game();
        adventureGame.start();
    }
}
