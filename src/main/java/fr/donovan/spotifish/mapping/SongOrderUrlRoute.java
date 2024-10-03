package fr.donovan.spotifish.mapping;

public interface SongOrderUrlRoute {

    String URL_SONGORDER = "/songorder";
    String URL_SONGORDER_NEW = URL_SONGORDER + "/new";
    String URL_SONGORDER_EDIT = URL_SONGORDER + "/edit";
    String URL_SONGORDER_DELETE = URL_SONGORDER + "/delete";

    String URL_ADMIN_SONGORDER = "/admin" + URL_SONGORDER;
    String URL_ADMIN_SONGORDER_NEW = URL_ADMIN_SONGORDER + "/new";
    String URL_ADMIN_SONGORDER_EDIT = URL_ADMIN_SONGORDER + "/edit";
    String URL_ADMIN_SONGORDER_DELETE = URL_ADMIN_SONGORDER + "/delete";
}