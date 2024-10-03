package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, String>, EntitySlugRepositoryInterface<Song> {


//    OR s.album.name IS LIKE %:search% OR s.artist.name IS LIKE %:search% OR s.musicalGenres.name IS LIKE %:search%

    @Query("SELECT s FROM Song s WHERE s.name = ?1")
    List<Song> leTrucQueKevinVeut(String search);

//    @Query("SELECT s FROM Song s WHERE s.name IS LIKE ?1")
//    List<Song> leTrucQueKevinVeut2(String search);
    List<Song> findByNameContainingOrSongOrdersSongStorageNameContainingOrArtistNameContainingOrMusicalGenresNameContaining(String name, String songStorage, String artist, String musicalGenre);

    @Query("SELECT e FROM Song AS e ORDER BY RAND() LIMIT 1")
    Song findRandom();
}