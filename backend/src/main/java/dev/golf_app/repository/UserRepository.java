package dev.golf_app.repository;

import dev.golf_app.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {
  Optional<Users> findByEmail(String email);
}
