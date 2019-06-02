package service;

import logging.Logger;
import model.Artist;
import model.Client;
import model.Event;
import utilities.CSVUtilities;
import utilities.ConnectionUtilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistService extends CSVUtilities<Artist> {
    private static final String FILENAME = "artists.csv";
    private static final ArtistService instance = new ArtistService();
    private List<Artist> artists = new ArrayList<>();

    private ConnectionUtilities connectionUtilities = ConnectionUtilities.getInstance();

    private ArtistService() {
        if (!CSVService.fileExists(FILENAME)) {
            CSVService.createHeaderFile(FILENAME, new String[]{"First Name", "Last Name", "Age", "Id"});
        }
    }

    public static ArtistService getInstance() {
        return instance;
    }

    public Artist createArtist(String firstName, String lastName, int age, boolean verbose) {
        int id = Artist.nrPersons;
        return createArtist(firstName, lastName, age, id, verbose);
    }

    public Artist createArtist(String firstName, String lastName, int age) {
        int id = Artist.nrPersons;
        return createArtist(firstName, lastName, age, id, false);
    }

    private Artist createArtist(String firstName, String lastName, int age, int id, boolean verbose) {
        Artist artist = new Artist(firstName, lastName, age, id);
        artists.add(artist);
        Logger.getInstance().info("Artist " + artist.toString() + " created!", verbose);
        return artist;
    }

    public void loadObjects() {
        super.loadObjects(FILENAME, this::createArtist);
    }

    public Artist createArtist(String[] attributes) {
        String firstName = attributes[0];
        String lastName = attributes[1];
        int age = Integer.parseInt(attributes[2]);
        int id = Integer.parseInt(attributes[3]);

        return createArtist(firstName, lastName, age, id, false);
    }

    public Artist getArtistById(int idArtist) {
        for (Artist artist : artists) {
            if (artist.getId() == idArtist)
                return artist;
        }

        return null;
    }

    @Override
    public String[] extractAttributes(Artist artist) {
        String firstName = artist.getFirstName();
        String lastName = artist.getLastName();
        String age = String.valueOf(artist.getAge());
        String id = String.valueOf(artist.getId());

        return new String[]{firstName, lastName, age, id};
    }

    public void writeToFile(Artist obj) {
        super.writeToFile(obj, FILENAME);
    }

    public void showObjects() {
        super.showObjects(artists);
    }

    public List<Artist> getArtists() {
        String query = "select * from tickets.clients where isArtist=1;";
        List<Artist> artists = new ArrayList<>();

        ResultSet rs = connectionUtilities.selectData(query);
        try {
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                int id = rs.getInt("idClient");

                artists.add(new Artist(firstName, lastName, age, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return artists;
    }

    public void showArtists() {
        getArtists().stream().forEach(System.out::println);
    }

    public void insertArtist(Artist obj) {
        String query = "insert into tickets.clients(firstName, lastName, age, isArtist) " +
                "values ('" + obj.getFirstName() + "','" + obj.getLastName() + "'," + obj.getAge() + ",1);";

        connectionUtilities.updateData(query);
    }

    public void updateArtist(String firstName, String lastName, int age, int artistId) {
        String query = "update tickets.clients set firstName='" + firstName + "', " +
                "lastName='"+lastName+"', age="+age+" where idClient="+artistId+";";

        connectionUtilities.updateData(query);
    }

    public void removeArtist(int id) {
        String query = "delete from tickets.clients where idClient=" + id +";";

        connectionUtilities.updateData(query);
    }
}
