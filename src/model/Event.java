package model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event implements IEvent {
    private static int nrOfEvents = 0;
    private int id;

    private String title;
    private Map<Integer, Ticket> tickets;
    private int maxTickets;
    private String location;
    private List<Artist> artists;
    private Date startDate;
    private Date endDate;

    public Event(String title, String location, Date startDate, Date endDate, int maxTickets) {
        this.title = title;
        this.maxTickets = maxTickets;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tickets = new HashMap<Integer, Ticket>();

        nrOfEvents++;
        id = nrOfEvents;
    }

    public int getId() {
        return id;
    }

    public static int getNrOfEvents() {
        return nrOfEvents;
    }

    @Override
    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    @Override
    public boolean removeArtist(Artist artist) {
        if (artists.contains(artist)) {
            artists.remove(artist);
            return true;
        }

        return false; // artist not found
    }

    @Override
    public Ticket generateTicket() {
        Ticket ticket = new Ticket(100, this.id); // TODO set custom price per event
        return ticket;
    }

    @Override
    public boolean buyTicket(Client client) {
        if (tickets.size() < maxTickets) {
            Ticket ticket = generateTicket();
            ticket.setClient(client);
            client.addTicket(ticket);

            tickets.put(ticket.getId(), ticket);
            return true;
        }

        return false; // sold out
    }

    @Override
    public boolean removeTicket(Ticket ticket) {
        if (this.containsTicket(ticket)) {
            tickets.remove(ticket.getId());
            return true;
        }

        return false; // ticket not found
    }

    @Override
    public boolean containsTicket(Ticket ticket) {
        if (tickets.containsKey(ticket.getId()))
            return true;

        return false;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxTickets() {
        return maxTickets;
    }

    public String getLocation() {
        return location;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
