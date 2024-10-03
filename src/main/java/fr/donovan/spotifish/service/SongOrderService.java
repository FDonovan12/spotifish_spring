package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.SongOrder;
import fr.donovan.spotifish.repository.SongOrderRepository;
import fr.donovan.spotifish.dto.SongOrderDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class SongOrderService  {

    private final SongOrderRepository songOrderRepository;
    private final SongStorageService songStorageService;
    private final SongService songService;

    public List<SongOrder> findAll() {
        return this.songOrderRepository.findAll();
    }

    public SongOrder getObjectById(Long id) {
        Optional<SongOrder> optionalSongOrder = songOrderRepository.findById(id);
        return optionalSongOrder.orElseThrow(() -> new NotFoundSpotifishException("SongOrderService - getObjectById("+id+")", "SongOrder", id));
    }
    public SongOrder getObjectBySlug(String slug) {
        Optional<SongOrder> optionalSongOrder = songOrderRepository.findBySlug(slug);
        return optionalSongOrder.orElseThrow(() -> new NotFoundSpotifishException("SongOrderService - getObjectBySlug("+slug+")", "SongOrder", slug));
    }

    public Boolean delete(Long id) {
        songOrderRepository.deleteById(id);
        return true;
    }

    public SongOrder persist(SongOrderDTO songOrderDTO) {
        return persist(songOrderDTO, null);
    }

    public SongOrder persist(SongOrderDTO songOrderDTO, Long id) {
        SongOrder songOrder = new SongOrder();
        if (id != null) {
            songOrder = getObjectById(id);
        }
        songOrder = getObjectFromDTO(songOrderDTO, songOrder);
        songOrder.setSlug("init slug before pre insert/update");
//        int count = songStorageService.getObjectById(songOrderDTO.getSongStorageId()).getSongs().size();
        int count = 0;
        songOrder.setPosition(count + 1);
        return songOrderRepository.saveAndFlush(songOrder);
    }

    public SongOrderDTO getDTOById(Long id) {
        SongOrder songOrder = getObjectById(id);
        return getDTOFromObject(songOrder);
    }

    public SongOrderDTO getDTOFromObject(SongOrder songOrder) {
        SongOrderDTO songOrderDTO = new SongOrderDTO();
        songOrderDTO.setPosition(songOrder.getPosition());
        songOrderDTO.setSongStorageId(songOrder.getSongStorage().getUuid());
        songOrderDTO.setSongId(songOrder.getSong().getUuid());
        return songOrderDTO;
    }
    public SongOrder getObjectFromDTO(SongOrderDTO songOrderDTO) {
        return getObjectFromDTO(songOrderDTO, new SongOrder());
    }
    public SongOrder getObjectFromDTO(SongOrderDTO songOrderDTO, SongOrder songOrder) {
        songOrder.setPosition(songOrderDTO.getPosition());
        songOrder.setSongStorage(songStorageService.getObjectById(songOrderDTO.getSongStorageId()));
        songOrder.setSong(songService.getObjectById(songOrderDTO.getSongId()));
        return songOrder;
    }


}