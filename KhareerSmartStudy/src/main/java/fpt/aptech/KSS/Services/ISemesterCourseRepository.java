/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.Entities.SemesterCourse;
import java.util.List;

/**
 *
 * @author jthie
 */
public interface ISemesterCourseRepository {

    public List<SemesterCourse> findAll();

    public void save(SemesterCourse semesterCourse);

    public SemesterCourse findOne(int id);

    public void delete(SemesterCourse semesterCourse);
    public List<SemesterCourse> findBySemester(Semester semester);
}
