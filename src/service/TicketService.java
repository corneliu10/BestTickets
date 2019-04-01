package service;

import model.Client;
import model.Event;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private static List<Ticket> tickets = new ArrayList<>();
    private static TicketService instance = new TicketService();

    private TicketService() {
        Event event = EventService.getInstance().getEventById(1);
        Client client = ClientService.getInstance().getClientById(3);
        createTicket(10, event.getId());
        event.addTicket(tickets.get(0));
        event.assignTicket(client, tickets.get(0));
    }

    public Ticket createTicket(int price, int eventId) {
        Ticket ticket = new Ticket(price, eventId);
        tickets.add(ticket);
        return ticket;
    }

    public static TicketService getInstance() {
        return instance;
    }

    public void showTickets() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }
}
