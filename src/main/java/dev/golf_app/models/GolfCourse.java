package dev.golf_app.models;

import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Entity
@Table(name = "golf_courses")
@Getter @Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GolfCourse {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME")
  private String name;
  @Column(name = "ADDRESS")
  private String address;
  @Column(name = "ZIP")
  private String zip;

  @OneToMany(mappedBy = "golfCourse", cascade = CascadeType.ALL)
  @Column(name = "ROUNDS")
  private List<Round> rounds;
}
