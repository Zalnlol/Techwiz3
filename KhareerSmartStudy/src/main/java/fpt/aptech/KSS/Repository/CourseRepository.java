/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author backs
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("select c from Course c where c.id = :id")
    Course findOne(@PathVariable("value") int id);
}
