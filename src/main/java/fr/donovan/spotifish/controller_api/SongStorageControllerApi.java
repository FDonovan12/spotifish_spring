//package fr.donovan.spotifish.controller_api;
//
//import com.fasterxml.jackson.annotation.JsonView;
//import fr.donovan.spotifish.entity.SongStorage;
//import fr.donovan.spotifish.custom_response.CustomResponse;
//import fr.donovan.spotifish.dto.SongStorageDTO;
//import fr.donovan.spotifish.service.SongStorageService;
//import fr.donovan.spotifish.json_view.JsonViews;
//import fr.donovan.spotifish.mapping.UrlRoute;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@AllArgsConstructor
//@RequestMapping(UrlRoute.URL_API)
//public class SongStorageControllerApi {
//
//    private SongStorageService songStorageService;
//
//    @GetMapping(path = UrlRoute.URL_SONGSTORAGE)
//    @JsonView(JsonViews.SongStorageListJsonViews.class)
//    public CustomResponse<List<SongStorage>> list() {
//        return new CustomResponse<>(HttpStatus.OK.value(), "SongStorageControllerApi - list()", "SongStorage", this.songStorageService.findAll());
//    }
//
//    @GetMapping(path = UrlRoute.URL_SONGSTORAGE + "/{id}")
//    @JsonView(JsonViews.SongStorageShowJsonViews.class)
//    public CustomResponse<SongStorage> show(@PathVariable String id) {
//        return new CustomResponse<>(HttpStatus.OK.value(), "SongStorageControllerApi - show("+id+")", "SongStorage", this.songStorageService.getObjectById(id));
//    }
//
//    @PostMapping(path = UrlRoute.URL_SONGSTORAGE_NEW)
//    @JsonView(JsonViews.SongStorageShowJsonViews.class)
//    @ResponseStatus(HttpStatus.CREATED)
//    public CustomResponse<SongStorage> create(@Valid @RequestBody SongStorageDTO songStorageDTO) {
//        return new CustomResponse<>(HttpStatus.CREATED.value(), "SongStorageControllerApi - create()", "SongStorage", songStorageService.persist(songStorageDTO));
//    }
//
//    @PutMapping(path = UrlRoute.URL_SONGSTORAGE_EDIT + "/{id}")
//    @JsonView(JsonViews.SongStorageShowJsonViews.class)
//    public CustomResponse<SongStorage> update(@Valid @RequestBody SongStorageDTO songStorageDTO, @PathVariable String id) {
//        return new CustomResponse<>(HttpStatus.OK.value(), "SongStorageControllerApi - update("+id+")", "SongStorage", songStorageService.persist(songStorageDTO, id));
//    }
//
//    @DeleteMapping(path = UrlRoute.URL_SONGSTORAGE_DELETE + "/{id}")
//    public CustomResponse<Boolean> delete(@PathVariable String id) {
//        return new CustomResponse<>(HttpStatus.OK.value(), "SongStorageControllerApi - delete("+id+")", "SongStorage", songStorageService.delete(id));
//    }
//}