package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.SongOrder;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.SongOrderDTO;
import fr.donovan.spotifish.service.SongOrderService;
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
public class SongOrderControllerApi {
    
    private SongOrderService songOrderService;

    @GetMapping(path = UrlRoute.URL_SONGORDER)
    @JsonView(JsonViews.SongOrderListJsonViews.class)
    public CustomResponse<List<SongOrder>> list() {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongOrderControllerApi - list()", "SongOrder", this.songOrderService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_SONGORDER + "/{slug}")
    @JsonView(JsonViews.SongOrderShowJsonViews.class)
    public CustomResponse<SongOrder> show(@PathVariable String slug) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongOrderControllerApi - show("+slug+")", "SongOrder", this.songOrderService.getObjectBySlug(slug));
    }
    
    @PostMapping(path = UrlRoute.URL_SONGORDER_NEW)
    @JsonView(JsonViews.SongOrderShowJsonViews.class)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<SongOrder> create(@Valid @RequestBody SongOrderDTO songOrderDTO) {
        return new CustomResponse<>(HttpStatus.CREATED.value(), "SongOrderControllerApi - create()", "SongOrder", songOrderService.persist(songOrderDTO));
    }
    
    @PutMapping(path = UrlRoute.URL_SONGORDER_EDIT + "/{id}")
    @JsonView(JsonViews.SongOrderShowJsonViews.class)
    public CustomResponse<SongOrder> update(@Valid @RequestBody SongOrderDTO songOrderDTO, @PathVariable Long id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongOrderControllerApi - update("+id+")", "SongOrder", songOrderService.persist(songOrderDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_SONGORDER_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable Long id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "SongOrderControllerApi - delete("+id+")", "SongOrder", songOrderService.delete(id));
    }
}