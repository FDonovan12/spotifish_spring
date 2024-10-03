package fr.donovan.spotifish.json_view;

public class JsonViewsSongStorage {
    
    public interface AllSongStorage extends Songs, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalSongStorage extends JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllSongStorageWithoutId extends Songs, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalSongStorageWithoutId extends JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Songs  {}
}