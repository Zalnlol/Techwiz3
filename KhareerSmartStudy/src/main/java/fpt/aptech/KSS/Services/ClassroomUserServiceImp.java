/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.ClassroomUser;
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
    IClassroomUserRepository classroomUserRepository;

    @Override
    public List<ClassroomUser> findAll() {
        return classroomUserRepository.findAll();
    }

    @Override
    public void save(ClassroomUser classroomUser) {
        classroomUserRepository.save(classroomUser);
    }

    @Override
    public ClassroomUser findById(int id) {
        return classroomUserRepository.findById(id);
    }

    @Override
    public void delete(ClassroomUser classroomUser) {
        classroomUserRepository.delete(classroomUser);
    }
    
}
