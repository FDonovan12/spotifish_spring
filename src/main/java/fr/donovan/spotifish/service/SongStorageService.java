package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.SongStorage;
import fr.donovan.spotifish.repository.SongStorageRepository;
import fr.donovan.spotifish.dto.SongStorageDTO;
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
public class SongStorageService  {

    private final SongStorageRepository songStorageRepository;

    public List<SongStorage> findAll() {
        return this.songStorageRepository.findAll();
    }

    public SongStorage getObjectById(String id) {
        Optional<SongStorage> optionalSongStorage = songStorageRepository.findById(id);
        return optionalSongStorage.orElseThrow(() -> new NotFoundSpotifishException("SongStorageService - getObjectById("+id+")", "SongStorage", id));
    }

    public Boolean delete(String id) {
        songStorageRepository.deleteById(id);
        return true;
    }

    public SongStorage persist(SongStorageDTO songStorageDTO) {
        return persist(songStorageDTO, null);
    }

    public SongStorage persist(SongStorageDTO songStorageDTO, String id) {
        SongStorage songStorage = new SongStorage();
        if (id != null) {
            songStorage = getObjectById(id);
        }
        songStorage = getObjectFromDTO(songStorageDTO, songStorage);
        songStorage.setCreatedAt(LocalDate.now());
        songStorage.setSlug("init slug before pre insert/update");
        return songStorageRepository.saveAndFlush(songStorage);
    }

    public SongStorageDTO getDTOById(String id) {
        SongStorage songStorage = getObjectById(id);
        return getDTOFromObject(songStorage);
    }

    public SongStorageDTO getDTOFromObject(SongStorage songStorage) {
        SongStorageDTO songStorageDTO = new SongStorageDTO();
        songStorageDTO.setName(songStorage.getName());
        songStorageDTO.setDescription(songStorage.getDescription());
        songStorageDTO.setImage(songStorage.getImage());
        return songStorageDTO;
    }
    public SongStorage getObjectFromDTO(SongStorageDTO songStorageDTO) {
        return getObjectFromDTO(songStorageDTO, new SongStorage());
    }
    public SongStorage getObjectFromDTO(SongStorageDTO songStorageDTO, SongStorage songStorage) {
        songStorage.setName(songStorageDTO.getName());
        songStorage.setDescription(songStorageDTO.getDescription());
        songStorage.setImage(songStorageDTO.getImage());
        return songStorage;
    }


}