/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jthie
 */
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    
}
