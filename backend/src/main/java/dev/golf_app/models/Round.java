package dev.golf_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Table(name = "rounds")
@Getter @Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
  private GolfCourse golfCourse;

//  @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//  @Column(name = "SCORE")
//  private List<Score> scores;

}
