package dev.golf_app.dto;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GolfCourseDTO {
  private Integer id;
  private String name;
  private String address;
  private String zip;
  private Double longitude;
  private Double latitude;
  private String classification;
  private String state_abbr;
  private String city;
  private String phone;
}
