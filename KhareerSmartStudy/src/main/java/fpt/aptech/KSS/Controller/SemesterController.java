/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.Entities.SemesterCourse;
import fpt.aptech.KSS.Routes.RouteWeb;
import fpt.aptech.KSS.Services.IAccountRepository;
import fpt.aptech.KSS.Services.IClassroomSemesterRepository;
import fpt.aptech.KSS.Services.ICourseRepository;
import fpt.aptech.KSS.Services.ISemesterCourseRepository;
import fpt.aptech.KSS.Services.ISemesterRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jthie
 */
@Controller
public class SemesterController {

    @Autowired
    private ISemesterRepository semesterRepository;

    @Autowired
    private ICourseRepository courseRepository;
    
    @Autowired
    private ISemesterCourseRepository semesterCourseRepository;

    @RequestMapping(value = {RouteWeb.SemesterManageURL}, method = RequestMethod.GET)
    public String SemesterList(Model model) {
        List<Semester> list = new ArrayList<>();
        list = semesterRepository.findAll();

        boolean check = false;
        for (Semester item : list) {
            if (item.getId() != null) {

                check = true;
                break;
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("check", check);
        return "admin/semester/index";
    }

    @RequestMapping(value = {RouteWeb.SemesterGetCreateURL}, method = RequestMethod.GET)
    public String GetCreate(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "admin/semester/create";
    }

    @RequestMapping(value = "semester/postCreate", method = RequestMethod.POST)
    public String PostCreate(Model model, HttpServletRequest request, HttpServletResponse response) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String[] courseList = request.getParameterValues("courses");
            String day = request.getParameter("beginDate").substring(0, 2);
            String month = request.getParameter("beginDate").substring(3, 5);
            String year = request.getParameter("beginDate").substring(6, 10);
            Date date = simpleDateFormat.parse(year + "-" + month + "-" + day);
            Semester semester = new Semester();
            semester.setStartDate(date);
            day = request.getParameter("endDate").substring(0, 2);
            month = request.getParameter("endDate").substring(3, 5);
            year = request.getParameter("endDate").substring(6, 10);
            date = simpleDateFormat.parse(year + "-" + month + "-" + day);
            semester.setEndDate(date);
            semester.setName(request.getParameter("txtSemesterName"));
            semester.setDescription(request.getParameter("txtDescription"));
            semesterRepository.save(semester);
            for (int i = 0; i < courseList.length; i++) {
                Course course = courseRepository.findOne(Integer.parseInt(courseList[i]));
                if(course != null){
                    SemesterCourse semesterCourse = new SemesterCourse();
                    semesterCourse.setIdCourse(course);
                    semesterCourse.setIdSemester(semester);
                    //JsonServices.dd(JsonServices.ParseToJson(semesterCourse.getIdCourse() + " " + semesterCourse.getIdSemester()), response);
                    semesterCourseRepository.save(semesterCourse);
                }
            }

            return SemesterList(model);
        } catch (ParseException ex) {
            Logger.getLogger(SemesterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @RequestMapping(value = {RouteWeb.SemesterGetCreateURL}, method = RequestMethod.GET)
    public String edit(Model model) {

        return "admin/semester/update";
    }   
}
