/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fpt.aptech.KSS.Services.ICourseRepository;

/**
 *
 * @author backs
 */
@Service
public class CourseServices implements ICourseRepository{

    @Autowired
    CourseRepository courseRepository;
    
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void saveCourse(Course newCourse) {
        courseRepository.save(newCourse);
    }

    @Override
    public Course findOne(int id) {
        return courseRepository.findOne(id);
    }

    @Override
    public void removeCourse(int id) {
        Course course = courseRepository.findOne(id);
        courseRepository.delete(course);
    }
 
}
