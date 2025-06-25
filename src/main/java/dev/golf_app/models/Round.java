package dev.golf_app.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "rounds")
@Getter @Setter
@NoArgsConstructor
public class Round {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Temporal(TemporalType.DATE)
  @Column(name = "DATE")
  private Date date;

  @Column(name = "HOLES")
  private Integer holes; // # of holes played in the round
  @Column(name = "TOTALPAR")
  private Integer totalPar; // the total par for the round being played

  @ManyToOne
  @JoinColumn(name = "golf_course_id", nullable = false)
  private GolfCourse golfCourse;

  @OneToMany(mappedBy = "round", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @Column(name = "SCORE")
  private List<Score> scores;

}
