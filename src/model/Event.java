package model;

import java.util.*;

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
        this.artists = new ArrayList<Artist>();

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event id: "); sb.append(this.id);
        sb.append('\n');
        sb.append("Event Title: "); sb.append(this.title);
        sb.append('\n');
        sb.append("Artists: ");
        for (Artist artist : artists) {
            sb.append(artist.toString());
        }
        sb.append("Location: "); sb.append(location);
        sb.append('\n');
        sb.append("Start date: "); sb.append(this.startDate.toString());
        sb.append('\n');
        sb.append("End date: "); sb.append(this.endDate.toString());
        sb.append('\n');
        sb.append("Tickets sold: "); sb.append(this.ticketsSold());
        sb.append('\n');
        sb.append("--------------");
        sb.append('\n');
        return sb.toString();
    }

    @Override
    public Ticket generateTicket() {
        Ticket ticket = new Ticket(100, this.id);
        tickets.put(ticket.getId(), ticket);

        return ticket;
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        if (tickets.size() < maxTickets) {
            tickets.put(ticket.getId(), ticket);
            return true;
        }

        return false; // sold out
    }

    @Override
    public boolean buyNewTicket(Client client) {
        if (tickets.size() < maxTickets) {
            Ticket ticket = generateTicket();
            ticket.setClient(client);
            client.addTicket(ticket);

            return true;
        }

        return false; // sold out
    }

    @Override
    public boolean assignTicket(Client client, Ticket ticket) {
        if (ticket.getIdEvent() == getId()) {
            ticket.setClient(client);
            client.addTicket(ticket);
            return true;
        }

        return false; // ticket not for this event
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

    @Override
    public int ticketsSold() {
        int count = 0;
        for (Map.Entry<Integer, Ticket> entry : tickets.entrySet()) {
            if (entry.getValue().getClient() != null)
                count++;
        }

        return count;
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
