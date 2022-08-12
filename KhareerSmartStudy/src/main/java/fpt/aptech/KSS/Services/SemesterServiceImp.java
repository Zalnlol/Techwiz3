/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.Repository.SemesterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class SemesterServiceImp implements ISemesterRepository{
    
    @Autowired
    SemesterRepository semesterRepository;

    @Override
    public List<Semester> findAll() {
        return semesterRepository.findAll();
    }

    @Override
    public void save(Semester semester) {
        semesterRepository.save(semester);
    }
    
}
