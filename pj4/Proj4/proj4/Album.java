public class Album {

    private String albumID;
    private String artistID;
    private String name;


    public Album(String albumID,String artistID, String name) {
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
