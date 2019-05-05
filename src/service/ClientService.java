package service;

import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

public class ClientService {
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

    public Client createClient(String firstName, String lastName, int age) {
        int id = Client.nrPersons;
        Client client = new Client(firstName, lastName, age, id);
        clients.add(client);
        return client;
    }

    public void loadClients() {
        List<String[]> clients = CSVService.readFromFile(FILENAME);
        clients.remove(0); // removing header

        for (String[] clientAttributes : clients) {
            createClient(clientAttributes);
        }
    }

    public Client createClient(String[] attributes) {
        String firstName = attributes[0];
        String lastName = attributes[1];
        int age = Integer.parseInt(attributes[2]);
        int id = Integer.parseInt(attributes[3]);

        Client client = new Client(firstName, lastName, age, id);
        clients.add(client);

        return client;
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

    public void writeClientToFile(Client client) {
        String[] attributes = extractAttributes(client);
        CSVService.writeDataToFile(FILENAME, attributes);
    }

    public void showClients() {
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
