/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.ClassroomSemester;
import java.util.List;

/**
 *
 * @author jthie
 */
public interface IClassroomSemesterRepository {
    public List<ClassroomSemester> findAll();

    public void save(ClassroomSemester classroomSemester);
    
    public ClassroomSemester findById(int id);
    
    public void delete(ClassroomSemester classroomSemester);
}
