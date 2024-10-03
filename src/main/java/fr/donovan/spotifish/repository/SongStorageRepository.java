package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.SongStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongStorageRepository extends JpaRepository<SongStorage, String>{
@Query("SELECT e FROM SongStorage AS e ORDER BY RAND() LIMIT 1")
    SongStorage findRandom();
}