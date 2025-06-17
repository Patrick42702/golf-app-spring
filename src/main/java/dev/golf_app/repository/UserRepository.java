package dev.golf_app.repository;

import dev.golf_app.models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Integer> {
  Optional<Users> findByEmail(String email);
}
