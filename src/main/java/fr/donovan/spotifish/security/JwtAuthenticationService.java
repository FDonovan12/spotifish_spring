package fr.donovan.spotifish.security;

import fr.donovan.spotifish.dto.UserLoginDTO;
import fr.donovan.spotifish.security.JwtService;
import fr.donovan.spotifish.custom_response.JwtTokenResponse;
import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JwtAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public ResponseEntity<JwtTokenResponse> authenticate(UserLoginDTO userLoginDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userLoginDTO.getUsername(),
                    userLoginDTO.getPassword()
                )
            );

            UserDetails user = userService.loadUserByUsername(authenticate.getName());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtTokenResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}