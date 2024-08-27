import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Album {
    private String albumID;
    private String artistID;
    private String name;

    public Album(String albumID, String artistID, String name) {
        this.albumID = albumID;
        this.artistID = artistID;
        this.name = name;
    }

    public String getAlbumID() {
        return albumID;
    }

    public String getArtistID() {
        return artistID;
    }

    public String getName() {
        return name;
    }
}

class Artist {
    private String artistID;
    private String name;
    private List<Album> albums;

    public Artist(String artistID, String name) {
        this.artistID = artistID;
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public String getArtistID() {
        return artistID;
    }

    public String getName() {
        return name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }
}

class MusicDB {
    private static final String DB_URL = "jdbc:sqlite:music_artists.sqlite";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static List<Artist> getAllArtistsWithAlbums() throws SQLException {
        List<Artist> artists = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String artistQuery = "SELECT * FROM Artists";
            ResultSet artistResultSet = statement.executeQuery(artistQuery);

            while (artistResultSet.next()) {
                String artistID = artistResultSet.getString("ArtistID");
                String artistName = artistResultSet.getString("Name");

                Artist artist = new Artist(artistID, artistName);

                String albumQuery = "SELECT * FROM Albums WHERE ArtistID = ?";
                try (var albumStatement = connection.prepareStatement(albumQuery)) {
                    albumStatement.setString(1, artistID);
                    ResultSet albumResultSet = albumStatement.executeQuery();
                    while (albumResultSet.next()) {
                        String albumID = albumResultSet.getString("AlbumID");
                        String albumName = albumResultSet.getString("Name");
                        Album album = new Album(albumID, artistID, albumName);
                        artist.addAlbum(album);
                    }
                }

                artists.add(artist);
            }
        }

        return artists;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Artists and Album Listning");
            System.out.println();

            List<Artist> artists = MusicDB.getAllArtistsWithAlbums();

            // Sort artists alphabetically by name
            artists.sort((a1, a2) -> a1.getName().compareTo(a2.getName()));

            // Sort albums by name
            for (Artist artist : artists) {
                artist.getAlbums().sort((album1, album2) -> album1.getName().compareTo(album2.getName()));
            }

            // Display a list of artists
            System.out.println("Artists");
            System.out.println("-------");
            for (Artist artist : artists) {
                System.out.println(artist.getName());
            }
            System.out.println();

            // Display a list of albums
            System.out.println("Albums");
            System.out.println("------");
            List<Album> allAlbums = new ArrayList<>();
            for (Artist artist : artists) {
                allAlbums.addAll(artist.getAlbums());
            }
            allAlbums.sort((album1, album2) -> album1.getName().compareTo(album2.getName()));
            for (Album album : allAlbums) {
                System.out.println(album.getName());
            }
            System.out.println();

            // Display a list of albums by artist
            System.out.println("Albums by Artist");
            System.out.println("----------------");
            for (Artist artist : artists) {
                System.out.println(artist.getName());
                List<Album> artistAlbums = artist.getAlbums();
                artistAlbums.sort((album1, album2) -> album1.getName().compareTo(album2.getName()));
                for (Album album : artistAlbums) {
                    System.out.println("       " + album.getName());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error accessing the database: " + ex.getMessage());
        }
    }
}