package com.nbu.carshop.controller;

import com.nbu.carshop.entity.Course;
import com.nbu.carshop.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping("/courses")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }

    @RequestMapping("/courses/{id}")
    public Course getCourse(@PathVariable("id") int id) {
        return courseService.getCourse(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/courses")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/courses")
    public void updateCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/courses/{id}")
    public void deleteCourse(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
    }

    @RequestMapping("/courses/name/{name}")
    public List<Course> getCoursesByName(@PathVariable("name") String name) {
        return courseService.getCoursesByName(name);
    }

    @RequestMapping("/programs/{id}/courses")
    public List<Course> getCoursesByProgram(@PathVariable("id") long id) {
        return courseService.getCoursesByProgram(id);
    }

    @RequestMapping("/courses/grades/{grades}")
    public List<Course> getCoursesByGrades(@PathVariable("grades") int grades) {
        return courseService.getCoursesWithGradesGreaterThan(grades);
    }

    @RequestMapping("/courses/averagegrades")
    public double getCoursesAverageGrade() {
        return courseService.getCoursesAverageGrade();
    }

    @RequestMapping("/programs/{id}/courses/averagegrades")
    public double getCoursesInProgramAverageGrade(@PathVariable("id") long id) {
        return courseService.getCoursesInProgramAverageGrade(id);
    }

}
