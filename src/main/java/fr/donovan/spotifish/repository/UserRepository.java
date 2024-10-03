package fr.donovan.spotifish.repository;

import fr.donovan.spotifish.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String>, EntitySlugRepositoryInterface<User> {

    @Query("SELECT e FROM User AS e ORDER BY RAND() LIMIT 1")
    User findRandom();

    Optional<User> findByActivationCode(String s);

    Optional<User> findByEmailAndActivationCodeIsNull(String email);
}