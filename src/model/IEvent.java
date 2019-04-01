package model;

public interface IEvent  {
    void addArtist(Artist artist);
    boolean removeArtist(Artist artist);

    Ticket generateTicket();
    boolean addTicket(Ticket ticket);
    boolean buyNewTicket(Client client);
    boolean assignTicket(Client client, Ticket ticket);
    boolean removeTicket(Ticket ticket);
    boolean containsTicket(Ticket ticket);
    int ticketsSold();
}
