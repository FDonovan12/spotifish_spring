package fr.donovan.spotifish.json_view;

public class JsonViewsLikeableItem {
    
    public interface AllLikeableItem extends Uuid, Name, Description, Image, CreatedAt, Slug {}
    public interface MinimalLikeableItem extends Name, Description, Image, Slug {}
    public interface PresentLikeableItem extends Name, Description, Slug {}

    public interface AllLikeableItemWithoutId extends Name, Description, Image, CreatedAt, Slug {}
    public interface MinimalLikeableItemWithoutId extends Name, Description, Image, CreatedAt, Slug {}

    public interface Uuid  {}
    public interface Name  {}
    public interface Description  {}
    public interface Image  {}
    public interface CreatedAt  {}
    public interface Slug  {}
}