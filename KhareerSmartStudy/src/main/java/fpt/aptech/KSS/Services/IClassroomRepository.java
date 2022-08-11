/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Classroom;
import java.util.List;

/**
 *
 * @author backs
 */
public interface IClassroomRepository {
    public List<Classroom> findAll();
    public Classroom findOne(int id);
    public void saveClassroom(Classroom newClassroom);
    public void removeClassroom(int id);
}
