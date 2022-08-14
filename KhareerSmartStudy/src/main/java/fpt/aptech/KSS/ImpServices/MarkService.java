/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Exam;
import fpt.aptech.KSS.Entities.Mark;
import fpt.aptech.KSS.Repository.MarkRepository;
import fpt.aptech.KSS.Services.IMark;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class MarkService implements IMark {
    @Autowired
    MarkRepository markRepository;

    @Override
    public List<Mark> findAll() {
        return markRepository.findAll();
    }

    @Override
    public List<Mark> findbyCourseExam(Course idCourse, Exam idExam) {
       return markRepository.findByExamCourse(idCourse,idExam);
        }

    @Override
    public Mark saveMark(Mark mark) {
        return  markRepository.save(mark);
    }

    @Override
    public void removeMark(Mark id) {
        markRepository.delete(id);
       }
    
}
