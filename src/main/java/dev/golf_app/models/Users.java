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
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  private String password;
}
