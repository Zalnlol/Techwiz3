/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.SemesterCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author jthie
 */
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse, Integer> {

    @Query("SELECT s FROM SemesterCourse s WHERE s.id = :id")
    SemesterCourse findOne(@PathVariable("id") int id);
}
