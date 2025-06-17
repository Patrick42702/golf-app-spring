package dev.golf_app.controllers;

import dev.golf_app.repository.UserRepository;
import dev.golf_app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtil jwtUtil;

  public record LoginRequest(String email, String password) {}
  public record JwtResponse(String token) {}

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest login) {
    var userOpt = userRepository.findByEmail(login.email());

    if (userOpt.isEmpty() || !passwordEncoder.matches(login.password(), userOpt.get().getPassword())) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    String token = jwtUtil.generateToken(login.email());
    return ResponseEntity.ok(new JwtResponse(token));
  }
}
