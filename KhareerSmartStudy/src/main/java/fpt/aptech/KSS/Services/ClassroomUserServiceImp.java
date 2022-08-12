/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.ClassroomUser;
import fpt.aptech.KSS.Repository.ClassroomUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class ClassroomUserServiceImp implements IClassroomUserRepository{
    
    @Autowired
    ClassroomUserRepository classroomUserRepository;

    @Override
    public List<ClassroomUser> findAll() {
        return classroomUserRepository.findAll();
    }

    @Override
    public void save(ClassroomUser classroomUser) {
        classroomUserRepository.save(classroomUser);
    }

    @Override
    public ClassroomUser findOne(int id) {
        return classroomUserRepository.findOne(id);
    }

    @Override
    public void delete(ClassroomUser classroomUser) {
        classroomUserRepository.delete(classroomUser);
    }
    
}
