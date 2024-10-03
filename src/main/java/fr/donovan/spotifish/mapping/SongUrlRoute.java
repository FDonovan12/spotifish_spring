package fr.donovan.spotifish.mapping;

public interface SongUrlRoute {

    String URL_SONG = "/song";
    String URL_SONG_NEW = URL_SONG + "/new";
    String URL_LE_TRUC_QUE_KEVIN_VEUT = URL_SONG + "/le-truc-que-kevin-veut";
    String URL_LE_TRUC_QUE_DONOVAN_VEUT = URL_SONG + "/le-truc-que-donovan-veut";
    String URL_SONG_EDIT = URL_SONG + "/edit";
    String URL_SONG_DELETE = URL_SONG + "/delete";

    String URL_ADMIN_SONG = "/admin" + URL_SONG;
    String URL_ADMIN_SONG_NEW = URL_ADMIN_SONG + "/new";
    String URL_ADMIN_SONG_EDIT = URL_ADMIN_SONG + "/edit";
    String URL_ADMIN_SONG_DELETE = URL_ADMIN_SONG + "/delete";
}