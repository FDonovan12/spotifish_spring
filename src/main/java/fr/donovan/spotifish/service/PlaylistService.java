package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.Playlist;
import fr.donovan.spotifish.repository.PlaylistRepository;
import fr.donovan.spotifish.dto.PlaylistDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class PlaylistService  {

    private final PlaylistRepository playlistRepository;
    private final UserService userService;

    public List<Playlist> findAll() {
        return this.playlistRepository.findAll();
    }

    public Playlist getObjectById(String id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
        return optionalPlaylist.orElseThrow(() -> new NotFoundSpotifishException("PlaylistService - getObjectById("+id+")", "Playlist", id));
    }

    public Boolean delete(String id) {
        playlistRepository.deleteById(id);
        return true;
    }

    public Playlist persist(PlaylistDTO playlistDTO) {
        return persist(playlistDTO, null);
    }

    public Playlist persist(PlaylistDTO playlistDTO, String id) {
        Playlist playlist = new Playlist();
        if (id != null) {
            playlist = getObjectById(id);
        }
        playlist = getObjectFromDTO(playlistDTO, playlist);
        playlist.setCreatedAt(LocalDate.now());
        playlist.setSlug("init slug before pre insert/update");
        return playlistRepository.saveAndFlush(playlist);
    }

    public PlaylistDTO getDTOById(String id) {
        Playlist playlist = getObjectById(id);
        return getDTOFromObject(playlist);
    }

    public PlaylistDTO getDTOFromObject(Playlist playlist) {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName(playlist.getName());
        playlistDTO.setDescription(playlist.getDescription());
        playlistDTO.setImage(playlist.getImage());
        playlistDTO.setOwnerId(playlist.getOwner().getUuid());
        return playlistDTO;
    }
    public Playlist getObjectFromDTO(PlaylistDTO playlistDTO) {
        return getObjectFromDTO(playlistDTO, new Playlist());
    }
    public Playlist getObjectFromDTO(PlaylistDTO playlistDTO, Playlist playlist) {
        playlist.setName(playlistDTO.getName());
        playlist.setDescription(playlistDTO.getDescription());
        playlist.setImage(playlistDTO.getImage());
        playlist.setOwner(userService.getObjectById(playlistDTO.getOwnerId()));
        return playlist;
    }


}