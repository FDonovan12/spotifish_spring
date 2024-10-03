package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.SongOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongOrderRepository extends JpaRepository<SongOrder, Long>, EntitySlugRepositoryInterface<SongOrder> {
@Query("SELECT e FROM SongOrder AS e ORDER BY RAND() LIMIT 1")
    SongOrder findRandom();
}