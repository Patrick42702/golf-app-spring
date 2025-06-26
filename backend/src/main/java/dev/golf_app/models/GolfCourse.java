package dev.golf_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "golf_courses")
@Getter @Setter // @ToString
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
  @Column(name = "LONGITUDE")
  private Double longitude;
  @Column(name = "LATITUDE")
  private Double latitude;
  @Column(name = "CLASSIFICATION")
  private String classification;
  @Column(name = "STATE_ABBR")
  private String state_abbr;
  @Column(name = "CITY")
  private String city;
  @Column(name = "PHONE")
  private String phone;

  @OneToMany(mappedBy = "golfCourse", cascade = CascadeType.ALL)
  @Column(name = "ROUNDS")
  private List<Round> rounds;

  public String toString(){
    return "\nGolf course id: " + id +
      "\nGolf course name: " + name +
      "\nGolf course address: " + address +
      "\nGolf course zip: " + zip +
      "\n------------\n";
  }
}
