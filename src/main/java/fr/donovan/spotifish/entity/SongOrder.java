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
public class SongOrder implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewsSongOrder.Id.class)
    private Long id;

    @JsonView(JsonViewsSongOrder.Order.class)
    @Column(nullable = false)
    private Integer position;

    @ManyToOne
    @JsonView(JsonViewsSongOrder.SongStorage.class)
    @JoinColumn(nullable = false)
    private SongStorage songStorage;

    @ManyToOne
    @JsonView(JsonViewsSongOrder.Song.class)
    @JoinColumn(nullable = false)
    private Song song;

    @JsonView(JsonViewsSongOrder.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getId();
    }
}