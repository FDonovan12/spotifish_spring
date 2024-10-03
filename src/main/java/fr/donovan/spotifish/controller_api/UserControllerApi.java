package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.service.UserService;
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
public class UserControllerApi {
    
    private UserService userService;

    @GetMapping(path = UrlRoute.URL_ADMIN_USER)
    @JsonView(JsonViews.UserListJsonViews.class)
    public CustomResponse<List<User>> list() {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - list()", "User", this.userService.findAll());
    }

    @GetMapping(path = UrlRoute.URL_USER + "/{slug}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> show(@PathVariable String slug) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - show("+slug+")", "User", this.userService.getObjectBySlug(slug));
    }

    @GetMapping(path = UrlRoute.URL_USER + "/{slugUser}/like/{slugLikeableItem}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> like(@PathVariable String slugUser, @PathVariable String slugLikeableItem) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - like("+slugUser+", "+slugLikeableItem+")", "User", this.userService.likeItem(slugUser, slugLikeableItem));
    }
    
    @PutMapping(path = UrlRoute.URL_ADMIN_USER_EDIT + "/{id}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> update(@Valid @RequestBody UserDTO userDTO, @PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - update("+id+")", "User", userService.persist(userDTO, id));
    }
    
    @DeleteMapping(path = UrlRoute.URL_ADMIN_USER_DELETE + "/{id}")
    public CustomResponse<Boolean> delete(@PathVariable String id) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - delete("+id+")", "User", userService.delete(id));
    }
}