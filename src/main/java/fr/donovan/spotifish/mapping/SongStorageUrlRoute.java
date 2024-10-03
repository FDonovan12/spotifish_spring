package fr.donovan.spotifish.mapping;

public interface SongStorageUrlRoute {

    String URL_SONGSTORAGE = "/songstorage";
    String URL_SONGSTORAGE_NEW = URL_SONGSTORAGE + "/new";
    String URL_SONGSTORAGE_EDIT = URL_SONGSTORAGE + "/edit";
    String URL_SONGSTORAGE_DELETE = URL_SONGSTORAGE + "/delete";

    String URL_ADMIN_SONGSTORAGE = "/admin" + URL_SONGSTORAGE;
    String URL_ADMIN_SONGSTORAGE_NEW = URL_ADMIN_SONGSTORAGE + "/new";
    String URL_ADMIN_SONGSTORAGE_EDIT = URL_ADMIN_SONGSTORAGE + "/edit";
    String URL_ADMIN_SONGSTORAGE_DELETE = URL_ADMIN_SONGSTORAGE + "/delete";
}