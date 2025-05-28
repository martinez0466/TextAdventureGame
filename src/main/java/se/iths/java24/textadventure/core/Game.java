package se.iths.java24.textadventure.core;

import se.iths.java24.textadventure.world.Item;
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

    // En privat hjälpmetod för att skapa rummen, föremålen och sätta starttillståndet
    private void initializeGame() {
        // Skapa rummen
        Room darkCave = new Room("En mörk grotta", "Fukt droppar från taket. Det är svårt att se något speciellt i mörkret.");
        Room forestPath = new Room("En slingrande skogsstig", "Stigen kantas av höga tallar och solljuset silar ner genom lövverket.");
        Room oldBridge = new Room("En gammal träbro", "Bron knarrar oroväckande under dina fötter och sträcker sig över en djup ravin.");
        Room sunnyClearing = new Room("En solig glänta", "Vilda blommor växer här och ett stilla sus hörs från träden.");

        // Skapa några föremål
        Item rustyKey = new Item("rostig nyckel", "En liten, rostig nyckel. Den ser ut att kunna passa i ett litet lås.");
        Item oldMap = new Item("gammal karta", "En bit skört pergament med en handritad karta. Vissa delar är svåra att tyda.");
        Item shinyStone = new Item("glänsande sten", "En slät, rund sten som glimmar svagt i ljuset.");
        // Item woodenShield = new Item("träsköld", "En enkel men stadig träsköld. Den har några repor."); // Oanvänt föremål, kan läggas till senare

        // Placera ut föremål i rummen
        darkCave.addItem(rustyKey);       // Nyckeln ligger i grottan
        forestPath.addItem(shinyStone);   // Den glänsande stenen ligger på skogsstigen
        sunnyClearing.addItem(oldMap);    // Kartan finns i den soliga gläntan
        // Gamla bron har inga föremål just nu

        // Definiera utgångar från rummen
        darkCave.setExit("öster", forestPath);

        forestPath.setExit("väster", darkCave);
        forestPath.setExit("norr", oldBridge);

        oldBridge.setExit("söder", forestPath);
        oldBridge.setExit("öster", sunnyClearing);

        sunnyClearing.setExit("väster", oldBridge);

        // Sätt spelarens nuvarande rum till startrummet
        currentRoom = darkCave; // Spelaren börjar i den mörka grottan
    }

    // Metod för att starta och köra spellopen
    public void start() {
        System.out.println("Välkommen till Äventyret!");
        System.out.println("-------------------------");

        while (gameRunning) {
            currentRoom.displayRoomInfo(); // Visa information om nuvarande rum
            System.out.print("> "); // Skriv ut en prompt för användaren

            String input = scanner.nextLine().toLowerCase().trim(); // Läs input, gör om till små bokstäver och ta bort extra mellanslag

            processInput(input); // Skicka input till en metod för bearbetning
        }

        System.out.println("-------------------------");
        System.out.println("Tack för att du spelade! Hejdå.");
        scanner.close(); // Stäng scannern när spelet är slut för att frigöra resurser
    }

    // Metod för att bearbeta användarens input
    private void processInput(String input) {
        String command;
        String argument = "";

        String[] parts = input.split(" ", 2); // Dela vid första mellanslaget, max 2 delar
        command = parts[0];
        if (parts.length > 1) {
            argument = parts[1];
        }

        // Ta bort "på" om det finns i argumentet för "titta på"
        // Exempel: "titta på nyckel" -> argument blir "nyckel"
        if (command.equals("titta") && argument.startsWith("på ")) {
            argument = argument.substring(3).trim(); // Ta bort "på " (3 tecken)
        }


        if (command.equals("avsluta")) {
            gameRunning = false;
            System.out.println("Du väljer att avsluta äventyret.");
        } else if (command.equals("gå")) {
            movePlayer(argument);
        } else if (command.equals("norr") || command.equals("söder") || command.equals("öster") || command.equals("väster")) {
            // Om kommandot direkt är en riktning
            movePlayer(command);
        } else if (command.equals("titta")) {
            if (argument.isEmpty()) {
                // Om bara "titta", visa ruminfo igen
                System.out.println("Du ser dig omkring i rummet igen.");
                currentRoom.displayRoomInfo(); // Visa rummets info igen explicit
            } else {
                // Försök hitta och beskriva föremålet
                lookAtItem(argument);
            }
        }
        // Här kan vi lägga till fler kommandon i framtiden med else if
        else {
            System.out.println("Jag förstår inte kommandot: '" + input + "'.");
            System.out.println("Försök: 'gå <riktning>', '<riktning>', 'titta', 'titta på <föremål>' eller 'avsluta'.");
        }
        System.out.println(); // Lägg till en tom rad för bättre läsbarhet efter varje kommando
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
            System.out.println("Du går " + direction + ".");
            // Informationen om det nya rummet visas i början av nästa loop-iteration
        }
    }

    // Ny privat hjälpmetod för att titta på ett föremål
    private void lookAtItem(String itemName) {
        if (itemName.isEmpty()) {
            System.out.println("Titta på vadå?");
            return;
        }

        Item itemToLookAt = currentRoom.getItem(itemName); // Använd getItem från Room

        if (itemToLookAt != null) {
            System.out.println("Du tittar närmare på " + itemToLookAt.getName() + ":");
            itemToLookAt.look(); // Använd Item-klassens look-metod
        } else {
            System.out.println("Du kan inte se något '" + itemName + "' här.");
        }
    }

    // Main-metoden - startpunkten för programmet
    public static void main(String[] args) {
        Game adventureGame = new Game(); // Skapa ett nytt Game-objekt (detta anropar konstruktorn)
        adventureGame.start();           // Anropa start()-metoden på objektet för att dra igång spelet
    }
}titta