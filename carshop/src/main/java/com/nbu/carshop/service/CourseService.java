package com.nbu.carshop.service;

import com.nbu.carshop.entity.Course;
import com.nbu.carshop.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getCourses() {
        List<Course> courseList = new ArrayList();
        courseRepository.findAll().forEach(course -> courseList.add(course));

        return courseList;
    }

    public Course getCourse(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        return course;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(long id, Course course) {
        courseRepository.save(course);
    }

    public void deleteCourse(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        courseRepository.delete(course);
    }

    public List<Course> getCoursesByName(String name) {
        List<Course> courseList = new ArrayList();
        courseRepository.findByName(name).forEach(course -> courseList.add(course));
        return courseList;
    }

    public List<Course> getCoursesByProgram(long programId) {
        List<Course> courseList = new ArrayList();
        courseRepository.findByProgramId(programId).forEach(course -> courseList.add(course));
        return courseList;
    }

    public List<Course> getCoursesWithGradesGreaterThan(int grades) {
        List<Course> courseList = new ArrayList();
        courseRepository.findCoursesGradesGreaterThan(grades).forEach(course -> courseList.add(course));
        return courseList;
    }

    public double getCoursesAverageGrade() {
        double sum = 0;
        for (Course course : getCourses()) {
            sum += course.getGrades();
        }
        return getCourses().size() > 0 ? sum / getCourses().size() : 0;
    }

    public double getCoursesInProgramAverageGrade(long programId) {
        double sum = 0;
        for (Course course : getCoursesByProgram(programId)) {
            sum += course.getGrades();
        }
        return getCoursesByProgram(programId).size() > 0 ? sum / getCoursesByProgram(programId).size() : 0;
    }
}