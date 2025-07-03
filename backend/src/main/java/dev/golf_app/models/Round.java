package dev.golf_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


@Entity
@Table(name = "rounds")
@Data
public class Round {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "DATE")
  private Instant date;

  @Column(name = "HOLES")
  private Integer holes; // # of holes played in the round
  @Column(name = "TOTALPAR")
  private Integer totalPar; // the total par for the round being played

  @ManyToOne
  @JoinColumn(name = "golf_course_id")
  @JsonIgnore
  private GolfCourse golfCourse;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private Users user;
}
