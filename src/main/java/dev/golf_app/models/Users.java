package dev.golf_app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
@Getter
@Setter
public class Users {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String email;

  private String password;
}
