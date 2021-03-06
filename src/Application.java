import model.Artist;
import model.Client;
import model.Event;
import model.Ticket;
import service.*;

import java.util.Date;
import java.util.Scanner;

public class Application extends Thread {
    private static final String[] menuOptions = new String[] {
        "1. Add client",
        "2. Add artist",
        "3. Add event",
        "4. Add ticket",
        "5. Assign event to artist",
        "6. Show clients",
        "7. Show events",
        "8. Show tickets",
        "9. Show artists",
    };
    private static final int OPTIONS = menuOptions.length;

    private static ClientService clientService = ClientService.getInstance();
    private static EventService eventService = EventService.getInstance();
    private static TicketService ticketService = TicketService.getInstance();
    private static ArtistService artistService = ArtistService.getInstance();
    private static EventArtistsService eventArtistsService = EventArtistsService.getInstance();

    public void run() {
        loadData();
        while (true) {
            showOptions();
            executeCommand(readKey());
        }
    }

    private static void loadData() {
        eventArtistsService.loadEventsArtists();
    }

    private static void showOptions() {
        for (String option : menuOptions) {
            System.out.println(option);
        }
    }

    private static void executeCommand(int readKey) {
        switch (readKey) {
            case 1:
                addClient();
                break;
            case 2:
                addArtist();
                break;
            case 3:
                addEvent();
                break;
            case 4:
                addTicket();
                break;
            case 5:
                assignEventToArtist();
                break;
            case 6:
                showClients();
                break;
            case 7:
                showEvents();
                break;
            case 8:
                showTickets();
                break;
            case 9:
                showArtists();
                break;
            default:
                System.out.println("Wrong input! Try again...");
                break;
        }
    }

    private static void assignEventToArtist() {
        int artistID = Integer.parseInt(readString("Artist ID: "));
        int eventID = Integer.parseInt(readString("Event ID: "));

        eventArtistsService.addPair(eventID, artistID);
        eventArtistsService.insertEventArtist(eventID, artistID);
    }

    private static void showArtists() {
        artistService.showArtists();
    }

    private static void showTickets() {
        ticketService.showTickets();
    }

    private static void addTicket() {
        int price = Integer.parseInt(readString("Price: "));
        int eventID = Integer.parseInt(readString("Event ID: "));

        Ticket ticket = new Ticket(price, eventID, -1);
        ticketService.insertTicket(ticket);
    }

    private static void showEvents() {
        eventService.showEvents();
    }

    private static void showClients() {
       clientService.showClients();
    }

    private static void addEvent() {
        String title = readString("Title: ");
        String location = readString("Location: ");
        Date startDate = new Date();
        Date endDate = new Date();
        int maxTickets = Integer.parseInt(readString("Max Tickets: "));

        Event event = new Event(title, location, startDate, endDate, maxTickets, -1);
        eventService.insertEvent(event);
    }

    private static void addArtist() {
        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        int age = Integer.parseInt(readString("Age: "));

        Artist artist = new Artist(firstName, lastName, age, -1);
        artistService.insertArtist(artist);
    }

    private static void addClient() {
        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        int age = Integer.parseInt(readString("Age: "));

        Client client = new Client(firstName, lastName, age, -1);
        clientService.insertClient(client);
    }

    private static int readKey() {
        Scanner sc = new Scanner(System.in);

        int option = sc.nextInt();
        if (option >= 1 && option <= OPTIONS) {
            return option;
        }

        return 0;
    }

    private static String readString(String info) {
        Scanner sc = new Scanner(System.in);
        System.out.println(info);
        return sc.next();
    }
}
