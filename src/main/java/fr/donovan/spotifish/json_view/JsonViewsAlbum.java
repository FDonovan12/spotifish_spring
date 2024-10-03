package fr.donovan.spotifish.json_view;

public class JsonViewsAlbum {
    
    public interface AllAlbum extends Artist, JsonViewsSongStorage.AllSongStorage {}
    public interface MinimalAlbum extends JsonViewsSongStorage.MinimalSongStorage {}

    public interface AllAlbumWithoutId extends Artist, JsonViewsSongStorage.AllSongStorageWithoutId {}
    public interface MinimalAlbumWithoutId extends JsonViewsSongStorage.MinimalSongStorageWithoutId {}

    public interface Artist  {}
}