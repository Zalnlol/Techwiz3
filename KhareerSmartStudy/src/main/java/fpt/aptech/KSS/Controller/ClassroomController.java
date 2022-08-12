/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Controller;

import fpt.aptech.KSS.Entities.Course;
import fpt.aptech.KSS.Entities.Semester;
import fpt.aptech.KSS.ImpServices.ClassroomServices;
import fpt.aptech.KSS.ImpServices.CourseServices;
import fpt.aptech.KSS.Services.IClassroomRepository;
import fpt.aptech.KSS.Services.SemesterServiceImp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author backs
 */
@Controller
public class ClassroomController {
    @Autowired
    ClassroomServices classroomServices;
    
    @Autowired
    SemesterServiceImp semesterServices;
    
    @Autowired
    CourseServices courseServices;
    
    @RequestMapping(value = "classroom/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("list", classroomServices.findAll());
        return "admin/classroom/index";
    }
    
    @RequestMapping(value = "classroom/doCreate", method = RequestMethod.GET)
    public String doCreate(Model model) {
        List<Semester> semesters = semesterServices.findAll();
        model.addAttribute("semesters", semesters);
        return "admin/classroom/create";
    }
    
    @RequestMapping(value = "classroom/create", method = RequestMethod.POST)
    public String create(Model model) {
        return index(model);
    }
    
    @RequestMapping(value = "classroom/edit/{id}", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable("_id") int id) {
        
        return index(model);
    }
    
}
