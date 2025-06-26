package dev.golf_app;

import com.querydsl.jpa.impl.JPAQuery;
import dev.golf_app.models.GolfCourse;
import dev.golf_app.models.QGolfCourse;
import dev.golf_app.models.Round;
import dev.golf_app.models.Users;
import dev.golf_app.repository.GolfCourseRepository;
import dev.golf_app.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GolfAppApplicationTest{
  @Autowired
  private GolfCourseRepository golfCourseRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EntityManager entityManager;
  private final Logger logger = LoggerFactory.getLogger(GolfAppApplicationTest.class);

  @Test
  public void testGolfCourseInsertion(){
    List<Round> round = new ArrayList<>();
    GolfCourse golfCourse = new GolfCourse().toBuilder()
      .name("Bethpage")
      .address("09384 gotcha")
      .zip("12345")
      .build();
    golfCourseRepository.save(golfCourse);
    assertEquals(golfCourseRepository.findById(golfCourse.getId()).get().getId(), golfCourse.getId());
  }

  @Test
  public void testGoldCourseFindByName(){
    GolfCourse golfCourse = new GolfCourse().toBuilder()
      .name("Fishkill")
      .address("09384 gotcha")
      .zip("12345")
      .build();

    GolfCourse golfCourse2 = new GolfCourse().toBuilder()
      .name("Beacon")
      .address("09384 gotcha")
      .zip("12345")
      .build();

    GolfCourse golfCourse3 = new GolfCourse().toBuilder()
      .name("Fishkill")
      .address("09384 gotcha")
      .zip("00000")
      .build();

    golfCourseRepository.save(golfCourse);
    golfCourseRepository.save(golfCourse2);
    golfCourseRepository.save(golfCourse3);

//    assertEquals(3, Iterables.size(golfCourseRepository.findAllByName("Fishkill")));
  }

  @Test
  public void testQueryByZipOrderByName(){
   Iterable<GolfCourse> golfCourses = golfCourseRepository.findAllByZipOrderByName("12345");
   golfCourses.forEach(golfCourse -> logger.info(golfCourse.getName()));
  }

  @Test
  public void givenDataAvailableLoadFirstFiveRecords(){
    Pageable pageable = PageRequest.of(0, 5);
    assertThat(golfCourseRepository.findAll(pageable)).hasSize(5);
    assertThat(pageable.getPageNumber()).isEqualTo(0);

    Pageable nextPageable = pageable.next();
  }

  @Test
  public void testInsertNewUser(){
    assertThrows(ConstraintViolationException.class, () -> {
      Users user = new Users().toBuilder()
        .email("Patrick@candy.com")
        .password("thispasswordshouldthrowanexception")
        .name("patrick muller")
        .build();
      userRepository.save(user);
    });

    Users user = new Users().toBuilder()
      .email("Patrick@candy.com")
      .password("This-Passsword-sh0uld!-pass")
      .name("patrick muller")
      .build();
    userRepository.save(user);
    assertThat(userRepository.findById(user.getId()).get().getId()).isEqualTo(user.getId());
  }

  @Test
  public void testQuerydsl(){
    QGolfCourse course = QGolfCourse.golfCourse;
    JPAQuery<GolfCourse> query1 = new JPAQuery<GolfCourse>(entityManager);

    query1.from(course).where(course.name.eq("Fishkill").and(course.address.eq("09384 gotcha")));
    List<GolfCourse> resultList = query1.fetch();
    logger.info("Query results:{}", resultList);

  }
}
