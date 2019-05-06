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
