/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Contact;
import fpt.aptech.KSS.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Admin
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
    @Query("SELECT c FROM Contact c WHERE c.id = :id")
    Contact findOne(@PathVariable("value") int id);
    
}
