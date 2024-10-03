package fr.donovan.spotifish.json_view;
import fr.donovan.spotifish.json_view.JsonViewsUser.*;
import fr.donovan.spotifish.json_view.JsonViewsLikeableItem.*;
import fr.donovan.spotifish.json_view.JsonViewsSong.*;
import fr.donovan.spotifish.json_view.JsonViewsArtist.*;
import fr.donovan.spotifish.json_view.JsonViewsMusicalGenre.*;
import fr.donovan.spotifish.json_view.JsonViewsSongOrder.*;
import fr.donovan.spotifish.json_view.JsonViewsSongStorage.*;
import fr.donovan.spotifish.json_view.JsonViewsAlbum.*;
import fr.donovan.spotifish.json_view.JsonViewsPlaylist.*;

public class JsonViews {
    public interface AllJsonViews {}

    public interface UserListJsonViews extends AllJsonViews, MinimalUser, MinimalPlaylist, MinimalLikeableItem {}
    public interface UserShowJsonViews extends AllJsonViews, AllUser, MinimalPlaylist, MinimalLikeableItem {}

    public interface LikeableItemListJsonViews extends AllJsonViews, MinimalLikeableItem {}
    public interface LikeableItemShowJsonViews extends AllJsonViews, AllLikeableItem {}

    public interface SongListJsonViews extends AllJsonViews, MinimalSong, MinimalArtist, MinimalMusicalGenre, StorageSongOrder {}
    public interface SongShowJsonViews extends AllJsonViews, AllSong, StorageSongOrder, MinimalArtist, MinimalMusicalGenre {}

    public interface ArtistListJsonViews extends AllJsonViews, MinimalArtist, MinimalSong, MinimalAlbum {}
    public interface ArtistShowJsonViews extends AllJsonViews, AllArtist, MinimalSong, MinimalAlbum {}

    public interface MusicalGenreListJsonViews extends AllJsonViews, MinimalMusicalGenre {}
    public interface MusicalGenreShowJsonViews extends AllJsonViews, AllMusicalGenre {}

    public interface SongOrderListJsonViews extends AllJsonViews, MinimalSongOrder, MinimalSongStorage, MinimalSong {}
    public interface SongOrderShowJsonViews extends AllJsonViews, AllSongOrder, MinimalSongStorage, MinimalSong {}

    public interface SongStorageListJsonViews extends AllJsonViews, MinimalSongStorage, MinimalSongOrder {}
    public interface SongStorageShowJsonViews extends AllJsonViews, AllSongStorage, SongSongOrder, MinimalSong {}

    public interface AlbumListJsonViews extends AllJsonViews, MinimalAlbum, MinimalArtist {}
    public interface AlbumShowJsonViews extends AllJsonViews, AllAlbum, MinimalArtist {}

    public interface PlaylistListJsonViews extends AllJsonViews, MinimalPlaylist, MinimalUser {}
    public interface PlaylistShowJsonViews extends AllJsonViews, AllPlaylist, MinimalUser {}
}