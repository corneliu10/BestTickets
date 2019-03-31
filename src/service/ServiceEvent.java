package service;

import model.Artist;
import model.Client;
import model.Event;

import java.util.Date;

public class ServiceEvent {
    private static ServiceEvent instance = new ServiceEvent();
    private static Event[] events = new Event[1];

    private ServiceEvent() {
        Artist artist = new Artist("Martin", "Garrix", 20);
        Client client = new Client("John", "Witzky", 21);
        events[0] = new Event("Untold", "Cluj-Napoca", new Date(2019, 8, 23),
                              new Date(2019, 9, 27), 400);
        events[0].addArtist(artist);
        events[0].buyTicket(client);
    }
}
