package fr.donovan.spotifish.json_view;

public class JsonViewsSong {
    
    public interface AllSong extends Duration, Artist, MusicalGenres, SongOrders, Album, JsonViewsLikeableItem.AllLikeableItem {}
    public interface PresentSong extends Artist, MusicalGenres, SongOrders, Album, JsonViewsLikeableItem.PresentLikeableItem  {}
    public interface MinimalSong extends JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllSongWithoutId extends Duration, Artist, MusicalGenres, JsonViewsSongOrder.StorageSongOrder, Album, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalSongWithoutId extends Duration, JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Duration  {}
    public interface Artist  {}
    public interface MusicalGenres  {}
    public interface SongOrders  {}
    public interface Album {}
}