package fr.donovan.spotifish.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
public class LeTrucQueDonovanVeut {

    private List<String> playlistIds = new ArrayList<>();

    private List<String> artistIds = new ArrayList<>();

    private List<String> musicalGenreIds = new ArrayList<>();
}