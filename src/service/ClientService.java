package service;

import logging.Logger;
import model.Client;
import utilities.CSVUtilities;

import java.util.ArrayList;
import java.util.List;

public class ClientService extends CSVUtilities<Client> {
    private static final String FILENAME = "clients.csv";

    private static List<Client> clients = new ArrayList<>();
    private static ClientService instance = new ClientService();

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
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
