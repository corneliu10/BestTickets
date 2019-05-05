package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<Ticket> tickets;

    public Client(String firstName, String lastName, int age, int id) {
        super(firstName, lastName, age, id);
        this.tickets = new ArrayList<>();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
