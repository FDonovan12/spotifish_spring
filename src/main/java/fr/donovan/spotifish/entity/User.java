package fr.donovan.spotifish.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.json_view.*;
import fr.donovan.spotifish.entity.interfaces.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsUser.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsUser.Email.class)
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonView(JsonViewsUser.Username.class)
    @Column(nullable = false)
    private String username;

    @JsonView(JsonViewsUser.FirstName.class)
    @Column(nullable = false)
    private String firstName;

    @JsonView(JsonViewsUser.LastName.class)
    @Column(nullable = false)
    private String lastName;

    @JsonView(JsonViewsUser.BirthAt.class)
    @Column(nullable = false)
    private LocalDate birthAt;

    @JsonView(JsonViewsUser.CreatedAt.class)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(JsonViewsUser.ActivationCode.class)
    private String activationCode;

    @Column(nullable = false)
    private LocalDateTime endOfActivation;

    @OneToMany(mappedBy = "owner")
    @JsonView(JsonViewsUser.Playlists.class)
    private List<Playlist> playlists = new ArrayList<>();

    @ManyToMany
    @JsonView(JsonViewsUser.LikeableItems.class)
    private List<LikeableItem> likeableItems = new ArrayList<>();

    private String roles;

    @JsonView(JsonViewsUser.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getUuid();
    }

    public void addLike(LikeableItem likeableItem) {
        this.likeableItems.add(likeableItem);
    }
}