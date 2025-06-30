package dev.golf_app.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RoundRequest {
  private Date date;
  private Integer holes;
  private Integer totalPar;
  private Integer golfCourseId; // Only the ID, not the full object
}
