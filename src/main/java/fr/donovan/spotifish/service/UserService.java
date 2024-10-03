package fr.donovan.spotifish.service;

import fr.donovan.spotifish.entity.User;
import fr.donovan.spotifish.exception.AlreadyActiveException;
import fr.donovan.spotifish.exception.ExpiredCodeException;
import fr.donovan.spotifish.repository.UserRepository;
import fr.donovan.spotifish.dto.UserDTO;
import fr.donovan.spotifish.exception.NotFoundSpotifishException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User getObjectById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new NotFoundSpotifishException("UserService - getObjectById("+id+")", "User", id));
    }
    public User getObjectBySlug(String slug) {
        Optional<User> optionalUser = userRepository.findBySlug(slug);
        return optionalUser.orElseThrow(() -> new NotFoundSpotifishException("UserService - getObjectBySlug("+slug+")", "User", slug));
    }

    public Boolean delete(String id) {
        userRepository.deleteById(id);
        return true;
    }

    public User persist(UserDTO userDTO) {
        return persist(userDTO, null);
    }

    public User persist(UserDTO userDTO, String id) {
        User user = new User();
        if (id != null) {
            user = getObjectById(id);
        }
        user = getObjectFromDTO(userDTO, user);
        user.setCreatedAt(LocalDateTime.now());
        user.setEndOfActivation(LocalDateTime.now().plusHours(1));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setRoles("ROLE_USER");
        user.setSlug("init slug before pre insert/update sans ca marche pas");
        return userRepository.saveAndFlush(user);
    }

    public UserDTO getDTOById(String id) {
        User user = getObjectById(id);
        return getDTOFromObject(user);
    }

    public UserDTO getDTOFromObject(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthAt(user.getBirthAt());
        return userDTO;
    }
    public User getObjectFromDTO(UserDTO userDTO) {
        return getObjectFromDTO(userDTO, new User());
    }
    public User getObjectFromDTO(UserDTO userDTO, User user) {
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthAt(userDTO.getBirthAt());
        return user;
    }

    public boolean activatedAccount(String activationCode) {
        Optional<User> optionalUser = userRepository.findByActivationCode(activationCode);
        if (optionalUser.isEmpty()) {
            throw new AlreadyActiveException("User", "activationCode", activationCode);
        }
        User user = optionalUser.get();
        if (user.getEndOfActivation().isBefore(LocalDateTime.now())) {
            throw new ExpiredCodeException("User", "activationCode", activationCode);
        }
        user.setActivationCode(null);
        return true;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        fr.donovan.spotifish.entity.User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Les cochons sont dans la baie"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                userGrantedAuthority(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> userGrantedAuthority(String jsonRoles) {
        Collection<? extends GrantedAuthority> grantedAuthorities =  Stream.of(jsonRoles.split(",")).map(SimpleGrantedAuthority::new).toList();
        System.out.println("grantedAuthorities = " + grantedAuthorities);
        return grantedAuthorities;
    }
}