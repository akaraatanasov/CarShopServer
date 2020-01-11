package com.nbu.carshop.repository;

import com.nbu.carshop.entity.Course;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByName(String name);

    List<Course> findByProgramId(long programId);

    List<Course> findByCredits(int credits);

    List<Course> findByGrades(int grades);

    @Query(value = "SELECT c FROM Course c WHERE c.grades > :grades")
    List<Course> findCoursesGradesGreaterThan(@Param("grades") int grades);

}
