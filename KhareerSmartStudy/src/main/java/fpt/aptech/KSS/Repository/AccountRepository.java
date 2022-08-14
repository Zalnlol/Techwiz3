/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Repository;

import fpt.aptech.KSS.Entities.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author jthie
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.mail = :mail")
    Account findByEmail(@PathVariable("mail") String mail);

    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Account findById(@PathVariable("id") int id);

    @Query("SELECT a FROM Account a WHERE CONCAT(a.mail,' ', a.name,' ', a.phone) LIKE %?1%")
    public List<Account> search(String keyword);

    @Query("SELECT a FROM Account a WHERE a.code = :code")
    Account checkUniqueCode(@PathVariable("code") String code);

    @Query(value = "SELECT * FROM Account WHERE id = :id order by id DESC limit 1", nativeQuery = true)
    Account selectLatestRecord(@PathVariable("id") Account id);

    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.role = 'Admin'")
    Account findByEmailAdmin(@PathVariable("mail") String mail);

    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.role = 'Teacher'")
    Account findByEmailTeacher(@PathVariable("mail") String mail);

    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.role = 'Student'")
    Account findByEmailStudent(@PathVariable("mail") String mail);
    
    @Query("SELECT a FROM Account a WHERE a.role = 'Teacher' AND a.name != NULL")
    List<Account> findAllTeacherList(@PathVariable("role") String role);
}
