package fr.donovan.spotifish.json_view;

public class JsonViewsPlaylist {
    
    public interface AllPlaylist extends Owner, JsonViewsSongStorage.AllSongStorage {}
    public interface MinimalPlaylist extends JsonViewsSongStorage.MinimalSongStorage {}

    public interface AllPlaylistWithoutId extends Owner, JsonViewsSongStorage.AllSongStorageWithoutId {}
    public interface MinimalPlaylistWithoutId extends JsonViewsSongStorage.MinimalSongStorageWithoutId {}

    public interface Owner  {}
}