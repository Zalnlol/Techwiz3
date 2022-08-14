/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Document;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Mark;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IMark {
        
      public List<Mark> findAll();

    public List<Mark> findbyCourseExam(Course idCourse, Exam idExam);
    
     public Mark saveMark(Mark mark);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    public void removeMark(Mark id);
    List<Mark> findByAccount(Account account);
    
}
