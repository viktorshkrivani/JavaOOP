import java.util.ArrayList;
import java.util.List;

public class Artist {
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
