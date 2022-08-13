/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.Exam;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IExam {
  List<Exam> findListByClass(Classroom classroom); 
//    List<Exam> findListExamByIdStudent()
}
