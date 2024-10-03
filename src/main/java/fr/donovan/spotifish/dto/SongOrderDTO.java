package fr.donovan.spotifish.dto;

import fr.donovan.spotifish.entity.*;
import jakarta.validation.constraints.*;
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
public class SongOrderDTO {
    
    @NotNull(message = "The position can't be null")
    private Integer position;
    
    @NotNull(message = "The songStorage can't be null")
    private String songStorageId;
    
    @NotNull(message = "The song can't be null")
    private String songId;
}