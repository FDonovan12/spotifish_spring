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
@DiscriminatorValue("SONG")
public class Song extends LikeableItem  implements SluggerInterface {

    @JsonView(JsonViewsSong.Duration.class)
    private Integer duration;

    @ManyToOne
    @JsonView(JsonViewsSong.Artist.class)
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "song_musicalGenre",
        joinColumns = @JoinColumn(name="song_id"),
        inverseJoinColumns = @JoinColumn(name="musicalGenre_id"))
    @JsonView(JsonViewsSong.MusicalGenres.class)
    private List<MusicalGenre> musicalGenres = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="song_uuid")
    @JsonView(JsonViewsSong.SongOrders.class)
    private List<SongOrder> songOrders = new ArrayList<>();

    @Override
    public String getField() {
        return "" + getUuid();
    }
}