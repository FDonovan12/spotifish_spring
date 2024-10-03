package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.dto.LeTrucQueDonovanVeut;
import fr.donovan.spotifish.entity.Song;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.SongDTO;
import fr.donovan.spotifish.service.*;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(UrlRoute.URL_API)
public class SongControllerApi {
    
    private SongService songService;

    @GetMapping(path = UrlRoute.URL_SONG)
    @JsonView(JsonViews.SongListJsonViews.class)
    public CustomResponse<List<Song>> list() {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongControllerApi - list()", "Song", songService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SONG + "/{slug}")
    @JsonView(JsonViews.SongShowJsonViews.class)
    public CustomResponse<Song> show(@PathVariable String slug) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongControllerApi - show("+slug+")", "Song", songService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SONG_NEW)
    @JsonView(JsonViews.SongShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Song> create(@Valid @RequestBody SongDTO songDTO) {
        return new CustomResponse<>(HttpStatus.CREATED.value(), "SongControllerApi - create()", "Song", songService.persist(songDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SONG_EDIT + "/{id}")
    @JsonView(JsonViews.SongShowJsonViews.class)
    public CustomResponse<Song> update(@Valid @RequestBody SongDTO songDTO, @PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongControllerApi - update("+id+")", "Song", songService.persist(songDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONG_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongControllerApi - delete("+id+")", "Song", songService.delete(id));
    }

    @GetMapping(path = UrlRoute.URL_SONG+"/leTrucQueKevinVeut/{search}")
    @JsonView(JsonViews.SongListJsonViews.class)
    public CustomResponse<List<Song>> leTrucQueKevinVeut(@PathVariable String search) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongControllerApi - leTrucQueKevinVeut()", "Song", songService.leTrucQueKevinVeut(search));
    }

    @PostMapping(path = UrlRoute.URL_SONG+"/leTrucQueDonovanVeut")
    @JsonView(JsonViews.SongListJsonViews.class)
    public CustomResponse<List<Song>> leTrucQueDonovanVeut(@RequestBody LeTrucQueDonovanVeut leTrucQueDonovanVeut) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongControllerApi - leTrucQueDonovanVeut()", "Song", songService.leTrucQueDonovanVeut(leTrucQueDonovanVeut));
    }
}