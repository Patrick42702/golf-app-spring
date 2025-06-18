package dev.golf_app.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "rounds")
@Getter
@Setter
public class Round {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Temporal(TemporalType.DATE)
  private Date date;

  private Integer holes; // # of holes played in the round
  private Integer totalPar; // the total par for the round being played

  @ManyToOne
  @JoinColumn(name = "golf_course_id", nullable = false)
  private GolfCourse golfCourse;

  @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Score> scores;

}
