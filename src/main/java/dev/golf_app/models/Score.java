package dev.golf_app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "scores")
@Getter
@Setter
public class Score {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String tees;
  private Integer holeNumber;
  private Integer par;
  private Integer score;
  private Integer putts;

  @ManyToOne
  @JoinColumn(name = "round_id", nullable = false)
  private Round round;
}
