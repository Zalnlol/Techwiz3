/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.ImpServices;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.Classroom;
import fpt.aptech.KSS.Repository.ClassroomRepository;
import fpt.aptech.KSS.Services.IClassroomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author backs
 */
@Service
public class ClassroomServices implements IClassroomRepository{
    
    @Autowired
    ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom findOne(int id) {
        return classroomRepository.findOne(id);
    }

    @Override
    public void saveClassroom(Classroom newClassroom) {
        classroomRepository.save(newClassroom);
    }

    @Override
    public void removeClassroom(int id) {
        Classroom classroom = classroomRepository.findOne(id);
        classroomRepository.delete(classroom);
    }

    @Override
    public Classroom findOneByName(String name) {
       return  classroomRepository.findByName(name);
    }

}
