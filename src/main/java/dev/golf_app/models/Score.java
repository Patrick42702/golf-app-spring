package dev.golf_app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "scores")
@Getter @Setter
@NoArgsConstructor
public class Score {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "TEES")
  private String tees;
  @Column(name = "HOLENUMBER")
  private Integer holeNumber;
  @Column(name = "PAR")
  private Integer par;
  @Column(name = "SCORE")
  private Integer score;
  @Column(name = "PUTTS")
  private Integer putts;

  @ManyToOne
  @JoinColumn(name = "round_id", nullable = false)
  private Round round;
}
