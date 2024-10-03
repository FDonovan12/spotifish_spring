package fr.donovan.spotifish.service;

import fr.donovan.spotifish.dto.LeTrucQueDonovanVeut;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.repository.SongRepository;
import fr.donovan.spotifish.dto.SongDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class SongService  {

    private final SongRepository songRepository;
    private final ArtistService artistService;
    private final AlbumService albumService;
    private final MusicalGenreService musicalGenreService;

    public List<Song> findAll() {
        return this.songRepository.findAll();
    }

    public Song getObjectById(String id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        return optionalSong.orElseThrow(() -> new NotFoundSpotifishException("SongService - getObjectById("+id+")", "Song", id));
    }
    public Song getObjectBySlug(String slug) {
        Optional<Song> optionalSong = songRepository.findBySlug(slug);
        System.out.println("optionalSong = " + optionalSong.get().getSongOrders());
        return optionalSong.orElseThrow(() -> new NotFoundSpotifishException("SongService - getObjectBySlug("+slug+")", "Song", slug));
    }

    public Boolean delete(String id) {
        songRepository.deleteById(id);
        return true;
    }

    public Song persist(SongDTO songDTO) {
        return persist(songDTO, null);
    }

    public Song persist(SongDTO songDTO, String id) {
        Song song = new Song();
        if (id != null) {
            song = getObjectById(id);
        }
        song = getObjectFromDTO(songDTO, song);
        song.setCreatedAt(LocalDate.now());
        song.setSlug("init slug before pre insert/update");
        return songRepository.saveAndFlush(song);
    }

    public SongDTO getDTOById(String id) {
        Song song = getObjectById(id);
        return getDTOFromObject(song);
    }

    public SongDTO getDTOFromObject(Song song) {
        SongDTO songDTO = new SongDTO();
        songDTO.setName(song.getName());
        songDTO.setDescription(song.getDescription());
        songDTO.setImage(song.getImage());
        songDTO.setDuration(song.getDuration());
        songDTO.setArtistId(song.getArtist().getUuid());
        return songDTO;
    }
    public Song getObjectFromDTO(SongDTO songDTO) {
        return getObjectFromDTO(songDTO, new Song());
    }
    public Song getObjectFromDTO(SongDTO songDTO, Song song) {
        song.setName(songDTO.getName());
        song.setDescription(songDTO.getDescription());
        song.setImage(songDTO.getImage());
        song.setDuration(songDTO.getDuration());
        song.setArtist(artistService.getObjectById(songDTO.getArtistId()));
        List<MusicalGenre> musicalGenres = songDTO.getMusicalGenresId().stream().map(musicalGenreService::getObjectById).toList();
        song.setMusicalGenres(musicalGenres);
        return song;
    }

    public List<Song> leTrucQueKevinVeut(String search) {
//        return this.songRepository.leTrucQueKevinVeut(search);
        return songRepository.findByNameContainingOrSongOrdersSongStorageNameContainingOrArtistNameContainingOrMusicalGenresNameContaining(search, search, search, search);
    }

    public List<Song> leTrucQueDonovanVeut(LeTrucQueDonovanVeut leTrucQueDonovanVeut) {
        List<Song> returnSongList =  this.songRepository.findAll();
        for (String artistId : leTrucQueDonovanVeut.getArtistIds()) {
            returnSongList = returnSongList.stream().filter(song -> Objects.equals(song.getArtist().getUuid(), artistId)).toList();
        }
        for (String musicalGenreId : leTrucQueDonovanVeut.getMusicalGenreIds()) {
            returnSongList = returnSongList.stream()
                    .filter(song -> song.getMusicalGenres().stream()
                            .map(MusicalGenre::getUuid).toList().contains(musicalGenreId)).toList();
        }
        for (String playlistId : leTrucQueDonovanVeut.getPlaylistIds()) {
            returnSongList = returnSongList.stream()
                    .filter(song -> song.getSongOrders().stream()
                            .map(SongOrder::getSongStorage)
                            .map(SongStorage::getUuid).toList().contains(playlistId)).toList();
        }
        return returnSongList;
    }


}