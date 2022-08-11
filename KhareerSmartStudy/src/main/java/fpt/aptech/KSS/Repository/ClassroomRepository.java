/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author backs
 */
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    @Query("select c from Classroom c where c.id = :id")
    Classroom findOne(@PathVariable("value") int id);
}
