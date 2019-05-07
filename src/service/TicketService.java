package service;

import logging.Logger;
import model.Event;
import model.Ticket;
import sun.rmi.runtime.Log;
import utilities.CSVUtilities;

import java.util.ArrayList;
import java.util.List;

public class TicketService extends CSVUtilities<Ticket> {
    private static final String FILENAME = "tickets.csv";

    private static List<Ticket> tickets = new ArrayList<>();
    private static TicketService instance = new TicketService();

    private TicketService() {
        if (!CSVService.fileExists(FILENAME)) {
            CSVService.createHeaderFile(FILENAME, new String[]{"Price", "Event ID", "Ticket ID"});
        }
    }

    public Ticket createTicket(int price, int eventId, boolean verbose) {
        return createTicket(price, eventId, Ticket.nrOfTickets, verbose);
    }

    public Ticket createTicket(int price, int eventId) {
        return createTicket(price, eventId, Ticket.nrOfTickets, false);
    }

    private Ticket createTicket(int price, int eventId, int ticketID, boolean verbose) {
        Event event = EventService.getInstance().getEventById(eventId);
        if (event == null) {
            // no event with this id
            String msg = "Event with " + eventId + " does not exist!";
            Logger.getInstance().error(msg, true);
            return null;
        }

        Ticket ticket = new Ticket(price, eventId, ticketID);
        tickets.add(ticket);
        event.addTicket(ticket);
        Logger.getInstance().info("Created ticket for event " + eventId, verbose);

        return ticket;
    }

    @Override
    public String[] extractAttributes(Ticket ticket) {
        String price = String.valueOf(ticket.getPrice());
        String eventId = String.valueOf(ticket.getIdEvent());
        String ticketId = String.valueOf(ticket.getId());

        return new String[]{price, eventId, ticketId};
    }

    public void loadObjects() {
        super.loadObjects(FILENAME, this::createTicket);
    }

    public Ticket createTicket(String[] attributes) {
        int price = Integer.parseInt(attributes[0]);
        int eventId = Integer.parseInt(attributes[1]);
        int ticketId = Integer.parseInt(attributes[2]);

        return createTicket(price, eventId, ticketId, false);
    }

    public static TicketService getInstance() {
        return instance;
    }

    public void writeToFile(Ticket obj) {
        super.writeToFile(obj, FILENAME);
    }

    public void showTickets() {
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }


}
