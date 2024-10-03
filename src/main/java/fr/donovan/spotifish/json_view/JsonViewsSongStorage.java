package fr.donovan.spotifish.json_view;

public class JsonViewsSongStorage {
    
    public interface AllSongStorage extends SongOrders, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalSongStorage extends JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllSongStorageWithoutId extends SongOrders, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalSongStorageWithoutId extends JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface SongOrders  {}
}