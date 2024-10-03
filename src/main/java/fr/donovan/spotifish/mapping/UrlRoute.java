package fr.donovan.spotifish.mapping;

public class UrlRoute implements UserUrlRoute, LikeableItemUrlRoute, SongUrlRoute, ArtistUrlRoute, MusicalGenreUrlRoute, SongOrderUrlRoute, SongStorageUrlRoute, AlbumUrlRoute, PlaylistUrlRoute{

    public static final String URL_API = "/api";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_ACCOUNT_ACTIVATION = "/account-activation";
    public static final String URL_REGISTER = "/register";
    public static final String URL_ADMIN = "/admin";

}