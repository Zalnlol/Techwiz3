/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.ClassroomUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author jthie
 */
public interface ClassroomUserRepository extends JpaRepository<ClassroomUser, Integer> {

    @Query("SELECT c FROM ClassroomUser c WHERE c.id = :id")
    ClassroomUser findOne(@PathVariable("id") int id);
    
    @Query("SELECT c FROM ClassroomUser c WHERE c.idUser = :idUser")
    List<ClassroomUser> findidUser(@PathVariable("idUser") Account idUser);
    
    @Query("SELECT c FROM ClassroomUser c WHERE c.idClassroom = :idClassroom")
    List<ClassroomUser> findAllUserSameClass(@PathVariable("idClassroom") Classroom idClassroom);
    
}
