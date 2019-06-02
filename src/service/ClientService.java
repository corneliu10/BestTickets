package service;

import logging.Logger;
import model.Client;
import sun.rmi.runtime.Log;
import utilities.CSVUtilities;
import utilities.ConnectionUtilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends CSVUtilities<Client> {
    private static final String FILENAME = "clients.csv";

    private static List<Client> clients = new ArrayList<>();
    private static ClientService instance = new ClientService();

    private ConnectionUtilities connectionUtilities = ConnectionUtilities.getInstance();

    private ClientService() {
        if (!CSVService.fileExists(FILENAME)) {
            CSVService.createHeaderFile(FILENAME, new String[]{"First Name", "Last Name", "Age", "Id"});
        }
    }

    public static ClientService getInstance() {
        return instance;
    }

    public Client createClient(String firstName, String lastName, int age, boolean verbose) {
        int id = Client.nrPersons;
        return createClient(firstName, lastName, age, id, verbose);
    }

    public Client createClient(String firstName, String lastName, int age) {
        int id = Client.nrPersons;
        return createClient(firstName, lastName, age, id, false);
    }

    private Client createClient(String firstName, String lastName, int age, int id, boolean verbose) {
        Client client = new Client(firstName, lastName, age, id);
        clients.add(client);
        Logger.getInstance().info("Client " + client.toString() + " created!", verbose);
        return client;
    }

    public void loadObjects() {
        super.loadObjects(FILENAME, this::createClient);
    }

    public Client createClient(String[] attributes) {
        String firstName = attributes[0];
        String lastName = attributes[1];
        int age = Integer.parseInt(attributes[2]);
        int id = Integer.parseInt(attributes[3]);

        return createClient(firstName, lastName, age, id, false);
    }

    public Client getClientById(int idClient) {
        for (Client client : clients) {
            if (client.getId() == idClient)
                return client;
        }

        return null;
    }

    public String[] extractAttributes(Client client) {
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String age = String.valueOf(client.getAge());
        String id = String.valueOf(client.getId());

        return new String[]{firstName, lastName, age, id};
    }

    public void writeToFile(Client obj) {
        super.writeToFile(obj, FILENAME);
    }

    public void showClients() {
        List<Client> clients = getClients();

        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }

    public List<Client> getClients() {
        String query = "select * from tickets.clients where isArtist=0;";
        List<Client> clients = new ArrayList<>();
        ResultSet rs = connectionUtilities.selectData(query);
        try {
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                int id = rs.getInt("idClient");

                clients.add(new Client(firstName, lastName, age, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public void insertClient(Client obj) {
        String query = "insert into tickets.clients(firstName, lastName, age, isArtist) " +
                 "values ('" + obj.getFirstName() + "','" + obj.getLastName() + "'," + obj.getAge() + ",0);";

        connectionUtilities.updateData(query);
    }

    public void removeClient(int clientID) {
        String query = "delete from tickets.clients where idClient=" + clientID +";";

        connectionUtilities.updateData(query);
    }

    public void updateClient(String firstName, String lastName, int age, int clientId) {
        String query = "update tickets.clients set firstName='" + firstName + "', " +
                "lastName='"+lastName+"', age="+age+" where idClient="+clientId+";";

        connectionUtilities.updateData(query);
    }
}
