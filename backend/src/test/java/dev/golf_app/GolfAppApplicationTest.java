package dev.golf_app;

import com.querydsl.jpa.impl.JPAQuery;
import dev.golf_app.dto.GolfCourseDTO;
import dev.golf_app.models.GolfCourse;
import dev.golf_app.models.QGolfCourse;
import dev.golf_app.models.Round;
import dev.golf_app.models.Users;
import dev.golf_app.repository.GolfCourseRepository;
import dev.golf_app.repository.RoundRepository;
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
  private RoundRepository roundRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EntityManager entityManager;
  private final Logger logger = LoggerFactory.getLogger(GolfAppApplicationTest.class);

  @Test
  public void testGolfCourseInsertion(){
    List<Round> round = new ArrayList<>();
    GolfCourse golfCourse = new GolfCourse();
    golfCourse.setName("Bethpage");
    golfCourse.setAddress("09384 gotcha");
    golfCourse.setZip("12345");
    golfCourseRepository.save(golfCourse);
    assertEquals(golfCourseRepository.findById(golfCourse.getId()).get().getId(), golfCourse.getId());
  }

  @Test
  public void testGoldCourseFindByName(){
    GolfCourse golfCourse = new GolfCourse();
    golfCourse.setName("Fishkill");
    golfCourse.setAddress("09384 gotcha");
    golfCourse.setZip("12345");

    GolfCourse golfCourse2 = new GolfCourse();
    golfCourse2.setName("Beacon");
    golfCourse2.setAddress("09384 gotcha");
    golfCourse2.setZip("12345");

    GolfCourse golfCourse3 = new GolfCourse();
    golfCourse.setName("Fishkill");
    golfCourse.setAddress("09384 gotcha");
    golfCourse.setZip("00000");

    golfCourseRepository.save(golfCourse);
    golfCourseRepository.save(golfCourse2);
    golfCourseRepository.save(golfCourse3);

  }

  @Test
  public void testQueryByZipOrderByName(){
   Iterable<GolfCourseDTO> golfCourses = golfCourseRepository.findAllByZipOrderByName("12345");
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
      Users user = new Users();
        user.setEmail("Patrick@candy.com");
        user.setPassword("thispasswordshouldthrowanexception");
        user.setName("patrick muller");
      userRepository.save(user);
    });

    Users user = new Users();
    user.setEmail("Patrick@candy.com");
    user.setPassword("This-Passsword-sh0uld!-pass");
    user.setName("patrick muller");
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

  @Test
  public void testRounds(){
    Round round = new Round();
    round.setGolfCourse(golfCourseRepository.findById(1).get());
    round.setHoles(18);
    round.setTotalPar(73);
    roundRepository.save(round);
    GolfCourse golfCourse = round.getGolfCourse();
    logger.info("This is the golf course: {}", golfCourse);
    assertThat(roundRepository.findById(round.getId()).get().getId()).isEqualTo(round.getId());
  }

  @Test
  public void testRoundGolfCourseJoin(){

  }

  @Test
  public void testInsertRounds(){
    Users user = userRepository.findById(1).get();
    Users user2 = userRepository.findById(2).get();
    GolfCourse golfCourse = golfCourseRepository.findById(1).get();
    GolfCourse golfCourse2 = golfCourseRepository.findById(1).get();

    Round round = new Round();
    round.setUser(user);
    round.setGolfCourse(golfCourse);

    Round round1 = new Round();
    round1.setUser(user2);
    round1.setGolfCourse(golfCourse2);
  }
}
