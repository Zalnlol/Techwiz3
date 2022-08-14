/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Course;
import java.util.List;

/**
 *
 * @author backs
 */
public interface ICourseRepository {

    public List<Course> findAll();

    public Course findOne(int id);

    public void saveCourse(Course course);

    public void removeCourse(int id);
    
}
