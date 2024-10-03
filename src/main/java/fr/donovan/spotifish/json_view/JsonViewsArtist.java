package fr.donovan.spotifish.json_view;

public class JsonViewsArtist {
    
    public interface AllArtist extends Songs, Albums, JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalArtist extends JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllArtistWithoutId extends Songs, Albums, JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalArtistWithoutId extends JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

    public interface Songs  {}
    public interface Albums  {}
}