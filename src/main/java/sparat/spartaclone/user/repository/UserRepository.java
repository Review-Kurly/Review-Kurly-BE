package sparat.spartaclone.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparat.spartaclone.common.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
