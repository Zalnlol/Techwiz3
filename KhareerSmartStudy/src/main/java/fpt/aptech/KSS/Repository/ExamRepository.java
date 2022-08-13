/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.Exam;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;


/**
 *
 * @author LÊ HỮU TÂM
 */
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    @Query("SELECT e FROM Exam e WHERE e.idClassroom = :idClassroom")
    List<Exam> findByClass(@PathVariable("idClassroom") Classroom idClassroom);
//    @Query("SELECT e FROM Exam e WHERE e.id = :id")
//    Exam findByName(@PathVariable("name") String name);
}
