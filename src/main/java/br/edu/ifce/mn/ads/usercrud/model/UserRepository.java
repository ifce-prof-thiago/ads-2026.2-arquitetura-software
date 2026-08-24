package br.edu.ifce.mn.ads.usercrud.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    @Query("SELECT count(u) > 0 FROM User u where u.id != :id AND u.username = :username")
    boolean existsByIdNotAndUsername(Long id, String username);
}
