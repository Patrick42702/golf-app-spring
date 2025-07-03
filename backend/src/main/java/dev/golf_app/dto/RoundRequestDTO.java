package dev.golf_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.Instant;

@Data
@AllArgsConstructor
public class RoundRequestDTO {
  private Instant date;
  private Integer holes;
  private Integer totalPar;
  private Integer golfCourseId; // Only the ID, not the full object
  private Integer userId;
}
