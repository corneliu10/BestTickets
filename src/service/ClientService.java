package service;

import model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private static List<Client> clients = new ArrayList<>();
    private static ClientService instance = new ClientService();

    private ClientService() {
        createClient("Coco", "Dumi", 20);
    }

    public static ClientService getInstance() {
        return instance;
    }

    public Client createClient(String firstName, String lastName, int age) {
        Client client = new Client(firstName, lastName, age);
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
}
