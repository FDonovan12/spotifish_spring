package fr.donovan.spotifish.configuration;

import fr.donovan.spotifish.dto.*;
import fr.donovan.spotifish.entity.*;
import fr.donovan.spotifish.service.*;
import fr.donovan.spotifish.repository.*;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@Configuration
@AllArgsConstructor
public class InitDataLoader implements CommandLineRunner {

    private static final Faker faker = new Faker(Locale.FRANCE);

    private static final long NB_USER = 50;
    private static final long NB_SONG = 500;
    private static final long NB_SONGORDER = 1000;
    private static final long NB_ARTIST = 50;
    private static final long NB_ALBUM = 50;
    private static final long NB_PLAYLIST = 10;
    private static final long NB_MUSICALGENRE = 50;

    private final UserService userService;
    private final UserRepository userRepository;

    private final SongService songService;
    private final SongRepository songRepository;

    private final SongOrderService songOrderService;
    private final SongOrderRepository songOrderRepository;

    private final SongStorageRepository songStorageRepository;

    private final ArtistService artistService;
    private final ArtistRepository artistRepository;

    private final AlbumService albumService;
    private final AlbumRepository albumRepository;

    private final PlaylistService playlistService;
    private final PlaylistRepository playlistRepository;

    private final MusicalGenreService musicalGenreService;
    private final MusicalGenreRepository musicalGenreRepository;

    @Override
    public void run(String... args) {

        createArtist();
        createMusicalGenre();
        createUser();
        createAlbum();
        createSong();
        createPlaylist();
        createSongOrder();
    System.out.println(" end of init !!!");
    }

    private void createUser() {
        System.out.println("InitDataLoader.createUser");
        long countInsert = NB_USER - userRepository.count();
        for (int i = 0; i < countInsert; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(faker.internet().emailAddress());
            userDTO.setPassword("12345");
            userDTO.setUsername(faker.internet().username());
            userDTO.setFirstName(faker.name().firstName());
            userDTO.setLastName(faker.name().lastName());
            userDTO.setBirthAt(faker.timeAndDate().birthday(18, 70));
            userService.persist(userDTO);
        }
        userRepository.flush();
    }
    private void createSong() {
        System.out.println("InitDataLoader.createSong");
        long countInsert = NB_SONG - songRepository.count();
        for (int i = 0; i < countInsert; i++) {
            SongDTO songDTO = new SongDTO();
            songDTO.setDuration(faker.number().numberBetween(150, 300));
            songDTO.setName(faker.animal().name()+" "+ faker.color().name());
            songDTO.setDescription(faker.backToTheFuture().quote());
            songDTO.setImage(faker.internet().url());
            songDTO.setArtistId(artistRepository.findRandom().getUuid());
            String musicalGenre1 = musicalGenreRepository.findRandom().getUuid();
            String musicalGenre2 = musicalGenreRepository.findRandom().getUuid();
            List<String> list = List.of(musicalGenre1, musicalGenre2);
            System.out.println("musicalGenre1 = " + musicalGenre1);
            System.out.println("musicalGenre2 = " + musicalGenre2);
            System.out.println("list = " + list);
            songDTO.setMusicalGenresId(list);
            songService.persist(songDTO);
        }
        songRepository.flush();
    }
    private void createSongOrder() {
        System.out.println("InitDataLoader.createSongOrder");
        long countInsert = NB_SONGORDER - songOrderRepository.count();
        for (int i = 0; i < countInsert; i++) {
            SongOrderDTO songOrderDTO = new SongOrderDTO();
            songOrderDTO.setSongStorageId(songStorageRepository.findRandom().getUuid());
            songOrderDTO.setSongId(songRepository.findRandom().getUuid());
            songOrderService.persist(songOrderDTO);
        }
        songOrderRepository.flush();
    }
    private void createArtist() {
        System.out.println("InitDataLoader.createArtist");
        long countInsert = NB_ARTIST - artistRepository.count();
        for (int i = 0; i < countInsert; i++) {
            ArtistDTO artistDTO = new ArtistDTO();
            artistDTO.setName(faker.artist().name());
            artistDTO.setDescription(faker.kaamelott().quote());
            artistDTO.setImage(faker.internet().url());
            artistService.persist(artistDTO);
        }
        artistRepository.flush();
    }
    private void createAlbum() {
        System.out.println("InitDataLoader.createAlbum");
        long countInsert = NB_ALBUM - albumRepository.count();
        for (int i = 0; i < countInsert; i++) {
            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setArtistId(artistRepository.findRandom().getUuid());
            albumDTO.setName(faker.book().title());
            albumDTO.setDescription(faker.yoda().quote());
            albumDTO.setImage(faker.internet().url());
            albumService.persist(albumDTO);
        }
        albumRepository.flush();
    }
    private void createPlaylist() {
        System.out.println("InitDataLoader.createPlaylist");
        long countInsert = NB_PLAYLIST - playlistRepository.count();
        for (int i = 0; i < countInsert; i++) {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setName(faker.animal().name());
            playlistDTO.setDescription(faker.spongebob().quotes());
            playlistDTO.setImage(faker.internet().url());
            playlistDTO.setOwnerId(userRepository.findRandom().getUuid());
            playlistService.persist(playlistDTO);
        }
        playlistRepository.flush();
    }
    private void createMusicalGenre() {
        System.out.println("InitDataLoader.createMusicalGenre");
        long countInsert = NB_MUSICALGENRE - musicalGenreRepository.count();
        for (int i = 0; i < countInsert; i++) {
            MusicalGenreDTO musicalGenreDTO = new MusicalGenreDTO();
            musicalGenreDTO.setName(faker.music().genre());
            musicalGenreDTO.setDescription(faker.chuckNorris().fact());
            musicalGenreDTO.setImage(faker.internet().url());
            musicalGenreService.persist(musicalGenreDTO);
        }
        musicalGenreRepository.flush();
    }
}