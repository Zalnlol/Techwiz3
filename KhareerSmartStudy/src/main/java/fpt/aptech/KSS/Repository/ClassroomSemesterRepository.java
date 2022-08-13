/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.ClassroomSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author jthie
 */
public interface ClassroomSemesterRepository extends JpaRepository<ClassroomSemester, Integer> {
    
    @Query("SELECT c FROM ClassroomSemester c WHERE c.id = :id")
    ClassroomSemester findOne(@PathVariable("id") int id);
    
}
