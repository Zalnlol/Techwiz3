/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Repository.ExamRepository;
import fpt.aptech.KSS.Services.IExam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class ExamServices implements IExam{
    @Autowired
    ExamRepository examRepository;       
    @Override
    public List<Exam> findListByClass(Classroom classroom) {
        return examRepository.findByClass(classroom);
    }

    public  List<Exam> findAAll(){
      return   examRepository.findAll();
    }

    public  Exam findOne(int id){
        return   examRepository.findByID(id);
    }


    public  Exam Create(Exam exam){
        return   examRepository.save(exam);
    }

    public  Exam Edit(Exam exam){
        return   examRepository.save(exam);
    }
    
}
