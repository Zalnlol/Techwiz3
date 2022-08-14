/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Entities.ClassroomSemester;
import fpt.aptech.KSS.Repository.ClassroomSemesterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class ClassroomSemesterServiceImp implements IClassroomSemesterRepository{
    
    @Autowired
    ClassroomSemesterRepository classroomSemesterRepository;

    @Override
    public List<ClassroomSemester> findAll() {
        return classroomSemesterRepository.findAll();
    }

    @Override
    public void save(ClassroomSemester classroomSemester) {
       classroomSemesterRepository.save(classroomSemester);
    }

    @Override
    public ClassroomSemester findOne(int id) {
        return classroomSemesterRepository.findOne(id);
    }

    @Override
    public void delete(ClassroomSemester classroomSemester) {
        classroomSemesterRepository.delete(classroomSemester);
    }

    @Override
    public List<ClassroomSemester> findByClassroom(Classroom classroom) {
        return classroomSemesterRepository.findByClassroom(classroom);
    }
    
}
