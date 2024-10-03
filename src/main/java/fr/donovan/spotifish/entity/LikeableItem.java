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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class LikeableItem implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonView(JsonViewsLikeableItem.Uuid.class)
    private String uuid;

    @JsonView(JsonViewsLikeableItem.Name.class)
    @Column(nullable = false)
    private String name;

    @JsonView(JsonViewsLikeableItem.Description.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @JsonView(JsonViewsLikeableItem.Image.class)
    private String image;

    @JsonView(JsonViewsLikeableItem.CreatedAt.class)
    @Column(nullable = false)
    private LocalDate createdAt;

    @JsonView(JsonViewsLikeableItem.Slug.class)
    @Column(nullable = false)
    private String slug;

    @Override
    public String getField() {
        return "" + getUuid();
    }
}