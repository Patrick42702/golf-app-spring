package dev.golf_app.controllers;

import dev.golf_app.models.Users;
import dev.golf_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody Users user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().body("Email already registered.");
    }

    // Encrypt the password before saving
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    return ResponseEntity.ok(userRepository.save(user));
  }

  @GetMapping
  public Iterable<Users> getAllUsers() {
    return userRepository.findAll();
  }


}
