package model;

public class Ticket {
    private static int nrOfTickets = 0;
    private int id;
    private Client client = null;
    private int price;
    private int idEvent;

    private void incrementTickets() {
        nrOfTickets++;
        id = nrOfTickets;
    }

    public Ticket(int price, int idEvent) {
        this.price = price;
        this.idEvent = idEvent;
        incrementTickets();
    }

    public Ticket(Client client, int price, int idEvent) {
        this.client = client;
        this.price = price;
        this.idEvent = idEvent;
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

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public static int getNrOfTickets() {
        return nrOfTickets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket id: "); sb.append(this.id);
        sb.append('\n');
        sb.append("Price: "); sb.append(this.price);
        sb.append("\n");
        if (this.client != null) {
            sb.append("Client: "); sb.append(this.client);
            sb.append("\n");
        }
        sb.append("----");
        sb.append("\n");

        return sb.toString();
    }
}
