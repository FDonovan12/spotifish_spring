package fr.donovan.spotifish.json_view;

public class JsonViewsMusicalGenre {
    
    public interface AllMusicalGenre extends JsonViewsLikeableItem.AllLikeableItem {}
    public interface MinimalMusicalGenre extends JsonViewsLikeableItem.MinimalLikeableItem {}

    public interface AllMusicalGenreWithoutId extends JsonViewsLikeableItem.AllLikeableItemWithoutId {}
    public interface MinimalMusicalGenreWithoutId extends JsonViewsLikeableItem.MinimalLikeableItemWithoutId {}

}