package model;

public interface IEvent  {
    void addArtist(Artist artist);
    boolean removeArtist(Artist artist);

    Ticket generateTicket();
    boolean buyTicket(Client client);
    boolean removeTicket(Ticket ticket);
    boolean containsTicket(Ticket ticket);
}
