package service;

import javafx.util.Pair;
import logging.Logger;
import model.Artist;
import model.Event;

import java.util.Arrays;
import java.util.List;

public class EventArtistsService {
    private static final String FILENAME = "event_artists.csv";
    private static final EventArtistsService instance = new EventArtistsService();

    private EventArtistsService() {
        if (!CSVService.fileExists(FILENAME)) {
            CSVService.createHeaderFile(FILENAME, new String[]{"artistID", "eventID"});
        }
    }

    public static EventArtistsService getInstance() {
        return instance;
    }

    public void writeToFile(int eventID, int artistID) {
        String[] attributes = new String[]{String.valueOf(eventID), String.valueOf(artistID)};
        CSVService.writeDataToFile(FILENAME, attributes);
    }

    public void loadObjects() {
        List<String[]> attributes = CSVService.readFromFile(FILENAME);
        attributes.remove(0); // removing header

        Logger.getInstance().info("Loading " + FILENAME + "...");
        for (String[] attribute : attributes) {
            try {
                int eventID = Integer.parseInt(attribute[0]);
                int artistID = Integer.parseInt(attribute[1]);
                loadPair(eventID, artistID);
            } catch (Exception e) {
                Logger.getInstance().error("Error on loading " + Arrays.toString(attribute));
                e.printStackTrace();
            }
        }

        Logger.getInstance().info("Objects loaded!");
    }

    public Pair<Event, Artist> validatePair(int eventID, int artistID) {
        Artist artist = ArtistService.getInstance().getArtistById(artistID);
        if (artist == null) {
            Logger.getInstance().error("Artist " + artistID + " not found!", true);
            return null;
        }

        Event event = EventService.getInstance().getEventById(eventID);
        if (event == null) {
            Logger.getInstance().error("Event " + eventID + " not found!", true);
            return null;
        }

        return new Pair<>(event, artist);
    }

    public void loadPair(int eventID, int artistID) {
        Pair<Event, Artist> pair = validatePair(eventID, artistID);

        if (pair != null) {
            pair.getValue().addEvent(pair.getKey());
            pair.getKey().addArtist(pair.getValue());
            return;
        }

        Logger.getInstance().error("Pair eventID: " + eventID + " - artistID: " + artistID + " not found!");
    }

    public void addPair(int eventID, int artistID) {
        if (validatePair(eventID, artistID) == null) {
            return;
        }

        Artist artist = ArtistService.getInstance().getArtistById(artistID);
        Event event = EventService.getInstance().getEventById(eventID);

        artist.addEvent(event);
        event.addArtist(artist);
        writeToFile(eventID, artistID);
    }
}
