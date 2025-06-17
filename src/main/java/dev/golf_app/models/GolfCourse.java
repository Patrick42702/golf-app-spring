package dev.golf_app.models;

import jakarta.persistence.*;

import javax.naming.Name;
import java.util.List;

@Entity
@Table(name = "golf_courses")
public class GolfCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String zip;

    @OneToMany(mappedBy = "golfCourse", cascade = CascadeType.ALL)
    private List<Round> rounds;
  }
