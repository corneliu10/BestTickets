package model;

public class Ticket {
    public static int nrOfTickets = 0;
    private Client client = null;
    private int price;
    private int idEvent;
    private int id;

    private void incrementTickets() {
        nrOfTickets++;
    }

    public Ticket(int price, int idEvent, int id) {
        this.price = price;
        this.idEvent = idEvent;
        this.id = id;
        incrementTickets();
    }

    public Ticket(Client client, int price, int idEvent, int id) {
        this.client = client;
        new Ticket(price, idEvent, id);
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

    public int getIdEvent() {
        return idEvent;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "client=" + client +
                ", price=" + price +
                ", idEvent=" + idEvent +
                ", id=" + id +
                '}';
    }
}
