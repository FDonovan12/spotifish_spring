package fr.donovan.spotifish.json_view;

public class JsonViewsSongOrder {
    
    public interface AllSongOrder extends Id, Order, SongStorage, Song, Slug {}
    public interface MinimalSongOrder extends Id, Order, Slug {}
    public interface StorageSongOrder extends Id, Order, SongStorage, Slug {}
    public interface SongSongOrder extends Id, Order, Song, Slug {}

    public interface AllSongOrderWithoutId extends Order, SongStorage, Song, Slug {}
    public interface MinimalSongOrderWithoutId extends Order, Slug {}

    public interface Id  {}
    public interface Order  {}
    public interface SongStorage  {}
    public interface Song  {}
    public interface Slug  {}
}