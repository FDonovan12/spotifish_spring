package fr.donovan.spotifish.json_view;

public class JsonViewsUser {
    
    public interface AllUser extends Uuid, Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, Playlists, LikeableItems, Slug {}
    public interface MinimalUser extends Uuid, Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, Slug {}

    public interface AllUserWithoutId extends Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, Playlists, LikeableItems, Slug {}
    public interface MinimalUserWithoutId extends Email, Password, Username, FirstName, LastName, BirthAt, CreatedAt, ActivationCode, Slug {}

    public interface Uuid  {}
    public interface Email  {}
    public interface Password  {}
    public interface Username  {}
    public interface FirstName  {}
    public interface LastName  {}
    public interface BirthAt  {}
    public interface CreatedAt  {}
    public interface ActivationCode  {}
    public interface Playlists  {}
    public interface LikeableItems  {}
    public interface Slug  {}
}