/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface MarkRepository extends JpaRepository<Mark, Integer> {

    @Query("SELECT m FROM Mark m WHERE m.idCourse = :idCourse AND m.idExam = :idExam")
    List<Mark> findByExamCourse(@PathVariable("idCourse") Course idCourse ,@PathVariable("idExam") Exam idExam );

    @Query("SELECT m FROM Mark m WHERE m.idUser = :idUser")
    List<Mark> findByAccount(@PathVariable("idUser") Account idUser);
    
}
