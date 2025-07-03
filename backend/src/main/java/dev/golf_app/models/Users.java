package dev.golf_app.models;

import dev.golf_app.validation.Password;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
@Data
public class Users {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  @Password
  @Valid
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Round> rounds;
}
