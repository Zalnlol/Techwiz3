/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Services;

import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.Entities.SemesterCourse;
import fpt.aptech.KSS.Repository.SemesterCourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jthie
 */
@Service
public class SemesterCourseServiceImp implements ISemesterCourseRepository {

    @Autowired
    SemesterCourseRepository semesterCourseRepository;

    @Override
    public List<SemesterCourse> findAll() {
        return semesterCourseRepository.findAll();
    }

    @Override
    public void save(SemesterCourse semesterCourse) {
        semesterCourseRepository.save(semesterCourse);
    }

    @Override
    public SemesterCourse findOne(int id) {
        return semesterCourseRepository.findOne(id);
    }

    @Override
    public void delete(SemesterCourse semesterCourse) {
        semesterCourseRepository.delete(semesterCourse);
    }

    @Override
    public List<SemesterCourse> findBySemester(Semester semester) {
        return semesterCourseRepository.findBySemester(semester);
    }

}
