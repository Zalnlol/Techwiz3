/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fpt.aptech.KSS.Entities.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    @Query("SELECT m FROM Mark m WHERE m.idUser = :idUser")
    List<Mark> findByAccount(@PathVariable("idUser") Account idUser);
}
