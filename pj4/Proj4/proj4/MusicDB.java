import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MusicDB {
    private static final String DB_URL = "jdbc:sqlite:music_artists.sqlite";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public  static List<Artist> getAllArtwithAlb() throws SQLException {
        List<Artist> artists = new ArrayList<>();

        try (Connection connection =getConnection();
             Statement statement = connection.createStatement()) {
            String artistQuery = "SELECT * FROM Artists";
            ResultSet artistResultSet = statement.executeQuery(artistQuery);

            while (artistResultSet.next()) {
                String artistID = artistResultSet.getString("ArtistID");
                String artistName = artistResultSet.getString("Name");

                Artist artist = new Artist(artistID, artistName);

                String albumQuery = "SELECT * FROM Albums WHERE ArtistID = ?";
                try (PreparedStatement albumStatement = connection.prepareStatement(albumQuery)) {
                     albumStatement.setString(1,artistID);
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
