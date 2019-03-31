package model;

public class Ticket {
    private static int nrOfTickets = 0;
    private int id;
    private Client client;
    private int price;
    private int indexOfEvent;

    private void incrementTickets() {
        nrOfTickets++;
        id = nrOfTickets;
    }

    public Ticket(int price, int indexOfEvent) {
        this.price = price;
        this.indexOfEvent = indexOfEvent;
        incrementTickets();
    }

    public Ticket(Client client, int price, int indexOfEvent) {
        this.client = client;
        this.price = price;
        this.indexOfEvent = indexOfEvent;
        incrementTickets();
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getIndexOfEvent() {
        return indexOfEvent;
    }

    public void setIndexOfEvent(int indexOfEvent) {
        this.indexOfEvent = indexOfEvent;
    }

    public static int getNrOfTickets() {
        return nrOfTickets;
    }
}
