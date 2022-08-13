/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Account;
import fpt.aptech.KSS.Entities.ClassroomUser;
import java.util.List;

/**
 *
 * @author jthie
 */
public interface IClassroomUserRepository {

    public List<ClassroomUser> findAll();

    public void save(ClassroomUser classroomUser);

    public ClassroomUser findOne(int id);

    public void delete(ClassroomUser classroomUser);

    public List<ClassroomUser> findClassesByUser(Account id);

}
