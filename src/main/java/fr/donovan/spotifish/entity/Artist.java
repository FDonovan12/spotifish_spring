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
@DiscriminatorValue("ARTIST")
public class Artist extends LikeableItem  implements SluggerInterface {

    @OneToMany(mappedBy = "artist")
    @JsonView(JsonViewsArtist.Songs.class)
    private List<Song> songs = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    @JsonView(JsonViewsArtist.Albums.class)
    private List<Album> albums = new ArrayList<>();

    @Override
    public String getField() {
        return "" + getUuid();
    }
}