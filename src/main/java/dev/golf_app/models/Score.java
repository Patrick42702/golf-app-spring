package dev.golf_app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
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

  // Getters and setters

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTees() {
    return tees;
  }

  public void setTees(String tees) {
    this.tees = tees;
  }

  public Integer getHoleNumber() {
    return holeNumber;
  }

  public void setHoleNumber(Integer holeNumber) {
    this.holeNumber = holeNumber;
  }

  public Integer getPar() {
    return par;
  }

  public void setPar(Integer par) {
    this.par = par;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getPutts() {
    return putts;
  }

  public void setPutts(Integer putts) {
    this.putts = putts;
  }
}
