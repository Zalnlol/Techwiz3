/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    
     @Query("SELECT d FROM Document d WHERE d.id = :id")
    Document findOne(@PathVariable("id") int id);


    @Query("SELECT d FROM Document d WHERE d.idCourse = :idCourse")
    Document findByCourse(@PathVariable("idCourse") Course idCourse);

}
