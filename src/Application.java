import model.Client;
import model.Event;
import service.ClientService;
import service.EventService;

import java.util.Date;
import java.util.Scanner;

public class Application {
    private static final int OPTIONS = 5;
    private static ClientService clientService = ClientService.getInstance();
    private static EventService eventService = EventService.getInstance();

    public static void run() {
        loadData();
        while (true) {
            showOptions();
            executeCommand(readKey());
        }
    }

    private static void loadData() {
        clientService.loadClients();
        eventService.loadEvents();
    }

    private static void showOptions() {
        System.out.println("1. Add client");
        System.out.println("2. Add artist");
        System.out.println("3. Add event");
        System.out.println("4. Show clients");
        System.out.println("5. Show events");
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
                showClients();
                break;
            case 5:
                showEvent();
                break;
            default:
                System.out.println("Wrong input! Try again...");
                break;
        }
    }

    private static void showEvent() {
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

        Event event = eventService.createEvent(title, location, startDate, endDate, maxTickets);
        eventService.writeEventToFile(event);
    }

    private static void addArtist() {
    }

    private static void addClient() {
        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        int age = Integer.parseInt(readString("Age: "));

        Client client = clientService.createClient(firstName, lastName, age);
        clientService.writeClientToFile(client);
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
