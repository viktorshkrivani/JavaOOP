import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Proj4 {
        public static void main(String[] args) {
            try {
                System.out.println("Artists and Album Listning");
                System.out.println();

                List<Artist> artists = MusicDB.getAllArtwithAlb();


                artists.sort((a1, a2) -> a1.getName().compareTo(a2.getName()));


                for (Artist artist : artists) {
                    artist.getAlbums().sort((album1, album2) -> album1.getName().compareTo(album2.getName()));
                }


                System.out.println("Artists");
                System.out.println("-------");
                for (Artist artist : artists) {
                    System.out.println(artist.getName());
                }
                System.out.println();


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


                System.out.println("Albums by Artist");
                System.out.println("----------------");
                for (Artist artist : artists) {
                    System.out.println(artist.getName());
                    List<Album> artistAlbums = artist.getAlbums();
                    artistAlbums.sort((album1, album2) -> album1.getName().compareTo(album2.getName()));
                    for (Album album : artistAlbums) {
                        System.out.println("        " + album.getName());
                    }
                }
            } catch (SQLException ex) {
                System.out.println("Error accessing the database: " + ex.getMessage());
            }
        }
    }
