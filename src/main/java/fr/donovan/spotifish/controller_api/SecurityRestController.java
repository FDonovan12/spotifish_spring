package fr.donovan.spotifish.controller_api;

import com.fasterxml.jackson.annotation.JsonView;
import fr.donovan.spotifish.custom_response.CustomResponse;
import fr.donovan.spotifish.custom_response.JwtTokenResponse;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.dto.UserLoginDTO;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.json_view.JsonViews;
import fr.donovan.spotifish.mapping.UrlRoute;
import fr.donovan.spotifish.security.JwtAuthenticationService;
import fr.donovan.spotifish.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class SecurityRestController {

    private UserService userService;

    private JwtAuthenticationService jwtAuthenticationService;

    @PostMapping(path = UrlRoute.URL_REGISTER)
    @JsonView(JsonViews.UserShowJsonViews.class)
    User create(@Valid @RequestBody UserDTO userDTO) {
        return userService.persist(userDTO);
    }

    @GetMapping(path = UrlRoute.URL_ACCOUNT_ACTIVATION + "/{code}")
    @JsonView(JsonViews.UserShowJsonViews.class)
    public CustomResponse<User> accountActivation(@PathVariable String code) {
        return new CustomResponse<>(HttpStatus.OK.value(), "UserControllerApi - accountActivation("+code+")", "User", this.userService.accountActivation(code));
    }

    @PostMapping(path = UrlRoute.URL_LOGIN)
    ResponseEntity<JwtTokenResponse> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return jwtAuthenticationService.authenticate(userLoginDTO);
    }

}